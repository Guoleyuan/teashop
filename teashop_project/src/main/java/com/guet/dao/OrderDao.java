package com.guet.dao;

import com.guet.entity.Order;

import java.sql.SQLException;
import java.util.List;

public interface OrderDao {
    //向数据库中插入订单表
    int insertOrder(Order order) throws SQLException;

    List<Order> queryOrder();

    //当订单做好之后，把订单的order_status修改为1，成为历史订单
    int updateOrderStatus(String orderNumber);
}
