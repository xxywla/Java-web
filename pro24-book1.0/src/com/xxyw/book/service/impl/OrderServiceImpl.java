package com.xxyw.book.service.impl;

import com.xxyw.book.dao.CartItemDAO;
import com.xxyw.book.dao.OrderDAO;
import com.xxyw.book.dao.OrderItemDAO;
import com.xxyw.book.pojo.CartItem;
import com.xxyw.book.pojo.OrderBean;
import com.xxyw.book.pojo.OrderItem;
import com.xxyw.book.pojo.User;
import com.xxyw.book.service.OrderService;

import java.util.Map;

public class OrderServiceImpl implements OrderService {

    private OrderDAO orderDAO;
    private OrderItemDAO orderItemDAO;
    private CartItemDAO cartItemDAO;

    @Override
    public void addOrderBean(OrderBean orderBean) {

        // 1. 把订单 添加到数据库
        orderDAO.addOrderBean(orderBean);

        // 2. 把订单项 添加到数据库
        // 订单项根据 当前用户 的 购物车项 获取
        User user = orderBean.getOrderUser();
        Map<Integer, CartItem> cartItemMap = user.getCart().getCartItemMap();
        for (CartItem value : cartItemMap.values()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setBook(value.getBook());
            orderItem.setBuyCount(value.getBuyCount());
            orderItem.setOrderBean(orderBean);
            orderItemDAO.addOrderItem(orderItem);
        }

        // 3. 把 购物车项 清空
        for (CartItem value : cartItemMap.values()) {
            cartItemDAO.delete(value);
        }
    }
}
