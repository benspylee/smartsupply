package com.bluebee.smartsupply.controller;


import com.bluebee.smartsupply.model.AppUserAddress;
import com.bluebee.smartsupply.model.Items;
import com.bluebee.smartsupply.model.Order;
import com.bluebee.smartsupply.model.Shop;
import com.bluebee.smartsupply.service.AppUserAddressService;
import com.bluebee.smartsupply.service.ItemService;
import com.bluebee.smartsupply.service.OrderService;
import com.bluebee.smartsupply.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SearchController {

    @Autowired
    ItemService itemService;

    @Autowired
    ShopService shopService;

    @Autowired
    AppUserAddressService appUserAddressService;


    @Autowired
    OrderService orderService;

    @RequestMapping(value = "items/users/{appuserid}",method = RequestMethod.GET)
    public List<Items> getItemsByUserId(@PathVariable("appuserid")int appuserid) throws Exception{
        return itemService.getItemByUserId(appuserid);
    }

    @RequestMapping(value = "order/users/{appuserid}",method = RequestMethod.GET)
    public List<Order> getOrderByUserId(@PathVariable("appuserid")int appuserid) throws Exception{
        return orderService.getOrderByUserId(appuserid);
    }
    @RequestMapping(value = "order/owner/{appuserid}",method = RequestMethod.GET)
    public List<Order> getOrderByOwnerUserId(@PathVariable("appuserid")int appuserid) throws Exception{
        return orderService.getOrderByOwnerUserId(appuserid);
    }


    @RequestMapping(value = "appuseraddress/users/{appuserid}",method = RequestMethod.GET)
    public List<AppUserAddress> getAddressByUserId(@PathVariable("appuserid")int appuserid) throws Exception{
        return appUserAddressService.getAddressByUserId(appuserid);
    }

    @RequestMapping(value = "shop/users/{appuserid}",method = RequestMethod.GET)
    public List<Shop> getShopByUserId(@PathVariable("appuserid")int appuserid) throws Exception{
        return shopService.getShopByUserId(appuserid);
    }

    @RequestMapping(value = "shop/search",method = RequestMethod.GET)
    public List<Shop> getShopByCriteria(@RequestParam("zipcode") String zipcode
            ,@RequestParam("shopname") String shopname,@RequestParam("itemname") String itemname) throws Exception{
        Shop shop=new Shop();
        shop.setZipcode(zipcode);
        shop.setItemname(itemname);
        shop.setShopname(shopname);
        return shopService.getShopByCriteria(shop);
    }

}
