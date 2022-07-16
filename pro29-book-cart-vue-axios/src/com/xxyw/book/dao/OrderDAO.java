package com.xxyw.book.dao;

import com.xxyw.book.pojo.OrderBean;
import com.xxyw.book.pojo.User;

import java.util.List;

public interface OrderDAO {
    void addOrderBean(OrderBean orderBean);

    // 获取指定用户的所有订单
    List<OrderBean> getOrderList(User user);

    // 获取指定订单的书本总个数
    Integer getTotalBookCount(OrderBean orderBean);
}
