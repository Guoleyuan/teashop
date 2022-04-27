package com.guet.dao.Impl;

import com.guet.dao.OrderDao;
import com.guet.entity.Order;
import com.guet.entity.Tea;
import com.guet.util.ConnUtil;
import com.guet.util.ConnectionHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao {

    Connection conn;
    PreparedStatement psm;
    ResultSet rs;


    /**
     * mysql实现订单表的查询
     * @param order
     * @return
     */
    @Override
    public int insertOrder(Order order) throws SQLException {
            conn = ConnectionHandler.getConnection();
            System.out.println(conn);
            //"INSERT INTO tea(tea_name,tea_amount,tea_price,tea_category,tea_discount)" +
            //                     "values(?,?,?,?,?)"
            String sql="INSERT INTO tea_order(order_number,order_price,order_name,order_creatTime,order_status)" +
                    "values(?,?,?,?,?)";
            psm=conn.prepareStatement(sql);
            psm.setString(1,order.getOrderNumber());
            psm.setFloat(2,order.getOrderPrice());
            psm.setString(3,order.getOrderName());
            psm.setString(4,order.getOrderCreatTime());
            psm.setInt(5,order.getOrderStatus());
            psm.executeUpdate();
        return 1;
    }

    /**
     * 查询当前订单表，条件是查询所有orderStatus为0的
     * @return
     */
    @Override
    public List<Order> queryOrder() {
        List<Order> list = new ArrayList<>();
        try {
            conn=ConnectionHandler.getConnection();
            // SELECT * FROM tea_order WHERE order_status=0
            String sql="SELECT * FROM tea_order WHERE order_status= 0 ORDER BY order_creatTime ASC";
            psm=conn.prepareStatement(sql);
            rs=psm.executeQuery();
            while (rs.next()){
                Order order = new Order();
                order.setOrderNumber(rs.getString("order_number"));
                order.setOrderPrice(rs.getFloat("order_price"));
                order.setOrderName(rs.getString("order_name"));
                order.setOrderCreatTime(rs.getString("order_creatTime"));
                order.setOrderStatus(rs.getInt("order_status"));
                order.setOrderId(rs.getInt("order_id"));
                list.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 当订单做好之后，把订单的order_status修改为1，成为历史订单
     * @return
     */
    @Override
    public int updateOrderStatus(String orderNumber) {
        try {
            conn=ConnectionHandler.getConnection();
            // UPDATE tea_order SET order_status=1  WHERE order_number=20220421215540000001
            String sql="UPDATE tea_order SET order_status=1  WHERE order_number=?";
            psm=conn.prepareStatement(sql);
            psm.setString(1,orderNumber);
            psm.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1;
    }

}
