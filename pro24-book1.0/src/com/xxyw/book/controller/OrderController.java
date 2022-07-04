package com.xxyw.book.controller;

import com.xxyw.book.pojo.OrderBean;
import com.xxyw.book.pojo.User;
import com.xxyw.book.service.OrderService;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.UUID;

public class OrderController {

    private OrderService orderService;

    public String checkout(HttpSession session) {
        OrderBean orderBean = new OrderBean();
        Date date = new Date();
        orderBean.setOrderNo(UUID.randomUUID() + "_" + date);
        orderBean.setOrderDate(date);
        User user = (User) session.getAttribute("currUser");
        orderBean.setOrderUser(user);
        orderBean.setOrderMoney(user.getCart().getTotalMoney());
        orderBean.setOrderStatus(0);

        orderService.addOrderBean(orderBean);
        return "index";
    }
}
