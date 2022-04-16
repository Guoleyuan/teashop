/*
 * Created by JFormDesigner on Thu Apr 14 20:03:26 CST 2022
 */
package com.guet.controller;
import java.awt.event.*;
import com.guet.entity.Tea;
import com.guet.service.Impl.ProductServiceImpl;
import com.guet.service.ProductService;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

/**
 * @author 1
 */
public class Index extends JFrame {

    String[]coin={"编号","奶茶","折扣","单价","奶茶品种","剩余数量"};
    DefaultTableModel dmt= new DefaultTableModel(null,coin);

    private ProductService productService=new ProductServiceImpl();


    public Index() {
        initComponents();
    }

    /**
     * 点击查询按钮  查出所有的数据
     * @param e
     */
    private void searchAllActionPerformed(ActionEvent e) {

        //清空数据
        dmt.setRowCount(0);

        // TODO add your code here
        //查询所有的产品
        List<Tea> list = productService.selectAllProduct();
        //把查到的数据放入到table中

        Object[][] obj = new Object[list.size()][coin.length];
        for (int i = 0; i < list.size(); i++) {
            // dmt=new DefaultTableModel(obj,coin);
            Tea tea = list.get(i);
            obj[i][0]=tea.getTeaId();
            obj[i][1]=tea.getTeaName();
            obj[i][2]=tea.getTeaDiscount();
            obj[i][3]=tea.getTeaPrice();
            obj[i][4]=tea.getTeaCategory();
            obj[i][5]=tea.getTeaAmount();
            dmt.addRow(obj[i]);
        }
        table.setModel(dmt);
        table.invalidate();
    }

    /**
     * 按照关键字查询文本框的获得焦点的事件，把文本框的内容删除
     * @param e
     */
    private void textField1FocusGained(FocusEvent e) {
        // TODO add your code here
    }

    /**
     * 按照关键字查询文本框的失去焦点的事件，把文本框的内容删除
     * @param e
     */
    private void textField1FocusLost(FocusEvent e) {
        // TODO add your code here
    }

    /**
     * 输入关键字搜索相关内容
     * 例如：搜索奶茶要搜索到所有带奶茶的产品
     * @param e
     */
    private void searchSomeActionPerformed(ActionEvent e) {
        // TODO add your code here
        //清空数据
        dmt.setRowCount(0);
        // TODO add your code here
        //查询所有的产品
        List<Tea> list = productService.selectSomeProducts(textField1.getText());
        //把查到的数据放入到table中

        Object[][] obj = new Object[list.size()][coin.length];
        for (int i = 0; i < list.size(); i++) {
            // dmt=new DefaultTableModel(obj,coin);
            Tea tea = list.get(i);
            obj[i][0]=tea.getTeaId();
            obj[i][1]=tea.getTeaName();
            obj[i][2]=tea.getTeaDiscount();
            obj[i][3]=tea.getTeaPrice();
            obj[i][4]=tea.getTeaCategory();
            obj[i][5]=tea.getTeaAmount();
            dmt.addRow(obj[i]);
        }
        table.setModel(dmt);
        table.invalidate();
    }

    /**
     * 点击添加之后的功能
     * @param e
     */
    private void addProductActionPerformed(ActionEvent e) {
        // TODO add your code here
        new AddProduct();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();
        scrollPane1 = new JScrollPane();
        table = new JTable();
        searchAll = new JButton();
        textField1 = new JTextField();
        searchSome = new JButton();
        addProduct = new JButton();

        //======== this ========
        setIconImage(new ImageIcon(getClass().getResource("/imgs/logo.jpg")).getImage());
        setTitle("\u871c\u96ea\u51b0\u57ce");
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("text");
        label1.setIcon(new ImageIcon(getClass().getResource("/imgs/index.jpg")));
        contentPane.add(label1);
        label1.setBounds(-5, -95, 335, 910);

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(table);
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(330, 0, 575, 765);

        //---- searchAll ----
        searchAll.setText("\u67e5\u8be2\u5168\u90e8");
        searchAll.addActionListener(e -> searchAllActionPerformed(e));
        contentPane.add(searchAll);
        searchAll.setBounds(950, 30, 95, 40);

        //---- textField1 ----
        textField1.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                textField1FocusGained(e);
            }
            @Override
            public void focusLost(FocusEvent e) {
                textField1FocusLost(e);
            }
        });
        contentPane.add(textField1);
        textField1.setBounds(1150, 30, 155, 40);

        //---- searchSome ----
        searchSome.setText("\u67e5\u8be2");
        searchSome.addActionListener(e -> searchSomeActionPerformed(e));
        contentPane.add(searchSome);
        searchSome.setBounds(1320, 30, 95, 40);

        //---- addProduct ----
        addProduct.setText("\u6dfb\u52a0");
        addProduct.addActionListener(e -> addProductActionPerformed(e));
        contentPane.add(addProduct);
        addProduct.setBounds(950, 140, 95, 40);

        contentPane.setPreferredSize(new Dimension(1490, 795));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
    }


    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JScrollPane scrollPane1;
    private JTable table;
    private JButton searchAll;
    private JTextField textField1;
    private JButton searchSome;
    private JButton addProduct;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
    public static void main(String[] args) {
        Index index = new Index();

    }


}
