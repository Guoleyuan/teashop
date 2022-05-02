package com.guet.service.Impl;

import com.guet.dao.DiscountProductDao;
import com.guet.dao.Impl.DiscountProductDaoImpl;
import com.guet.entity.Tea;
import com.guet.service.DiscountProductService;

import java.util.List;

public class DiscountProductServiceImpl implements DiscountProductService {
    private DiscountProductDao productDao=new DiscountProductDaoImpl();

    @Override
    public List<Tea> selectDiscountProduct() {
        List<Tea> list = productDao.selectDiscountProducts();
        return list;
    }
    @Override
    public int changeDiscount(Tea tea) {
        int i= productDao.changeDiscount(tea);
        if(i==1){
            return 1;
        }else {
            return 0;
        }

    }

    @Override
    public List<Tea> activeDiscount() {
        List<Tea> list=productDao.activeDiscount();
        return list;
    }
}
