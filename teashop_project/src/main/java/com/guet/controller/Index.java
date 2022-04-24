/*
 * Created by JFormDesigner on Thu Apr 14 20:03:26 CST 2022
 */
package com.guet.controller;

import com.alibaba.fastjson.JSON;
import com.guet.entity.Order;
import com.guet.entity.Tea;
import com.guet.enums.TeaStatus;
import com.guet.service.Impl.OrderServiceImpl;
import com.guet.service.Impl.ProductServiceImpl;
import com.guet.service.OrderService;
import com.guet.service.ProductService;
import com.guet.util.MyTable;
import com.guet.util.TimeNumberUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

/**
 * @author 1
 */
public class Index extends JFrame {

    String[]coin;
    DefaultTableModel dmt;
    //添加到购物车使用的list
    List<Tea> shopCardList= new ArrayList<>();
    //存放order的list
    List<List<Tea>> orderList=new ArrayList<>();





    //结算按钮的点击次数
    int payButtonNum;
    int finishButtonNum;

    private ProductService productService=new ProductServiceImpl();

    private OrderService orderService=new OrderServiceImpl();


    public Index() {
        initComponents();
        searchAllActionPerformed();
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
     * 进主页面自动查询所有产品
     * @param
     */
    private void searchAllActionPerformed() {

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
        float price=0;
        Object[][] obj = new Object[shopCardList.size()][coin.length];
        for (int i = 0; i < shopCardList.size(); i++) {
            // dmt=new DefaultTableModel(obj,coin);
            Tea tea1 = shopCardList.get(i);
            obj[i][0]=tea1.getTeaName();
            obj[i][1]= tea1.getTeaDiscount();
            obj[i][2]=tea1.getTeaPrice();
            dmt.addRow(obj[i]);
            Double teaDiscount = tea1.getTeaDiscount();
            float teaPrice = (float) tea1.getTeaPrice();
            price+=teaPrice*teaDiscount;
            label3.setText(String.valueOf(price)+"元");
        }
        preOrderTable.setModel(dmt);
        preOrderTable.invalidate();
    }

    /**
     * 清空购物车的list数据
     */
    public void clearShopCardList(){

        orderList.add(shopCardList);
        //把list数据放入到preOrderTable中
        coin= new String[]{"产品名称","折扣", "单价(元)"};
        dmt= new DefaultTableModel(null,coin);
        //清空数据
        dmt.setRowCount(0);
        preOrderTable.setModel(dmt);
        preOrderTable.invalidate();
        shopCardList.clear();
        label3.setText(" ");
    }

    /**
     * 结算按钮  把订单放入到数据库，订单状态为0  表示正在做
     *
     * @param e
     */
    private void payButtonActionPerformed(ActionEvent e) {
        // payButtonNum++;
        // System.out.println("点击的结算次数："+payButtonNum);
        //把与订单表初始化
        // clearShopCardList();
        //获取了orderList  把购物车的东西加入到当前订单中
        // TODO add your code here
        //1.清空购物车的表格内容
        //2.将数据创建订单并且显示到当前订单orderTable中，显示status状态为0的，当点击完成订单之后，status转换为0，再刷新一下
        //把list数据放入到preOrderTable中
        // coin= new String[]{"订单号","商品名称", "总价钱(元)","订单状态"};
        // dmt= new DefaultTableModel(null,coin);

        //清空数据
        // dmt.setRowCount(0);

        // float price=0;
        // Object[][] obj = new Object[payButtonNum-finishButtonNum][coin.length];
        // System.out.println("当前订单的数量："+(payButtonNum-finishButtonNum));
        // for (int i = 0; i <payButtonNum-finishButtonNum ; i++) {
        //     List<Tea> list = orderList.get(i);
        //     List<String> name = new ArrayList<>();
        //     for (Tea tea : list) {
        //         name.add(tea.getTeaName());
        //         Double teaDiscount = tea.getTeaDiscount();
        //         float teaPrice = (float) tea.getTeaPrice();
        //         price+=teaPrice*teaDiscount;
        //     }
        //     obj[i][0]= TimeNumberUtils.getLocalTrmSeqNum();
        //     obj[i][1]= name;
        //     obj[i][2]= price;
        //     price=0;
        //     obj[i][3]= TeaStatus.WAIT.getType();
        //     dmt.addRow(obj[i]);
        // }
        // orderTable.setModel(dmt);
        // orderTable.invalidate();
        //把购物车的总价钱设置为0
        // label3.setText(" ");
        Order order = new Order();
        float price=0;
        List<String> productNameList=new ArrayList<>();
        for (Tea tea : shopCardList) {
            Double teaDiscount = tea.getTeaDiscount();
            float teaPrice = (float) tea.getTeaPrice();
            price+=teaPrice*teaDiscount;
            productNameList.add(tea.getTeaName());
        }
        order.setOrderNumber(TimeNumberUtils.getLocalTrmSeqNum());
        order.setOrderPrice(price);
        String json = JSON.toJSON(productNameList).toString();
        order.setOrderName(json);
        order.setOrderCreatTime(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));
        order.setOrderStatus(0);
        orderService.insertOrder(order);
        clearShopCardList();

        //调用当前订单的查询功能  就是为了刷新当前订单表
        orderDisplay();

    }

