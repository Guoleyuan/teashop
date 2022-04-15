package com.guet.dao.Impl;

import com.guet.dao.ProductDao;
import com.guet.entity.Tea;
import com.guet.util.ConnUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
            conn = ConnUtil.getConn();
            String sql="SELECT tea_id,tea_name,tea_discount,tea_price,tea_category,tea_amount FROM tea";
            psm=conn.prepareStatement(sql);
            rs=psm.executeQuery();
            while (rs.next()){
                Tea tea=new Tea();
                tea.setTeaId(rs.getInt("tea_id"));
                tea.setTeaName(rs.getString("tea_name"));
                tea.setTeaAmount(rs.getInt("tea_amount"));
                tea.setTeaPrice(rs.getInt("tea_price"));
                tea.setTeaCategory(rs.getString("tea_category"));
                tea.setTeaDiscount(rs.getDouble("tea_discount"));
                list.add(tea);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  list;
    }
}
