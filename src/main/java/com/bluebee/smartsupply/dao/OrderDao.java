package com.bluebee.smartsupply.dao;


import com.bluebee.smartsupply.model.*;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
@Transactional(rollbackFor = Exception.class)
public class OrderDao extends NamedParameterJdbcDaoSupport {

    static final Order ORDER=new Order();

    @Autowired
    private DataSource dataSource;

    @Autowired
    private ShopDao shopDao;

    @Autowired
    private OrderItemDao orderItemDao;

    @Autowired
    private AppUserAddressDao appUserAddressDao;

    @Autowired
    private OrderPaymentDao orderPaymentDao;

    @Autowired
    private OrderDeliveryDao orderDeliveryDao;

    @Autowired
    private OrderStatusDao orderStatusDao;

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<Order> getAllOrder(){
    String sql="SELECT ORDER_BY  orderby ,STATUS  status ,ORDER_ID  orderid ,ORDER_TO  orderto ,ORDER_DT  orderdt ,SHOP_CD  shopcd  FROM VSV58378.ORDER  WHERE STATUS =  1";
    return namedParameterJdbcTemplate.query(sql, new BeanPropertyRowMapper(Order.class));
    }

    public List<Order> getOrderByUserId(int appusercd){
        Map<String,Object> map = new HashMap<>(1);
        map.put("appusercd",appusercd);
        String sql="SELECT ORDER_BY  orderby ,STATUS  status ,ORDER_ID  orderid ,ORDER_TO  orderto ,ORDER_DT  orderdt ,SHOP_CD  shopcd  FROM VSV58378.ORDER  WHERE STATUS =  1 and = ORDER_BY=:appusercd";
        return namedParameterJdbcTemplate.query(sql, new BeanPropertyRowMapper(Order.class));
    }

    public List<Order> getOrderByOwnerUserId(int appusercd){
        Map<String,Object> map = new HashMap<>(1);
        map.put("appusercd",appusercd);
        String sql="SELECT ORDER_BY  orderby ,STATUS  status ,ORDER_ID  orderid ,ORDER_TO  orderto ,ORDER_DT  orderdt ,SHOP_CD  shopcd  FROM VSV58378.ORDER  WHERE STATUS =  1 and = ORDER_TO=:appusercd";
        return namedParameterJdbcTemplate.query(sql, new BeanPropertyRowMapper(Order.class));
    }

    public Order getOrderById(int orderid){
        Map<String,Object> map = new HashMap<>(1);
        map.put("orderid",orderid);
        String sql="SELECT ORDER_BY  orderby ,STATUS  status ,ORDER_ID  orderid ,ORDER_TO  orderto ,ORDER_DT  orderdt ,SHOP_CD  shopcd  FROM VSV58378.ORDER  WHERE ORDER_ID = :orderid ";
        return (Order) namedParameterJdbcTemplate.queryForObject(sql,map,new BeanPropertyRowMapper(Order.class));
    }

    public Order addOrder(Order order){
        String sql="INSERT INTO VSV58378.ORDER(ORDER_BY,ORDER_DT,ORDER_ID,ORDER_TO,SHOP_CD,STATUS) values(:orderby,:orderdt,:orderid,:orderto,:shopcd,:status)";
        order.setOrderid(getSequence()+1);
        SqlParameterSource sqlpara=new BeanPropertySqlParameterSource(order);
        namedParameterJdbcTemplate.update(sql,sqlpara);
        return getOrderById(order.getOrderid());
    }


    private int getSequence(){
        String sql="SELECT max(ORDER_ID) FROM VSV58378.ORDER ";
        Integer seq= null;
        try {
            seq = namedParameterJdbcTemplate.queryForObject(sql,new HashMap(),Integer.class);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return seq==null?0:seq;
    }

    public Order updateOrder(Order order){
        String sql="UPDATE VSV58378.ORDER set ORDER_BY=:orderby ,STATUS=:status ,ORDER_TO=:orderto ,ORDER_DT=:orderdt ,SHOP_CD=:shopcd  WHERE ORDER_ID = :orderid ";
        SqlParameterSource sqlpara=new BeanPropertySqlParameterSource(order);
        namedParameterJdbcTemplate.update(sql,sqlpara);
        return (Order) getOrderById(order.getOrderid());
    }

    public Order deleteOrder(int orderid){
        Map<String,Object> map = new HashMap<>(1);
        map.put("orderid",orderid);
        String sql="DELETE FROM VSV58378.ORDER  WHERE ORDER_ID = :orderid ";
        int update = namedParameterJdbcTemplate.update(sql,map);
        return update==0?null:ORDER;
    }



    public List<Order> placeOrder(OrderBundle orderBundle) throws Exception{
       List<OrderItem> orderItems = orderBundle.getOrderItems();
       OrderPayment orderPayment= orderBundle.getOrderPayment();
      OrderDelivery orderDelivery =  orderBundle.getOrderDelivery();

       AppUser appUser= orderBundle.getAppUser();
       Map<Integer,List<OrderItem>> orderItemsets= new HashMap<>() ;
       orderItems.stream().forEachOrdered(obj->{
         List temp=  orderItemsets.get(obj.getShopcode());
           if(temp==null)
            temp=new ArrayList<>();
           temp.add(obj);
           orderItemsets.put(obj.getShopcode(),temp);
       });
        orderItemsets.forEach((key,value)->{
          Shop shop =  shopDao.getShopById(key);
            Order order=new Order();
            order.setOrderby(appUser.getAppusercd());
            order.setOrderto(shop.getAppusercd());
            order.setShopcd(key);
            order.setOrderdt(new Timestamp(System.currentTimeMillis()));
            order.setStatus(1);
            addOrder(order);

            value.forEach(val->
            {
                val.setOrderid(order.getOrderid());
                val.setStatus(1);
                orderItemDao.addOrderItem(val);
            });

            orderPayment.setIspaid(0);
            orderPayment.setOrderid(order.getOrderid());
            orderPayment.setStatus(1);
            orderPaymentDao.addOrderPayment(orderPayment);

            if(orderDelivery.getDeliveryaddr()==null || orderDelivery.getDeliveryaddr()==0) {
                try {
                    AppUserAddress appUserAddress = appUserAddressDao.getAddressByUserId(appUser.getAppusercd()).get(0);
                    orderDelivery.setDeliveryaddr(appUserAddress.getAppuseraddrcd());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
           // AppUserAddress appownerAddress = appUserAddressDao.getAddressByUserId(shop.getAppusercd());
            orderDelivery.setFromaddr(shop.getShopcd());

            orderDelivery.setStatus(1);
            orderDelivery.setOrderid(order.getOrderid());
            orderDeliveryDao.addOrderDelivery(orderDelivery);


            OrderStatus orderStatus=new OrderStatus();
            orderStatus.setStatus(1);
            orderStatus.setOrderts(new Timestamp(System.currentTimeMillis()));
            orderStatus.setOrderid(order.getOrderid());
            orderStatus.setOrderstatuscd(1); //1-order placed //2- accepted //3-rejected //4-delivery accepted
            //5- delivery rejected // 6 - inTransit //7-delivered //8-cancelled
            orderStatusDao.addOrderStatus(orderStatus);

        });

   return getOrderByUserId(appUser.getAppusercd());
    }
}