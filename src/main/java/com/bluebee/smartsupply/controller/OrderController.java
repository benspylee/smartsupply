package com.bluebee.smartsupply.controller;

import com.bluebee.smartsupply.model.Order;
import com.bluebee.smartsupply.model.OrderBundle;
import com.bluebee.smartsupply.model.Result;
import com.bluebee.smartsupply.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController  {

    @Autowired
    OrderService orderService;

    @RequestMapping(value = "order",method = RequestMethod.GET)
    public List<Order> getAllOrder() throws Exception{
        return orderService.getAllOrder();
    }

    @RequestMapping(value = "order/{orderid}",method = RequestMethod.GET)
    public Order getOrderById(@PathVariable("orderid")int orderid)  throws Exception{
        return orderService.getOrderById(orderid);
    }

    @RequestMapping(value = "order",method = RequestMethod.POST)
    public Order addOrder(@RequestBody Order order)  throws Exception{
        return orderService.addOrder(order);
    }

    @RequestMapping(value = "order",method = RequestMethod.PUT)
    public Order updateOrder(@RequestBody Order order)  throws Exception{
        return orderService.updateOrder(order);
    }

    @RequestMapping(value = "order/{orderid}",method = RequestMethod.DELETE)
    public Order deleteOrder(@PathVariable("orderid")int orderid)  throws Exception{
        return orderService.deleteOrder(orderid);
    }

    @RequestMapping(value = "order/template",method = RequestMethod.GET)
        public Order templateOrder()  throws Exception{
            return new Order();
        }

    @RequestMapping(value = "order/placeorder",method = RequestMethod.POST)
    public List<Order> placeOrder(@RequestBody OrderBundle orderbundle)  throws Exception{
        return orderService.placeOrder(orderbundle);
    }

    @RequestMapping(value = "order/accept",method = RequestMethod.POST)
    public List<Order> acceptOrder(@RequestBody OrderBundle orderbundle)  throws Exception{
        return orderService.processOrderStatus(orderbundle);
    }

    @RequestMapping(value = "order/reject",method = RequestMethod.POST)
    public List<Order> rejectOrder(@RequestBody OrderBundle orderbundle)  throws Exception{
        return orderService.processOrderStatus(orderbundle);
    }

    @RequestMapping(value = "order/acceptdelivery",method = RequestMethod.POST)
    public List<Order> acceptdelivery(@RequestBody OrderBundle orderbundle)  throws Exception{
        return orderService.processOrderStatus(orderbundle);
    }

    @RequestMapping(value = "order/rejectdelivery",method = RequestMethod.POST)
    public List<Order> rejectdelivery(@RequestBody OrderBundle orderbundle)  throws Exception{
        return orderService.processOrderStatus(orderbundle);
    }

}