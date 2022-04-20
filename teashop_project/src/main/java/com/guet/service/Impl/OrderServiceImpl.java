package com.guet.service.Impl;

import com.guet.dao.Impl.OrderDaoImpl;
import com.guet.dao.OrderDao;
import com.guet.entity.Order;
import com.guet.service.OrderService;

public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao=new OrderDaoImpl();

    @Override
    public int insertOrder(Order order) {
        int i = orderDao.insertOrder(order);
        if (i==1){
            return 1;
        }else {
            return 0;
        }
    }
}
