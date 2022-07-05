package com.xxyw.book.dao.impl;

import com.xxyw.book.dao.OrderDAO;
import com.xxyw.book.pojo.OrderBean;
import com.xxyw.book.pojo.User;
import com.xxyw.myssm.basedao.BaseDAO;

import java.math.BigDecimal;
import java.util.List;

public class OrderDAOImpl extends BaseDAO<OrderBean> implements OrderDAO {
    @Override
    public void addOrderBean(OrderBean orderBean) {
        int orderBeanId = executeUpdate("insert into t_order values (0,?,?,?,?,?)", orderBean.getOrderNo(), orderBean.getOrderDate(), orderBean.getOrderUser().getId(), orderBean.getOrderMoney(), orderBean.getOrderStatus());
        orderBean.setId(orderBeanId);
    }

    @Override
    public List<OrderBean> getOrderList(User user) {
        return executeQuery("select * from t_order where orderUser = ? ", user.getId());
    }

    @Override
    public Integer getTotalBookCount(OrderBean orderBean) {
        String sql = "select sum(t3.buyCount) from " +
                "(select t2.buyCount, t2.orderBean from t_order t1 inner join t_order_item t2 on t1.id = t2.orderBean) t3 " +
                "where t3.orderBean = ? group by orderBean";
        return ((BigDecimal) executeComplexQuery(sql, orderBean.getId())[0]).intValue();
    }
}
