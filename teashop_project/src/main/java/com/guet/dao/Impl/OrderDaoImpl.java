package com.guet.dao.Impl;

import com.guet.dao.OrderDao;
import com.guet.entity.Order;
import com.guet.util.ConnUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderDaoImpl implements OrderDao {

    Connection conn;
    PreparedStatement psm;
    ResultSet rs;




    @Override
    public int insertOrder(Order order) {
        try {
            conn = ConnUtil.getConn();
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1;
    }


}
