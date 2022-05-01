/*
 * Created by JFormDesigner on Sun May 01 16:15:53 CST 2022
 */

package com.guet.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.guet.entity.Tea;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * @author 1
 */

public class OrderDetail extends JFrame {

    String value;


    public OrderDetail(String value) {
        this.value=value;
        System.out.println(this.value);
        initComponents();
    }


    private void finishOrderButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        scrollPane1 = new JScrollPane();
        table1 = new JTable();

        //======== this ========
        setTitle("\u871c\u96ea\u51b0\u57ce");
        setIconImage(new ImageIcon(getClass().getResource("/imgs/logo.jpg")).getImage());
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(table1);
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(155, 55, 340, 330);

        contentPane.setPreferredSize(new Dimension(690, 495));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents


        List<String> list = JSONObject.parseArray(value, String.class);
        Map<String, Integer> map = new HashMap<>();
        for (String s : list) {
            if (map.containsKey(s)){
                Integer integer = map.get(s);
                map.put(s,++integer);
            }else {
                map.put(s,1);
            }
        }
        map.forEach((s, integer) -> System.out.println(s+":"+integer));

        //把list数据放入到preOrderTable中
        String [] coin= new String[]{"产品名称","数量"};

        DefaultTableModel dmt= new DefaultTableModel(null,coin);
        //清空数据
        dmt.setRowCount(0);
        Object[][] obj = new Object[map.size()][coin.length];
        Set<String> strings = map.keySet();
        Iterator it = strings.iterator();
        int i=0;
        while(it.hasNext()){
            //判断是否有下一个
            obj[i][0]=it.next();
            obj[i][1]=map.get(obj[i][0]);
            dmt.addRow(obj[i]);
            i++;
        }

        table1.setModel(dmt);
        table1.invalidate();






        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JScrollPane scrollPane1;
    private JTable table1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

}
