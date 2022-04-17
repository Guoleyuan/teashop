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
import java.util.ArrayList;
import java.util.List;

/**
 * @author 1
 */
public class Index extends JFrame {

    String[]coin;
    DefaultTableModel dmt;
    //添加到购物车使用的list
    List<Tea> shopCardList = new ArrayList<>();

    private ProductService productService=new ProductServiceImpl();


    public Index() {
        initComponents();
    }

    /**
     * 点击查询按钮  查出所有的数据
     * @param e
     */
    private void searchAllActionPerformed(ActionEvent e) {

        coin= new String[]{"编号", "奶茶", "折扣", "单价（元）", "奶茶品种", "剩余数量（杯）"};
        dmt= new DefaultTableModel(null,coin);
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
        coin= new String[]{"编号", "奶茶", "折扣", "单价（元）", "奶茶品种", "剩余数量（杯）"};
        dmt= new DefaultTableModel(null,coin);
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

    /**
     * 点击加入购物车之后  把数据放入到欲购买表格中，购物车功能
     * @param e
     */
    private void addShopCartActionPerformed(ActionEvent e) {
        // TODO add your code here
        int index1 = table.getSelectedRow();//获取选中行
        Tea tea = new Tea();
        tea.setTeaName((String) table.getValueAt(index1,1));
        tea.setTeaDiscount((Double) table.getValueAt(index1,2));
        tea.setTeaPrice((Double) table.getValueAt(index1,3));
        // System.out.println(tea);
        shopCardList.add(tea);

        //把list数据放入到preOrderTable中
        coin= new String[]{"产品名称","折扣", "单价(元)"};

        dmt= new DefaultTableModel(null,coin);
        //清空数据
        dmt.setRowCount(0);
        Double price=0.00;
        Object[][] obj = new Object[shopCardList.size()][coin.length];
        for (int i = 0; i < shopCardList.size(); i++) {
            // dmt=new DefaultTableModel(obj,coin);
            Tea tea1 = shopCardList.get(i);
            obj[i][0]=tea1.getTeaName();
            obj[i][1]= tea1.getTeaDiscount();
            obj[i][2]=tea1.getTeaPrice();
            dmt.addRow(obj[i]);
            Double teaDiscount = tea1.getTeaDiscount();
            double teaPrice = tea1.getTeaPrice();
            price+=teaPrice*teaDiscount;
            label3.setText(String.valueOf(price).substring(0,4));
            label3.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 28));
            label3.setForeground(Color.red);

        }
        preOrderTable.setModel(dmt);
        preOrderTable.invalidate();
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
        scrollPane2 = new JScrollPane();
        preOrderTable = new JTable();
        addShopCart = new JButton();
        label2 = new JLabel();
        label3 = new JLabel();

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
        scrollPane1.setBounds(330, 0, 575, 335);

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

        //======== scrollPane2 ========
        {
            scrollPane2.setViewportView(preOrderTable);
        }
        contentPane.add(scrollPane2);
        scrollPane2.setBounds(330, 400, 570, 190);

        //---- addShopCart ----
        addShopCart.setText("\u52a0\u5165\u8d2d\u7269\u8f66");
        addShopCart.addActionListener(e -> addShopCartActionPerformed(e));
        contentPane.add(addShopCart);
        addShopCart.setBounds(905, 280, 130, 55);

        //---- label2 ----
        label2.setText("\u603b\u4ef7\u94b1\uff1a");
        label2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 28));
        label2.setForeground(Color.red);
        contentPane.add(label2);
        label2.setBounds(605, 600, 120, 40);

        //---- label3 ----
        label3.setText(" ");
        contentPane.add(label3);
        label3.setBounds(720, 605, 170, 45);

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
    private JScrollPane scrollPane2;
    private JTable preOrderTable;
    private JButton addShopCart;
    private JLabel label2;
    private JLabel label3;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
    public static void main(String[] args) {
        Index index = new Index();

    }


}
