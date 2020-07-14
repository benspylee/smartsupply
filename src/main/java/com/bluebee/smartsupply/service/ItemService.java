package com.bluebee.smartsupply.service;

import com.bluebee.smartsupply.dao.ItemDao;
import com.bluebee.smartsupply.model.Items;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService extends CommonService{

    @Autowired
    ItemDao itemDao;

    public List<Items> getAllItems(){
        try {
            return  itemDao.getAllItems();
        } catch (Exception e) {

        }
        return null;
    }

    public Items getItemById(int itemid){
        try {
            return  itemDao.getItemById(itemid);
        } catch (Exception e) {

        }
        return null;
    }

    public Items addItem(Items item){
        try {
            return  itemDao.addItem(item);
        } catch (Exception e) {

        }
        return null;
    }

    public Items updateItem(Items item){
        try {
            return  itemDao.updateItem(item);
        } catch (Exception e) {

        }
        return null;
    }

    public Items deleteItem(int itemid){
        try {
            return  itemDao.deleteItem(itemid);
        } catch (Exception e) {

        }
        return null;
    }


}
