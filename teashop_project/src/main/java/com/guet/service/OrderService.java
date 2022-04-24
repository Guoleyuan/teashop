package com.guet.service;

import com.guet.entity.Order;

import java.util.List;

public interface OrderService {

    int insertOrder(Order order);

    // 查询所有订单状态为0的订单
    List<Order> queryOrders();

    // 将当前订单改为历史订单
    boolean updateOrderStatus(String orderNumber);
}
