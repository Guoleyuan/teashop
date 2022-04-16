package com.guet.util;

public class Test {
    public static void main(String[] args) {
        String sql="SELECT tea_id,tea_name,tea_discount,tea_price,tea_category,tea_amount FROM tea WHERE " +
                "tea_name LIKE \"%\"?\"%\"  OR tea_category LIKE \"%\"?\"%\" ";
        System.out.println(sql);
        String sqla="INSERT INTO tea() values(?,?,?,?,?)";
        System.out.println(sqla);
    }
}
