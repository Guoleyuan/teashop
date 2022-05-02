/*
 * Created by JFormDesigner on Sat Apr 30 15:06:38 CST 2022
 */

package com.guet.controller;

import java.beans.*;
import com.guet.entity.Tea;
import com.guet.service.DiscountProductService;
import com.guet.service.Impl.DiscountProductServiceImpl;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * @author 1
 */
public class ChangeDiscount extends JFrame {
    Tea tea;
    private DiscountProductService discountproductService=new DiscountProductServiceImpl();
    public ChangeDiscount(Tea tea) {
        this.tea=tea;
        initComponents();
    }
    /**
     * 修改奶茶信息到数据库中
     * @param e
     */
    private void changeActionPerformed(ActionEvent e) {
        // TODO add your code here
        int search=JOptionPane.showConfirmDialog(null,"是否修改商品信息？","修改提示",JOptionPane.YES_NO_OPTION);
        if(search==JOptionPane.YES_OPTION){
            tea.setTeaName(textField1.getText());
            tea.setTeaPrice(Float.parseFloat(textField3.getText()));
            tea.setTeaCategory(textField4.getText());
            tea.setTeaDiscount(Float.parseFloat(textField2.getText()));
            int i=discountproductService.changeDiscount(tea);
            if(i==1){
                JOptionPane.showMessageDialog(null,"修改成功");
            }else {
                JOptionPane.showMessageDialog(null,"修改失败");
            }
        }else{

        }

    }
    /**
     * 重置功能
     */

    private void resetActionPerformed(ActionEvent e) {
        // TODO add your code here
        textField1.setText("");
        textField2.setText("");
        textField3.setText("");
        textField4.setText("");
        textField5.setText("");
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();
        textField1 = new JTextField();
        label2 = new JLabel();
        textField2 = new JTextField();
        label3 = new JLabel();
        textField3 = new JTextField();
        label4 = new JLabel();
        textField4 = new JTextField();
        label5 = new JLabel();
        textField5 = new JTextField();
        change = new JButton();
        reset = new JButton();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("\u540d\u79f0:");
        label1.setFont(label1.getFont().deriveFont(label1.getFont().getStyle() | Font.BOLD, label1.getFont().getSize() + 6f));
        contentPane.add(label1);
        label1.setBounds(95, 75, 50, 30);
        contentPane.add(textField1);
        textField1.setBounds(155, 75, 140, 35);

        //---- label2 ----
        label2.setText("\u6298\u6263\uff1a");
        label2.setFont(label2.getFont().deriveFont(label2.getFont().getStyle() | Font.BOLD, label2.getFont().getSize() + 6f));
        contentPane.add(label2);
        label2.setBounds(new Rectangle(new Point(95, 125), label2.getPreferredSize()));
        contentPane.add(textField2);
        textField2.setBounds(155, 125, 140, 35);

        //---- label3 ----
        label3.setText("\u5355\u4ef7\uff1a");
        label3.setFont(label3.getFont().deriveFont(label3.getFont().getStyle() | Font.BOLD, label3.getFont().getSize() + 6f));
        contentPane.add(label3);
        label3.setBounds(new Rectangle(new Point(95, 170), label3.getPreferredSize()));
        contentPane.add(textField3);
        textField3.setBounds(155, 170, 140, 35);

        //---- label4 ----
        label4.setText("\u7c7b\u578b\uff1a");
        label4.setFont(label4.getFont().deriveFont(label4.getFont().getStyle() | Font.BOLD, label4.getFont().getSize() + 6f));
        contentPane.add(label4);
        label4.setBounds(new Rectangle(new Point(95, 230), label4.getPreferredSize()));
        contentPane.add(textField4);
        textField4.setBounds(155, 225, 140, 35);

        //---- label5 ----
        label5.setText("\u6570\u91cf\uff1a");
        label5.setFont(label5.getFont().deriveFont(label5.getFont().getStyle() | Font.BOLD, label5.getFont().getSize() + 6f));
        contentPane.add(label5);
        label5.setBounds(new Rectangle(new Point(95, 285), label5.getPreferredSize()));
        contentPane.add(textField5);
        textField5.setBounds(155, 285, 140, 35);

        //---- change ----
        change.setText("\u4fee\u6539");
        change.setFont(change.getFont().deriveFont(change.getFont().getStyle() | Font.BOLD, change.getFont().getSize() + 6f));
        change.addActionListener(e -> changeActionPerformed(e));
        contentPane.add(change);
        change.setBounds(415, 130, 90, 45);

        //---- reset ----
        reset.setText("\u91cd\u7f6e");
        reset.setFont(reset.getFont().deriveFont(reset.getFont().getStyle() | Font.BOLD, reset.getFont().getSize() + 6f));
        reset.addActionListener(e -> resetActionPerformed(e));
        contentPane.add(reset);
        reset.setBounds(415, 225, 90, 45);

        contentPane.setPreferredSize(new Dimension(600, 460));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
        textField1.setText(tea.getTeaName());
        textField2.setText(String.valueOf(tea.getTeaDiscount()));
        textField3.setText(String.valueOf(tea.getTeaPrice()));
        textField4.setText(tea.getTeaCategory());
        textField5.setText(String.valueOf(tea.getTeaAmount()));
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
    private JLabel label4;
    private JTextField textField4;
    private JLabel label5;
    private JTextField textField5;
    private JButton change;
    private JButton reset;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
