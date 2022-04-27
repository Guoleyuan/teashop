package com.guet.dao.Impl;

import com.guet.dao.UserDao;
import com.guet.entity.User;
import com.guet.util.ConnUtil;
import com.guet.util.ConnectionHandler;
import lombok.Data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Data
public class UserDaoImpl implements UserDao {

    Connection conn;
    PreparedStatement psm;
    ResultSet rs;

    /**
     * 从数据库获取用户名和密码
     * @return
     */
    @Override
    public User getUserMessage() {
        User user=null;
        try {
            conn = ConnectionHandler.getConnection();
            String sql="SELECT user_name,user_password FROM user";
            psm=conn.prepareStatement(sql);
            rs=psm.executeQuery();
            while (rs.next()){
                user = new User();
                user.setUserName(rs.getString("user_name"));
                user.setUserPassword(rs.getString("user_password"));
                System.out.println(user.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user ;
    }

    public static void main(String[] args) {
        UserDaoImpl userDao = new UserDaoImpl();
        userDao.getUserMessage();
    }
}
