/*
 * Created by JFormDesigner on Sat Apr 16 11:27:19 CST 2022
 */

package com.guet.controller;

import com.guet.entity.Tea;
import com.guet.service.Impl.ProductServiceImpl;
import com.guet.service.ProductService;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * @author 1
 */
public class AddProduct extends JFrame {


    private ProductService productService=new ProductServiceImpl();


    public AddProduct() {
        initComponents();
    }

    /**
     * 重置功能
     * @param e
     */
    private void resetButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
        addTextField1.setText("");
        addTextField2.setText("");
        addTextField3.setText("");
        addTextField4.setText("");
        addTextField5.setText("");
    }

    /**
     * 折扣获得焦点事件
     * @param e
     */
    private void addTextField2FocusGained(FocusEvent e) {
        // TODO add your code here
        addTextField2.setText("");
    }

    /**
     * 添加奶茶品种到数据库中，并且显示在主页的table中
     * @param e
     */
    private void addButtonActionPerformed(ActionEvent e) {
        // TODO add your code here-
        //向数据库中添加数据
        if ("".equals(addTextField1.getText())){
            JOptionPane.showMessageDialog(null,"请输入奶茶名称");
        }else if ("".equals(addTextField2.getText())){
            JOptionPane.showMessageDialog(null,"请输入产品的折扣");
        }else if ("".equals(addTextField3.getText())){
            JOptionPane.showMessageDialog(null,"请输入产品的单价");
        }else if ("".equals(addTextField4.getText())){
            JOptionPane.showMessageDialog(null,"请输入产品的类型");
        }else if ("".equals(addTextField5.getText())){
            JOptionPane.showMessageDialog(null,"请输入产品的数量");
        }else {
            Tea tea = new Tea();
            tea.setTeaName(addTextField1.getText());
            tea.setTeaDiscount(Float.parseFloat(addTextField2.getText()));
            tea.setTeaPrice(Float.parseFloat(addTextField3.getText()));
            tea.setTeaCategory(addTextField4.getText());
            tea.setTeaAmount(Integer.parseInt(addTextField5.getText()));
            int i = productService.insertProduct(tea);
            if (i==1){
                JOptionPane.showMessageDialog(null,"添加成功");
            }else {
                JOptionPane.showMessageDialog(null,"添加失败");
            }
        }
        //更新数据到主页的table中
        

    }
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();
        addTextField1 = new JTextField();
        label2 = new JLabel();
        addTextField2 = new JTextField();
        label3 = new JLabel();
        addTextField3 = new JTextField();
        label4 = new JLabel();
        addTextField4 = new JTextField();
        label5 = new JLabel();
        addTextField5 = new JTextField();
        addButton = new JButton();
        resetButton = new JButton();

        //======== this ========
        setIconImage(new ImageIcon(getClass().getResource("/imgs/logo.jpg")).getImage());
        setTitle("\u6dfb\u52a0\u5976\u8336");
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("\u540d\u79f0\uff1a");
        label1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 18));
        contentPane.add(label1);
        label1.setBounds(60, 60, 60, 30);
        contentPane.add(addTextField1);
        addTextField1.setBounds(130, 60, 140, 35);

        //---- label2 ----
        label2.setText("\u6298\u6263\uff1a");
        label2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 18));
        contentPane.add(label2);
        label2.setBounds(60, 115, 60, 30);

        //---- addTextField2 ----
        addTextField2.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                addTextField2FocusGained(e);
            }
        });
        contentPane.add(addTextField2);
        addTextField2.setBounds(130, 115, 140, 35);

        //---- label3 ----
        label3.setText("\u5355\u4ef7\uff1a");
        label3.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 18));
        contentPane.add(label3);
        label3.setBounds(60, 190, 60, 30);
        contentPane.add(addTextField3);
        addTextField3.setBounds(130, 190, 140, 35);

        //---- label4 ----
        label4.setText("\u7c7b\u578b\uff1a");
        label4.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 18));
        contentPane.add(label4);
        label4.setBounds(60, 260, 60, 30);
        contentPane.add(addTextField4);
        addTextField4.setBounds(130, 260, 140, 35);

        //---- label5 ----
        label5.setText("\u6570\u91cf\uff1a");
        label5.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 18));
        contentPane.add(label5);
        label5.setBounds(60, 335, 60, 30);
        contentPane.add(addTextField5);
        addTextField5.setBounds(130, 335, 140, 35);

        //---- addButton ----
        addButton.setText("\u6dfb\u52a0");
        addButton.setFont(new Font("Microsoft YaHei UI", Font.BOLD | Font.ITALIC, 24));
        addButton.addActionListener(e -> addButtonActionPerformed(e));
        contentPane.add(addButton);
        addButton.setBounds(395, 95, 105, 65);

        //---- resetButton ----
        resetButton.setText("\u91cd\u7f6e");
        resetButton.setFont(new Font("Microsoft YaHei UI", Font.BOLD | Font.ITALIC, 24));
        resetButton.addActionListener(e -> resetButtonActionPerformed(e));
        contentPane.add(resetButton);
        resetButton.setBounds(395, 215, 105, 65);

        contentPane.setPreferredSize(new Dimension(580, 435));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents

        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JTextField addTextField1;
    private JLabel label2;
    private JTextField addTextField2;
    private JLabel label3;
    private JTextField addTextField3;
    private JLabel label4;
    private JTextField addTextField4;
    private JLabel label5;
    private JTextField addTextField5;
    private JButton addButton;
    private JButton resetButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    public static void main(String[] args) {
        new AddProduct();
    }
}