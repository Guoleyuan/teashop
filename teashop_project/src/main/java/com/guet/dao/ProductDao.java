package com.guet.dao;

import com.guet.entity.Tea;

import java.sql.SQLException;
import java.util.List;

public interface ProductDao {
    List<Tea> selectAllProducts();

    List<Tea> selectSomeProducts(String str);

    int insertProduct(Tea tea);

    int updateProductAmount(String name) throws SQLException;

    boolean deleteProductById(int id);

    float searchPriceByName(String name);

    float searchCountByName(String name);

    int searchAmountByName(String name);
}
