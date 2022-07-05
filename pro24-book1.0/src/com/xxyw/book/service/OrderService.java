package com.xxyw.book.service;

import com.xxyw.book.pojo.OrderBean;
import com.xxyw.book.pojo.User;

import java.util.List;

public interface OrderService {
    void addOrderBean(OrderBean orderBean);

    // 获取指定用户的所有订单
    List<OrderBean> getOrderList(User user);
}