    /**
     * 当前订单表的刷新功能
     */
    public void orderDisplay(){
        List<Order> orders = orderService.queryOrders();
        coin= new String[]{"订单号","商品名称", "总价钱(元)","订单状态"};
        dmt= new DefaultTableModel(null,coin);
        dmt.setRowCount(0);
        // System.out.println(1);
        // System.out.println(orders.size());
        Object[][] obj=new Object[orders.size()][coin.length];
        // System.out.println(obj);
        for (int i = 0; i < orders.size(); i++) {
            Order order = orders.get(i);
            obj[i][0]=order.getOrderNumber();
            obj[i][1]=order.getOrderName();
            obj[i][2]=order.getOrderPrice();
            obj[i][3]=TeaStatus.WAIT.getType();
            dmt.addRow(obj[i]);
        }
        orderTable.setModel(dmt);
        orderTable.invalidate();
    }

    /**
     * 订单完成按钮，点击订单完成，把数据库订单表的status字段改为1，成为历史订单。
     * 注意：涉及订单表和商品表的双表  所以要判断是否进行事务回滚
     * @param e
     */
    private void finishOrderButtonActionPerformed(ActionEvent e) {
        // finishButtonNum++;
        // System.out.println("点击了完成订单次数："+finishButtonNum);
        // TODO add your code here
        // int selectedRow = orderTable.getSelectedRow();
        // List<Tea> list = orderList.get(selectedRow);
        // List<String> name = new ArrayList<>();
        // float price=0;
        // for (Tea tea : list) {
        //     name.add(tea.getTeaName());
        //     Double teaDiscount = tea.getTeaDiscount();
        //     float teaPrice = (float) tea.getTeaPrice();
        //     price+=teaPrice*teaDiscount;
        //
        // }
        // // System.out.println(list);
        // Order order = new Order();
        // order.setOrderNumber((String) orderTable.getValueAt(selectedRow,0));
        // order.setOrderPrice((Float) orderTable.getValueAt(selectedRow,2));
        // //把list转换为json字符串存储在数据库中
        // String json = JSON.toJSON(name).toString();
        // order.setOrderName(json);
        // order.setOrderCreatTime(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));
        // order.setOrderStatus(1);
        // int i = orderService.insertOrder(order);
        // // System.out.println(i);
        // //更新当前订单，调用结算按钮的方法
        // orderList.remove(selectedRow);
        // //调用一个方法，大小是orderList-1  数据是orderList中的数据  大小是  payButtonNum-finishButtonNum
        // for (int i1 = 0; i1 < payButtonNum-finishButtonNum; i1++) {
        //     List<Tea> list1 = orderList.get(i1);
        //     orderList.add(list1);
        // }
        //
        // System.out.println("点击完成订单之后 当前内存订单中的size数值"+orderList.size());
        // //调用结算的按钮方法，但是不能让按钮的点击次数加1，所以采用方法重载的方式把参数去掉，再把点击次数加一的去掉
        // payButtonActionPerformed();
        //
        // //数据库中对应的数量更新  然后调用查询全部的按钮方法
        // Set set=new TreeSet();
        // for (String productName : name) {
        //     int i1 = productService.updateProductAmount(productName);
        //     // if (i1==1){
        //     //     JOptionPane.showMessageDialog(null,"请及时提醒客户产品已经做好");
        //     // }else {
        //     //     JOptionPane.showMessageDialog(null,"出现错误，请及时联系管理员处理");
        //     // }
        //     set.add(i1);
        // }
        // if (set.size()==1&&set.contains(1)){
        //     JOptionPane.showMessageDialog(null,"请及时提醒客户产品已经做好");
        // }
        // searchAllActionPerformed();
        // 点击订单完整之后，提醒客户取走，并且将其改为历史订单
        int selectedRow = orderTable.getSelectedRow();
        String orderNumber = (String) orderTable.getValueAt(selectedRow, 0);
        boolean status = orderService.updateOrderStatus(orderNumber);
        if (status){
            JOptionPane.showMessageDialog(null,"请及时提醒客户产品已经做好");
            orderDisplay();
        }else {
            JOptionPane.showMessageDialog(null,"出现错误，请及时联系管理员处理");
        }
    }

