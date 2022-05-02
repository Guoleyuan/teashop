package com.guet.service;

import com.guet.entity.Tea;

import java.util.List;

public interface DiscountProductService {
    List<Tea> selectDiscountProduct();
    int changeDiscount(Tea tea);
    List<Tea> activeDiscount();
}
