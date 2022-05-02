/*
 * Created by JFormDesigner on Sun May 01 22:57:24 CST 2022
 */

package com.guet.controller;

import com.alibaba.fastjson.JSONObject;
import com.guet.entity.Order;
import com.guet.enums.TeaStatus;

import java.awt.*;
import java.util.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;


/**
 * @author 1
 */
public class SreachOrder extends JFrame {
    Order order;
    String []coin={"商品名称","数量"};
    public SreachOrder(Order order) {
        this.order=order;
        initComponents();
        //String orderstring=order.getOrderName().substring(1,order.getOrderName().length()-1);//不带[]的包含商品名称的字符串
        //System.out.println(orderstring);
        //String []str=orderstring.split(",");//将商品名称分隔开来

        //Object [][]obj=new Object[str.length][coin.length];
        //for(int i=0;i< str.length;i++){
        //    obj[i][0]=str[i].substring(1,str[i].length()-1);
        //}
        //DefaultTableModel defaultTableModel=new DefaultTableModel(obj, coin);
        //table1.setModel(defaultTableModel);
        //table1.invalidate();
        List<String> list= JSONObject.parseArray(order.getOrderName(),String.class);//将JSON数据转换成数组字符串
        //按照我的理解就是将大段的字符串按照逗号分段，并放在一个集合里面
        HashMap<String,Integer> map= new HashMap<>();//这里只能string，Integer
        for(String s:list){
            //判断是否有一样的商品，有的话加1，没有的话加进去，并设为1
            if(map.containsKey(s)){
                int i=map.get(s);
                System.out.println(map.get(s));
                map.put(s,++i);
            }else {
                map.put(s,1);
            }
        }
        Object[][]obj=new Object[map.size()][coin.length];
        Iterator key= map.keySet().iterator();
        int i=0;
        while (key.hasNext()){
            obj[i][0]=key.next();
            obj[i][1]=map.get(obj[i][0]);
            i++;
        }
        DefaultTableModel defaultTableModel=new DefaultTableModel(obj,coin);
        table1.setModel(defaultTableModel);
        table1.invalidate();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();
        textField1 = new JTextField();
        label2 = new JLabel();
        textField2 = new JTextField();
        label3 = new JLabel();
        textField3 = new JTextField();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        label4 = new JLabel();
        textField4 = new JTextField();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("\u7b49\u5f85\u53f7\uff1a");
        label1.setFont(label1.getFont().deriveFont(label1.getFont().getStyle() | Font.BOLD, label1.getFont().getSize() + 6f));
        contentPane.add(label1);
        label1.setBounds(new Rectangle(new Point(85, 60), label1.getPreferredSize()));

        //---- textField1 ----
        textField1.setBorder(null);
        contentPane.add(textField1);
        textField1.setBounds(155, 65, 120, textField1.getPreferredSize().height);

        //---- label2 ----
        label2.setText("\u603b\u4ef7\u94b1\uff1a");
        label2.setFont(label2.getFont().deriveFont(label2.getFont().getStyle() | Font.BOLD, label2.getFont().getSize() + 6f));
        contentPane.add(label2);
        label2.setBounds(new Rectangle(new Point(85, 100), label2.getPreferredSize()));

        //---- textField2 ----
        textField2.setBorder(null);
        contentPane.add(textField2);
        textField2.setBounds(155, 105, 120, textField2.getPreferredSize().height);

        //---- label3 ----
        label3.setText("\u8ba2\u5355\u72b6\u6001\uff1a");
        label3.setFont(label3.getFont().deriveFont(label3.getFont().getStyle() | Font.BOLD, label3.getFont().getSize() + 6f));
        contentPane.add(label3);
        label3.setBounds(new Rectangle(new Point(65, 140), label3.getPreferredSize()));

        //---- textField3 ----
        textField3.setBorder(null);
        contentPane.add(textField3);
        textField3.setBounds(155, 145, 120, textField3.getPreferredSize().height);

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(table1);
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(80, 180, 285, 215);

        //---- label4 ----
        label4.setText("\u8ba2\u5355\u53f7\uff1a");
        label4.setFont(label4.getFont().deriveFont(label4.getFont().getStyle() | Font.BOLD, label4.getFont().getSize() + 6f));
        contentPane.add(label4);
        label4.setBounds(new Rectangle(new Point(55, 430), label4.getPreferredSize()));

        //---- textField4 ----
        textField4.setBorder(null);
        contentPane.add(textField4);
        textField4.setBounds(125, 435, 260, textField4.getPreferredSize().height);

        contentPane.setPreferredSize(new Dimension(430, 540));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
        textField1.setText(String.valueOf(order.getOrderId()));
        textField2.setText(String.valueOf(order.getOrderPrice()));
        textField3.setText(String.valueOf(TeaStatus.WAIT.getType()));
        textField4.setText(String.valueOf(order.getOrderNumber()));
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JTextField textField1;
    private JLabel label2;
    private JTextField textField2;
    private JLabel label3;
    private JTextField textField3;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JLabel label4;
    private JTextField textField4;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

}
