package com.guet.dao.Impl;

import com.guet.dao.DiscountProductDao;
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

public class DiscountProductDaoImpl implements DiscountProductDao {
    Connection conn;
    PreparedStatement psm;
    ResultSet rs;
    /**
     * 查询数据中给打折商品的信息
     * @return  返回一个list 里面存储了tea表中的打折商品信息
     */
    @Override
    public List<Tea> selectDiscountProducts() {
        List<Tea> list = new ArrayList<>();
        try {
            conn = ConnUtil.getConn();
            String sql="SELECT tea_id,tea_name,tea_discount,tea_price,tea_category,tea_amount " + "FROM tea WHERE tea_discount<1" ;
            psm=conn.prepareStatement(sql);
            rs=psm.executeQuery();
            while (rs.next()){
                Tea tea=new Tea();
                tea.setTeaId(rs.getInt("tea_id"));
                tea.setTeaName(rs.getString("tea_name"));
                tea.setTeaAmount(rs.getInt("tea_amount"));
                tea.setTeaPrice(rs.getFloat("tea_price"));
                tea.setTeaCategory(rs.getString("tea_category"));
                tea.setTeaDiscount(rs.getFloat("tea_discount"));
                list.add(tea);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  list;
    }

    /**
     * 修改数据中给打折商品的信息
     * @return  返回一个list 里面存储了tea表中的打折商品信息
     */
    @Override
    public int changeDiscount(Tea tea) {
        try {
            conn = ConnectionHandler.getConnection();
            String sql="update tea set tea_price="+tea.getTeaPrice()+",tea_amount="+tea.getTeaAmount()
                    +",tea_name='"+tea.getTeaName()+"',tea_discount="+tea.getTeaDiscount()+",tea_category='"+tea.getTeaCategory()
                    +"'WHERE tea_id="+tea.getTeaId();
            psm=conn.prepareStatement(sql);
            psm.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1;
    }

    @Override
    public List<Tea> activeDiscount() {
        List<Tea> list=new ArrayList<>();
        try {
            conn=ConnUtil.getConn();
            String sql="SELECT tea_name,tea_discount " + "FROM tea WHERE tea_discount<1";
            psm=conn.prepareStatement(sql);
            rs= psm.executeQuery();
            while(rs.next()){
                Tea tea=new Tea();
                tea.setTeaName(rs.getString("tea_name"));
                tea.setTeaDiscount(rs.getFloat("tea_discount"));
                list.add(tea);
            }
            System.out.println(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    @Override
    public List<Order> exportData(String start, String end) {
        List<Order> list=new ArrayList<>();
        try {
            conn=ConnectionHandler.getConnection();
            String sql="SELECT *" +
                    "FROM tea_order " +
                    "WHERE (order_status=1 and order_Time BETWEEN '"+start+"' AND '"+end+"')";
            psm= conn.prepareStatement(sql);
            rs= psm.executeQuery();
            while (rs.next()){
                Order order=new Order();
                order.setOrderNumber(rs.getString("order_number"));
                order.setOrderPrice(rs.getFloat("order_price"));
                order.setOrderName(rs.getString("order_name"));
                order.setOrderStatus(rs.getInt("order_status"));
                order.setOrderTime(rs.getTimestamp("order_Time"));
                order.setOrderId(rs.getInt("order_id"));
                order.setMchId(rs.getInt("mch_id"));
                order.setTransactionId(rs.getString("transaction_id"));
                list.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public static void main(String[] args) {
        DiscountProductDaoImpl discountProductDao=new DiscountProductDaoImpl();
        discountProductDao.activeDiscount();
    }
}
