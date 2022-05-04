package com.guet.service;

import com.guet.entity.Order;
import com.guet.entity.Tea;

import java.util.List;

public interface DiscountProductService {
    List<Tea> selectDiscountProduct();
    int changeDiscount(Tea tea);
    List<Tea> activeDiscount();
    List<Order> exportData(String start, String end);
}
