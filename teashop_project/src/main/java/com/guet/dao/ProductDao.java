package com.guet.dao;

import com.guet.entity.Tea;

import java.util.List;

public interface ProductDao {
    List<Tea> selectAllProducts();
}