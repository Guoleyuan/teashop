package com.guet.print;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Test {
        public static void main(String[] args) {
            List<Goods> goods = new ArrayList<Goods>();
            goods.add(new Goods("J2EE", "11800", "1", "11800",""));
            goods.add(new Goods("大数据", "14800", "1", "14800",""));
            goods.add(new Goods("前端", "11800", "1", "11800",""));
            int size = goods.size();
            String s = Integer.toString(size);
            Date date = new Date();
            SalesTicket stk = new SalesTicket(goods, "201705230010", s, "38400", "38400", "0",date);
            Printer p = new Printer(stk);
            p.printer();
        }
}
