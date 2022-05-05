package com.guet.service.Impl;

import com.guet.dao.Impl.OrderDaoImpl;
import com.guet.dao.Impl.ProductDaoImpl;
import com.guet.dao.OrderDao;
import com.guet.dao.ProductDao;
import com.guet.entity.Order;
import com.guet.entity.Tea;
import com.guet.entity.Goods;
import com.guet.print.Printer;
import com.guet.print.SalesTicket;
import com.guet.service.OrderService;
import com.guet.util.ConnectionHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao=new OrderDaoImpl();
    private ProductDao productDao=new ProductDaoImpl();

    @Override
    public int insertOrder(Order order) throws SQLException {
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

    @Override
    public void shopCardPay(Order order, List<Tea> shopCardList) {
        Connection connection=null;
        try {
            connection= ConnectionHandler.getConnection();
            // System.out.println(connection);

            connection.setAutoCommit(false);


            orderDao.insertOrder(order);

            //更新商品表，把数量剪掉对应的数目
            Float price=0.00F;
            for (Tea tea : shopCardList) {
                String teaName = tea.getTeaName();
                price += tea.getTeaPrice();
                productDao.updateProductAmount(teaName);
            }

            connection.commit();


            //打印小票
            List<Goods> goods = new ArrayList<Goods>();
            HashMap<String,Float> map= new HashMap<>();//这里只能string，Integer
            for(Tea tea:shopCardList){
                //判断是否有一样的商品，有的话加1，没有的话加进去，并设为1
                if(map.containsKey(tea.getTeaName())){
                    Float i=map.get(tea.getTeaName());
                    System.out.println(map.get(tea.getTeaName()));
                    map.put(tea.getTeaName(),++i);
                }else {
                    map.put(tea.getTeaName(), 1F);
                }
            }
            for (String s : map.keySet()) {
                goods.add(new Goods(s,
                        String.valueOf(productDao.searchPriceByName(s)),
                        String.valueOf(map.get(s)),
                        String.valueOf(productDao.searchPriceByName(s)*map.get(s)),
                        productDao.searchCountByName(s)==1? "暂无折扣":String.valueOf(productDao.searchCountByName(s))
                        ));
            }
            int size = goods.size();
            SalesTicket salesTicket = new SalesTicket(goods, order.getOrderNumber(), String.valueOf(size), String.valueOf(price), String.valueOf(order.getOrderPrice()), "0", new Date());
            Printer printer = new Printer(salesTicket);
            printer.printer();


        } catch (Exception e) {
            e.printStackTrace();
            try {
                connection.rollback();
                System.out.println("事务回滚了");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }finally {
                ConnectionHandler.closeConnection();
            }
        }


    }


}
