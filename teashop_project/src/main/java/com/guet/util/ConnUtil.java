package com.guet.util;
import java.sql.Connection;
import java.sql.DriverManager;


public class ConnUtil {
    static String url="jdbc:mysql://localhost:3306/teashop?serverTimezone=GMT%2B8&characterEncoding=utf-8";
    static String username="root";
    static String password="123456";

    static Connection conn;

    public static Connection getConn() throws Exception {
        conn = DriverManager.getConnection(url, username, password);
        if (conn!=null){
            return conn;
        }else {
            throw new Exception("数据库登录信息异常");
        }
    }

    // public static void main(String[] args) throws Exception {
    //     Connection conn = getConn();
    //     System.out.println(conn);
    // }
}
