/*
 * Created by JFormDesigner on Sat Apr 30 14:50:22 CST 2022
 */

package com.guet.controller;

import com.guet.entity.Tea;
import com.guet.service.DiscountProductService;
import com.guet.service.Impl.DiscountProductServiceImpl;

import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * @author 1
 */
public class Discount extends JFrame {
    String[]coin={"编号","奶茶","折扣","单价","奶茶品种","剩余数量"};


    private DiscountProductService discountproductService=new DiscountProductServiceImpl();

    public Discount() {
        initComponents();
        //查询所有的产品
        List<Tea> list = discountproductService.selectDiscountProduct();
        //将所有的数据放到table中
        Object [][]obj=new Object[list.size()][coin.length];
        for(int i=0;i<list.size();i++){
            Tea tea=list.get(i);
            obj[i][0]=tea.getTeaId();
            obj[i][1]=tea.getTeaName();
            obj[i][2]=tea.getTeaDiscount();
            obj[i][3]=tea.getTeaPrice();
            obj[i][4]=tea.getTeaCategory();
            obj[i][5]=tea.getTeaAmount();
        }
        //obj是放到表单里的二维数组，coin是充当表头
        DefaultTableModel defaultTableModel = new DefaultTableModel(obj, coin);
        searchDiscount.setModel(defaultTableModel);
        searchDiscount.invalidate();

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        scrollPane1 = new JScrollPane();
        searchDiscount = new JTable();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(searchDiscount);
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(0, 0, 480, 395);

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
    private JTable searchDiscount;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
