package com.xxyw.book.dao.impl;

import com.xxyw.book.dao.OrderDAO;
import com.xxyw.book.pojo.OrderBean;
import com.xxyw.myssm.basedao.BaseDAO;

public class OrderDAOImpl extends BaseDAO<OrderBean> implements OrderDAO {
    @Override
    public void addOrderBean(OrderBean orderBean) {
        int orderBeanId = executeUpdate("insert into t_order values (0,?,?,?,?,?)", orderBean.getOrderNo(), orderBean.getOrderDate(), orderBean.getOrderUser().getId(), orderBean.getOrderMoney(), orderBean.getOrderStatus());
        orderBean.setId(orderBeanId);
    }
}
