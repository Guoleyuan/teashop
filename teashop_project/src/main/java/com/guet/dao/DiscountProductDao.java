package com.guet.dao;

import com.guet.entity.Order;
import com.guet.entity.Tea;

import java.util.List;

public interface DiscountProductDao {
    List<Tea> selectDiscountProducts();
    int changeDiscount(Tea tea);
    List<Tea> activeDiscount();
    List<Order> exportData(String start, String end);
}
