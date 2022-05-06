/*
 * Created by JFormDesigner on Fri May 06 14:29:10 CST 2022
 */

package com.guet.controller;

import com.alibaba.fastjson.JSON;
import com.guet.entity.Order;
import com.guet.entity.Tea;
import com.guet.enums.TeaStatus;
import com.guet.service.DiscountProductService;
import com.guet.service.Impl.DiscountProductServiceImpl;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * @author 1
 */
public class History extends JFrame {
    private DiscountProductService discountproductService=new DiscountProductServiceImpl();
    String coin[]={"商品编号","订单总价钱","订单内容","下单时间","订单状态","顾客等待号","统一编号","流水编号"};
    public History() {
        initComponents();
        //查询到的所有历史订单
        List<Order> list=discountproductService.exportData();
        //将所有的数据放到historytable中
        Object [][]obj=new Object[list.size()][coin.length];
        for(int i=0;i<list.size();i++){
            Order order= list.get(i);
            obj[i][0]=order.getOrderNumber();
            obj[i][1]=order.getOrderPrice();
            //将所有的商品名称转换为JSON字符串
            String orderName = order.getOrderName();
            List<String> list1 = JSON.parseArray(orderName, String.class);
            Map<String, Integer> map = new HashMap<>();
            for (String s : list1) {
                if (map.containsKey(s)){
                    Integer integer = map.get(s);
                    map.put(s,++integer);
                }else {
                    map.put(s,1);
                }
            }
            String s = JSON.toJSONString(map);
            //放到那么一栏里
            obj[i][2]=s;
            obj[i][3]=String.valueOf(order.getOrderTime());
            //评判数据库中的历史订单中商品是否都会卖出，也可以用于检测程序中是否出现bug
            if(order.getOrderStatus()==1){
                obj[i][4]=TeaStatus.HISTORY.getType();
            }else{
                obj[i][4]=TeaStatus.WAIT.getType();//如果出现这个就是程序出了bug
            }
            obj[i][5]=order.getOrderId();
            obj[i][6]=order.getMchId();
            obj[i][7]=order.getTransactionId();
        }
        DefaultTableModel defaultTableModel = new DefaultTableModel(obj, coin);
        history.setModel(defaultTableModel);
        history.invalidate();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        scrollPane1 = new JScrollPane();
        history = new JTable();

        //======== this ========
        setIconImage(new ImageIcon(getClass().getResource("/imgs/logo.jpg")).getImage());
        setTitle("\u5386\u53f2\u8ba2\u5355");
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(history);
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(0, 0, 615, 410);

        {
            // compute preferred size
            Dimension preferredSize = new Dimension();
            for(int i = 0; i < contentPane.getComponentCount(); i++) {
                Rectangle bounds = contentPane.getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = contentPane.getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            contentPane.setMinimumSize(preferredSize);
            contentPane.setPreferredSize(preferredSize);
        }
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JScrollPane scrollPane1;
    private JTable history;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
