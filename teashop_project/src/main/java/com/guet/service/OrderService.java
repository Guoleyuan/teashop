package com.guet.service;

import com.guet.entity.Order;
import com.guet.entity.Tea;

import java.sql.SQLException;
import java.util.List;

public interface OrderService {

    int insertOrder(Order order) throws SQLException;

    // 查询所有订单状态为0的订单
    List<Order> queryOrders();

    // 将当前订单改为历史订单
    boolean updateOrderStatus(String orderNumber);


    void shopCardPay(Order order, List<Tea> shopCardList);

}
