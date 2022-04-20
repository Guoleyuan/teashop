package com.guet.dao;

import com.guet.entity.Order;

public interface OrderDao {
    //向数据库中插入订单表
    int insertOrder(Order order);
}
