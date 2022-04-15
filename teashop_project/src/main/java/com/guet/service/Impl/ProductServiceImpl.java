package com.guet.service.Impl;

import com.guet.dao.Impl.ProductDaoImpl;
import com.guet.dao.ProductDao;
import com.guet.entity.Tea;
import com.guet.service.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {

    private ProductDao productDao=new ProductDaoImpl();

    /**
     * 查询所有商品
     * @return
     */
    @Override
    public List<Tea> selectAllProduct() {
        List<Tea> list = productDao.selectAllProducts();
        return list;
    }
}