    /**
     *  清空购物车
     */
    private void payButtonActionPerformed() {
        //把与订单表初始化
        clearShopCardList();
        //获取了orderList  把购物车的东西加入到当前订单中
        // TODO add your code here
        //1.清空购物车的表格内容
        //2.将数据创建订单并且显示到当前订单orderTable中，显示status状态为0的，当点击完成订单之后，status转换为0，再刷新一下
        //把list数据放入到preOrderTable中
        coin= new String[]{"订单号","商品名称", "总价钱(元)","订单状态"};
        dmt= new DefaultTableModel(null,coin);

        //清空数据
        dmt.setRowCount(0);

        orderTable.setModel(dmt);
        orderTable.invalidate();
        //把购物车的总价钱设置为0
        label3.setText(" ");
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
        payButton = new JButton();
        scrollPane3 = new JScrollPane();
        orderTable = new JTable();
        finishOrderButton = new JButton();
        label4 = new JLabel();
        label5 = new JLabel();

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
        searchAll.setBounds(340, 350, 95, 40);

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
        textField1.setBounds(915, 10, 155, 40);

        //---- searchSome ----
        searchSome.setText("\u67e5\u8be2");
        searchSome.addActionListener(e -> searchSomeActionPerformed(e));
        contentPane.add(searchSome);
        searchSome.setBounds(1080, 10, 95, 40);

        //---- addProduct ----
        addProduct.setText("\u6dfb\u52a0");
        addProduct.addActionListener(e -> addProductActionPerformed(e));
        contentPane.add(addProduct);
        addProduct.setBounds(460, 350, 95, 40);

        //======== scrollPane2 ========
        {
            scrollPane2.setViewportView(preOrderTable);
        }
        contentPane.add(scrollPane2);
        scrollPane2.setBounds(330, 480, 570, 190);

        //---- addShopCart ----
        addShopCart.setText("\u52a0\u5165\u8d2d\u7269\u8f66");
        addShopCart.addActionListener(e -> addShopCartActionPerformed(e));
        contentPane.add(addShopCart);
        addShopCart.setBounds(640, 340, 130, 55);

        //---- label2 ----
        label2.setText("\u603b\u4ef7\u94b1\uff1a");
        label2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 28));
        label2.setForeground(Color.red);
        contentPane.add(label2);
        label2.setBounds(605, 675, 120, 40);

        //---- label3 ----
        label3.setText("     ");
        label3.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 24));
        label3.setBackground(Color.white);
        label3.setForeground(new Color(255, 51, 51));
        contentPane.add(label3);
        label3.setBounds(720, 675, 170, 45);

        //---- payButton ----
        payButton.setText("\u7ed3\u7b97");
        payButton.addActionListener(e -> payButtonActionPerformed(e));
        contentPane.add(payButton);
        payButton.setBounds(360, 670, 135, 50);

        //======== scrollPane3 ========
        {
            scrollPane3.setViewportView(orderTable);
        }
        contentPane.add(scrollPane3);
        scrollPane3.setBounds(930, 100, 630, 315);

        //---- finishOrderButton ----
        finishOrderButton.setText("\u5b8c\u6210\u8ba2\u5355");
        finishOrderButton.addActionListener(e -> finishOrderButtonActionPerformed(e));
        contentPane.add(finishOrderButton);
        finishOrderButton.setBounds(1440, 435, 125, 50);

        //---- label4 ----
        label4.setText("\u8d2d\u7269\u8f66\uff1a");
        label4.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 28));
        label4.setForeground(new Color(51, 51, 255));
        contentPane.add(label4);
        label4.setBounds(335, 410, 135, 70);

        //---- label5 ----
        label5.setText("\u5f53\u524d\u8ba2\u5355");
        label5.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 28));
        label5.setForeground(new Color(51, 255, 102));
        contentPane.add(label5);
        label5.setBounds(1425, 50, 130, 45);

        contentPane.setPreferredSize(new Dimension(1590, 795));
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
    private JButton payButton;
    private JScrollPane scrollPane3;
    private JTable orderTable;
    private JButton finishOrderButton;
    private JLabel label4;
    private JLabel label5;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
    public static void main(String[] args) {
        Index index = new Index();

    }


}
