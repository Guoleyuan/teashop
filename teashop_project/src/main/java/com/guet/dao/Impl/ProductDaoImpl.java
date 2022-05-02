package com.guet.dao.Impl;



import com.guet.dao.ProductDao;
import com.guet.entity.Tea;
import com.guet.util.ConnectionHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {

    Connection conn;
    PreparedStatement psm;
    ResultSet rs;


    /**
     * 查询数据中给所有商品的信息
     * @return  返回一个list 里面存储了tea表中的所有信息
     */
    @Override
    public List<Tea> selectAllProducts() {
        List<Tea> list = new ArrayList<>();
        try {
            conn = ConnectionHandler.getConnection();
            String sql="SELECT tea_id,tea_name,tea_discount,tea_price,tea_category,tea_amount FROM tea";
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
     * 查询数据中所有带关键字的商品的信息
     * @return  返回一个list 里面存储了tea表中符合条件的
     */
    @Override
    public List<Tea> selectSomeProducts(String str) {
        List<Tea> list = new ArrayList<>();
        try {
            conn = ConnectionHandler.getConnection();
            // SELECT * FROM tea WHERE tea_name LIKE '%波%' OR tea_category LIKE '%奶茶';
            String sql="SELECT tea_id,tea_name,tea_discount,tea_price,tea_category,tea_amount FROM tea WHERE " +
                    "tea_name LIKE \"%\"?\"%\"  OR tea_category LIKE \"%\"?\"%\" ";
            psm=conn.prepareStatement(sql);
            psm.setString(1,str);
            psm.setString(2,str);
            rs=psm.executeQuery();
            while (rs.next()){
                Tea tea = new Tea();
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
        return list;
    }

    @Override
    public int insertProduct(Tea tea) {
        try {
            conn = ConnectionHandler.getConnection();
            String sql="INSERT INTO tea(tea_name,tea_amount,tea_price,tea_category,tea_discount)" +
                    "values(?,?,?,?,?)";
            psm=conn.prepareStatement(sql);
            psm.setString(1,tea.getTeaName());
            psm.setInt(2,tea.getTeaAmount());
            psm.setDouble(3,tea.getTeaPrice());
            psm.setString(4,tea.getTeaCategory());
            psm.setDouble(5,tea.getTeaDiscount());
            psm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1;
    }

    /**
     * 结算之后把数据库中的产品数量-1
     * @param name 根据name定位
     */
    @Override
    public int updateProductAmount(String name) throws SQLException {
            conn=ConnectionHandler.getConnection();
            // System.out.println(conn);
            // UPDATE tea SET tea_price=tea_price-1
            // WHERE tea_name='美式'
            String sql="UPDATE tea SET tea_amount=tea_amount-1 WHERE tea_name=?";
            psm=conn.prepareStatement(sql);
            psm.setString(1,name);
            psm.executeUpdate();
        return 1;
    }

    /**
     * 通过id删除商品信息
     * @param id
     * @return
     */
    @Override
    public boolean deleteProductById(int id) {
        try {
            conn=ConnectionHandler.getConnection();
            // DELETE FROM tea WHERE tea_id=9
            String sql="DELETE FROM tea WHERE tea_id=?";
            psm=conn.prepareStatement(sql);
            psm.setInt(1,id);
            psm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }


}
