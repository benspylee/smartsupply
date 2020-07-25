package com.bluebee.smartsupply.service;

import com.bluebee.smartsupply.dao.OrderDao;
import com.bluebee.smartsupply.model.Order;
import com.bluebee.smartsupply.model.OrderBundle;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderService extends CommonService{
    Log logger= LogFactory.getLog(OrderService.class);

    @Autowired
    OrderDao orderDao;

    public List<Order> getAllOrder() throws Exception{
        try {
            return  orderDao.getAllOrder();
        } catch (Exception e) {
            logger.error(e);
            throw new Exception(e);
        }
    }

    public Order getOrderById(int orderid) throws Exception{
        try {
            return  orderDao.getOrderById(orderid);
        } catch (Exception e) {
            logger.error(e);
            throw new Exception(e);
        }
    }

    public List<Order> getOrderByUserId(int appusercd) throws Exception{
        try {
            return  orderDao.getOrderByUserId(appusercd);
        } catch (Exception e) {
            logger.error(e);
            throw new Exception(e);
        }
    }

    public List<Order> getOrderByOwnerUserId(int appusercd) throws Exception{
        try {
            return  orderDao.getOrderByOwnerUserId(appusercd);
        } catch (Exception e) {
            logger.error(e);
            throw new Exception(e);
        }
    }


    public List<Order> placeOrder(OrderBundle orderBundle) throws Exception{
        try {
            return  orderDao.placeOrder(orderBundle);
        } catch (Exception e) {
            logger.error(e);
            throw new Exception(e);
        }
    }

    public Order addOrder(Order order) throws Exception{
        try {
            return  orderDao.addOrder(order);
        } catch (Exception e) {
            logger.error(e);
            throw new Exception(e);
        }
    }

    public Order updateOrder(Order order) throws Exception{
        try {
            return  orderDao.updateOrder(order);
        } catch (Exception e) {
            logger.error(e);
            throw new Exception(e);
        }
    }

    public Order deleteOrder(int orderid) throws Exception{
        try {
            return  orderDao.deleteOrder(orderid);
        } catch (Exception e) {
            logger.error(e);
            throw new Exception(e);
        }
    }


}