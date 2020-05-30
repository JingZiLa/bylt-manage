package com.mirror.controller;

import com.github.pagehelper.PageInfo;
import com.mirror.domain.Orders;
import com.mirror.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @Author Mirror
 * @CreateDate 2020/3/2.
 * 订单信息Controller
 */
@Controller
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    @GetMapping("/findAll")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1")Integer page,@RequestParam(name = "pagesize", required = true,defaultValue = "5") Integer pagesize) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        List<Orders> orders = ordersService.findAll(page,pagesize);
        PageInfo pageInfo = new PageInfo(orders);
        modelAndView.addObject("pageInfo",pageInfo);
        modelAndView.setViewName("orders-list");
        return modelAndView;
    }


    @GetMapping("/findById")
    public ModelAndView fingById(@RequestParam(name = "id", required = true) String id) throws Exception{
        ModelAndView modelAndView  = new ModelAndView();
        Orders orders= ordersService.fingById(id);
        modelAndView.addObject("orders",orders);
        modelAndView.setViewName("orders-show");
        return modelAndView;
    }


}
