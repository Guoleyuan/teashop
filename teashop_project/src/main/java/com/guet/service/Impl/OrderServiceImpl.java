package com.guet.service.Impl;

import com.guet.dao.Impl.OrderDaoImpl;
import com.guet.dao.OrderDao;
import com.guet.entity.Order;
import com.guet.service.OrderService;

import java.util.List;

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

    /**
     * 查询所有订单状态为0的订单
     * @return
     */
    @Override
    public List<Order> queryOrders() {
        List<Order> orders = orderDao.queryOrder();
        return orders;
    }

    /**
     * 将当前订单改为历史订单
     * @param orderNumber
     * @return
     */
    @Override
    public boolean updateOrderStatus(String orderNumber) {
        int i = orderDao.updateOrderStatus(orderNumber);
        if (i==1){
            return true;
        }else {
            return false;
        }
    }

}
