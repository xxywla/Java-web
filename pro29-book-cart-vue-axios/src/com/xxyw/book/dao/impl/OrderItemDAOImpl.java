package com.xxyw.book.dao.impl;

import com.xxyw.book.dao.OrderItemDAO;
import com.xxyw.book.pojo.OrderItem;
import com.xxyw.myssm.basedao.BaseDAO;

public class OrderItemDAOImpl extends BaseDAO<OrderItem> implements OrderItemDAO {
    @Override
    public void addOrderItem(OrderItem orderItem) {
        executeUpdate("insert into t_order_item values (0,?,?,?)", orderItem.getBook().getId(), orderItem.getBuyCount(), orderItem.getOrderBean().getId());
    }
}
