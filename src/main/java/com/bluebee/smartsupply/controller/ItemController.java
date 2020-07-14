package com.bluebee.smartsupply.controller;

import com.bluebee.smartsupply.model.Items;
import com.bluebee.smartsupply.model.Result;
import com.bluebee.smartsupply.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ItemController  {

    @Autowired
    ItemService itemService;

    @RequestMapping(value = "items",method = RequestMethod.GET)
    public List<Items> getAllItems(){
        return itemService.getAllItems();
    }

    @RequestMapping(value = "items/{itemid}",method = RequestMethod.GET)
    public Items getItemById(@PathVariable("itemid")int itemid){
        return itemService.getItemById(itemid);
    }

    @RequestMapping(value = "items",method = RequestMethod.POST)
    public Items addItem(@RequestBody Items item){
        return itemService.addItem(item);
    }

    @RequestMapping(value = "items",method = RequestMethod.PUT)
    public Items updateItem(@RequestBody Items item){
        return itemService.updateItem(item);
    }

    @RequestMapping(value = "items/{itemcode}",method = RequestMethod.DELETE)
    public Items deleteItem(@PathVariable("itemid")int itemid){
        return itemService.deleteItem(itemid);
    }



}
