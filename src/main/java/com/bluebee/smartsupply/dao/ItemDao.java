package com.bluebee.smartsupply.dao;


import com.bluebee.smartsupply.model.Items;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemDao extends NamedParameterJdbcDaoSupport {

    static final Items ITEM=new Items();

    @Autowired
    private DataSource dataSource;

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<Items> getAllItems(){
       return namedParameterJdbcTemplate.query("SELECT ITEMNAME,ITEMDESC,ITEMID,PRODCAT,ITEMCODE FROM VSV58378.ITEM_INFO", new BeanPropertyRowMapper(Items.class));
    }

    public Items getItemById(int itemid){
        Map<String,Object> map = new HashMap<>(1);
        map.put("itemid",itemid);
        String sql="SELECT ITEMNAME,ITEMDESC,ITEMID,PRODCAT,ITEMCODE FROM VSV58378.ITEM_INFO where ITEMCODE= :itemid";
        return (Items) namedParameterJdbcTemplate.queryForObject(sql,map,new BeanPropertyRowMapper(Items.class));
    }

    public Items addItem(Items item){
        String sql="insert into VSV58378.ITEM_INFO (ITEMNAME,ITEMDESC,ITEMID,PRODCAT,ITEMCODE) " +
                "values(:itemname,:itemdesc,:itemId,:prodcat,:itemcode)";
        item.setItemcode(getSequence()+1);
        String stage=null;
        stage = getProdCat(item);
        item.setItemId(stage);
        SqlParameterSource sqlpara=new BeanPropertySqlParameterSource(item);
        namedParameterJdbcTemplate.update(sql,sqlpara);
        return getItemById(item.getItemcode());
    }

    private String getProdCat(Items item) {
        String stage=null;
        if(item.getProdcat()==1){
            stage ="VEG"+"0000"+ item.getItemcode();
        }else if(item.getProdcat()==1){
            stage ="GROW"+"0000"+ item.getItemcode();
        }
        return stage;
    }

    private int getSequence(){
        String sql="SELECT max(ITEMCODE)+1 FROM VSV58378.ITEM_INFO";
        return namedParameterJdbcTemplate.queryForObject(sql,new HashMap(),Integer.class);
    }

    public Items updateItem(Items item){
        String stage=null;
        stage = getProdCat(item);
        item.setItemId(stage);
        //:itemname,:itemdesc,:itemId,:prodcat,:itemcode
        String sql="UPDATE  VSV58378.ITEM_INFO set ITEMNAME=:itemname,ITEMDESC=:itemdesc,ITEMID=:itemId,PRODCAT=:prodcat where ITEMCODE = :itemcode";
        SqlParameterSource sqlpara=new BeanPropertySqlParameterSource(item);
        namedParameterJdbcTemplate.update(sql,sqlpara);
        return (Items) getItemById(item.getItemcode());
    }

    public Items deleteItem(int itemid){
        Map<String,Object> map = new HashMap<>(1);
        map.put("itemid",itemid);
        String sql="DELETE FROM FROM VSV58378.ITEM_INFO where ITEMCODE= :itemid";
        int update = namedParameterJdbcTemplate.update(sql,map);
        return update==0?null:ITEM;
    }
}
