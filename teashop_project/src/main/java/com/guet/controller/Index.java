/*
 * Created by JFormDesigner on Thu Apr 14 20:03:26 CST 2022
 */
package com.guet.controller;

import com.alibaba.fastjson.JSON;
import com.guet.entity.Order;
import com.guet.entity.Tea;
import com.guet.enums.TeaStatus;
import com.guet.pay.WXPay;
import com.guet.sdk.WXPayUtil;
import com.guet.service.Impl.OrderServiceImpl;
import com.guet.service.Impl.ProductServiceImpl;
import com.guet.service.OrderService;
import com.guet.service.ProductService;
import com.guet.util.MyTable;

import javax.swing.*;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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


    private ProductService productService=new ProductServiceImpl();

    private OrderService orderService=new OrderServiceImpl();




    public Index() {
        initComponents();
        searchAllActionPerformed();
        refreshCurrentOrder();
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

    public void searchAllActionPerformed() {

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
            tea.setTeaDiscount((Float) table.getValueAt(index1,2));
            tea.setTeaPrice((Float) table.getValueAt(index1,3));
            

            // System.out.println(tea);
            shopCardList.add(tea);

            Map<String, Integer> map = new HashMap<>();
            for (Tea teas : shopCardList) {
                if (map.containsKey(teas.getTeaName())){
                    Integer integer = map.get(tea.getTeaName());
                    map.put(tea.getTeaName(),++integer);
                }else {
                    map.put(tea.getTeaName(),1);
                }
            }
            Set<String> strings = map.keySet();
            for (String string : strings) {
                int i = productService.searchAmount(string);
                if (i-map.get(string)<0){
                    JOptionPane.showMessageDialog(null,"十分抱歉,"+string+"已经没有了");
                    return;
                }
            }

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
                Float teaDiscount = tea1.getTeaDiscount();
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

        List<Tea> objects = new ArrayList<>(shopCardList);
        orderList.add(objects);
        //把list数据放入到preOrderTable中
        coin= new String[]{"产品名称","折扣", "单价(元)"};
        dmt= new DefaultTableModel(null,coin);
        //清空数据
        dmt.setRowCount(0);
        preOrderTable.setModel(dmt);
        preOrderTable.invalidate();
        shopCardList.clear();
    }

    /**
     * 购物车结算功能，把购物车的东西存放到当前正在进行的table中，显示出当前正在进行的订单
     * @param e
     */
    private void payButtonActionPerformed(ActionEvent e) {
        // // TODO add your code here

        if (shopCardList.size() == 0) {
            JOptionPane.showMessageDialog(null,"请加入商品到购物车");
            return;
        }else {

            Order order = new Order();
            List<String> names = new ArrayList<>();
            float price=0;
            for (Tea tea : shopCardList) {
                Float teaDiscount = tea.getTeaDiscount();
                float teaPrice = (float) tea.getTeaPrice();
                price+=teaPrice*teaDiscount;
                names.add(tea.getTeaName());
            }
            order.setOrderNumber(WXPayUtil.generateNonceStr());
            order.setOrderPrice(price);
            order.setOrderName(JSON.toJSONString(names));
            order.setOrderStatus(0);






            WXPay.unifiedOrder(order);

            new CodePay();


            //让service层处理插入订单表和修改库存的事务
            // orderService.shopCardPay(order,shopCardList);


            //清空购物车
            clearShopCardList();

            label3.setText(" ");
            //把数据放到当前订单中  就是查询订单表中的所有order_status为0的值


        }

    }

    /**
     * 刷新当前订单表，就是查询出所有还没有制作完成的订单
     */
    public void refreshCurrentOrder() {
        coin= new String[]{"订单号","商品名称", "总价钱(元)","订单状态","等待号"};
        dmt= new DefaultTableModel(null,coin);

        //清空数据
        dmt.setRowCount(0);
        List<Order> list = orderService.queryOrders();
        Object[][] obj = new Object[list.size()][coin.length];
        for (int i = 0; i < list.size(); i++) {
            Order order = list.get(i);
            obj[i][0]=order.getOrderNumber();
            obj[i][1]=order.getOrderName();
            obj[i][2]=order.getOrderPrice();
            obj[i][3]=TeaStatus.WAIT.getType();
            obj[i][4]=order.getOrderId();
            dmt.addRow(obj[i]);
        }
        orderTable.setModel(dmt);
        orderTable.invalidate();
    }

    /**
     * 订单完成按钮，点击订单完成，把数据库订单表的status字段改为1，成为历史订单。
     * @param e
     */
    private void finishOrderButtonActionPerformed(ActionEvent e) {
        // // TODO add your code here
        int selectedRow = orderTable.getSelectedRow();
        String orderNumber = (String) orderTable.getValueAt(selectedRow, 0);
        boolean b = orderService.updateOrderStatus(orderNumber);
        if (b){
            JOptionPane.showMessageDialog(null,"请提醒客户取茶");
        }else {
            JOptionPane.showMessageDialog(null,"出现错误，请及时联系管理员");
        }

        //刷新订单表
        refreshCurrentOrder();
    }

    /**
     * 删除奶茶商品  获取主键id值来删除
     * @param e
     */
    private void deleteProductButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
        int selectedRow = table.getSelectedRow();
        int valueAt = (int) table.getValueAt(selectedRow, 0);
        int flag = JOptionPane.showConfirmDialog(null, "确定要删除吗?", "删除提示", 2);
        if (flag==JOptionPane.YES_OPTION){
            boolean b = productService.deleteProductById(valueAt);
            if (b){
                JOptionPane.showMessageDialog(null,"删除成功");
            }else {
                JOptionPane.showMessageDialog(null,"删除失败");
            }
        }else if (flag==JOptionPane.NO_OPTION){

        }else if (flag==JOptionPane.CANCEL_OPTION){

        }
        searchAllActionPerformed();
    }

    /**
     * 从购物车一处选中的商品
     * @param e
     */
    private void deleteButtonActionPerformed(ActionEvent e) {
        // TODO add your code here

        int selectedRow = preOrderTable.getSelectedRow();
        System.out.println(selectedRow);
        shopCardList.remove(selectedRow);

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
            Float teaDiscount = tea1.getTeaDiscount();
            float teaPrice = (float) tea1.getTeaPrice();
            price+=teaPrice*teaDiscount;
            label3.setText(String.valueOf(price)+"元");
        }
        preOrderTable.setModel(dmt);
        preOrderTable.invalidate();
        if (shopCardList.size()==0){
            label3.setText("");
        }

    }





    /**
     * 查询到所有的打折的商品的信息
     * */
    private void searchDiscountActionPerformed(ActionEvent e) {
        // TODO add your code here
        new Discount();
    }
    /**
    * 改变商品信息
    * */
    private void changeDiscountButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
        //获取表中被选中的行的信息
        int rowNo=table.getSelectedRow();
        Tea tea =new Tea();
        tea.setTeaId((Integer) table.getValueAt(rowNo,0));
        tea.setTeaName((String) table.getValueAt(rowNo,1));
        tea.setTeaDiscount((Float) table.getValueAt(rowNo,2));
        tea.setTeaPrice((Float) table.getValueAt(rowNo,3));
        tea.setTeaCategory((String) table.getValueAt(rowNo,4));
        tea.setTeaAmount((Integer) table.getValueAt(rowNo,5));
        new ChangeDiscount(tea);
    }
    /**
     * 当前订单之中显示的信息被覆盖，设置显示全部信息的按钮
     * */
    private void searchOrderButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
        //需要被选中行的信息
        int rowNo=orderTable.getSelectedRow();

        //打印被选中行的所有信息
        //调取OrderTable中的信息，需要组长的传送代码，明确参数是哪些，实现查询
        //由于当前订单的订单状态全是待处理，故这里直接用0来代替
        Order order=new Order();
        order.setOrderNumber((String) orderTable.getValueAt(rowNo,0));
        order.setOrderName((String) orderTable.getValueAt(rowNo,1));
        order.setOrderPrice((Float) orderTable.getValueAt(rowNo,2));
        order.setOrderStatus(0);
        order.setOrderId((Integer) orderTable.getValueAt(rowNo,4));
        System.out.println(order);//已经可以查询到所有产品信息
        //查询待处理产品信息类
        new SreachOrder(order);
    }

    private void refreshButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
        //刷新订单表
        refreshCurrentOrder();
        //刷新商品表
        searchAllActionPerformed();
    }

    /**
     * 付款码支付  扫码枪扫取之后进行付款
     * @param e
     */
    private void paymentCodeButtonActionPerformed(ActionEvent e){
        // TODO add your code here
        //132840844856049306
        String auth_code = textField2.getText();

        if (shopCardList.size() == 0) {
            JOptionPane.showMessageDialog(null,"请加入商品到购物车");
            return;
        }else {

            Order order = new Order();
            List<String> names = new ArrayList<>();
            float price = 0;
            for (Tea tea : shopCardList) {
                Float teaDiscount = tea.getTeaDiscount();
                float teaPrice = (float) tea.getTeaPrice();
                price += teaPrice * teaDiscount;
                names.add(tea.getTeaName());
            }
            order.setOrderNumber(WXPayUtil.generateNonceStr());
            order.setOrderPrice(price);
            order.setOrderName(JSON.toJSONString(names));
            order.setMchId(1623889015);


            try {
                String returnStatus = WXPay.scanCodeToPay(auth_code, order,shopCardList);
                if ("SUCCESS".equals(returnStatus)){

                    // orderService.shopCardPay(order,shopCardList);
                    JOptionPane.showMessageDialog(null,"支付成功");

                    //清空购物车
                    clearShopCardList();

                    label3.setText(" ");

                    //刷新订单表
                    refreshCurrentOrder();
                    //刷新商品表
                    searchAllActionPerformed();

                }else {
                    JOptionPane.showMessageDialog(null,"支付失败，请重新扫码");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }


        }

    }

    /**
     * 导出销售数据的按钮
     * */
    private void exportDataActionPerformed(ActionEvent e) {
        // TODO add your code here
        new ExportData();
    }
    /**
     * 查询历史订单按钮
     * */
    private void historyActionPerformed(ActionEvent e) {
        // TODO add your code here
        new History();
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();
        scrollPane1 = new JScrollPane();
        table = new MyTable();
        searchAll = new JButton();
        textField1 = new JTextField();
        searchSome = new JButton();
        addProduct = new JButton();
        scrollPane2 = new JScrollPane();
        preOrderTable = new MyTable();
        addShopCart = new JButton();
        label2 = new JLabel();
        label3 = new JLabel();
        payButton = new JButton();
        scrollPane3 = new JScrollPane();
        orderTable = new MyTable();
        finishOrderButton = new JButton();
        label4 = new JLabel();
        label5 = new JLabel();
        deleteProductButton = new JButton();
        deleteButton = new JButton();
        searchDiscount = new JButton();
        changeDiscountButton = new JButton();
        searchOrderButton = new JButton();
        refreshButton = new JButton();
        paymentCodeButton = new JButton();
        textField2 = new JTextField();
        timeLabel = new JLabel();
        button = new JTextArea(10,5);
        eventButton = new JButton();
        textArea = new JTextArea(10,5);
        submitButton = new JButton();
        exportData = new JButton();
        history = new JButton();


        //======== this ========
        setIconImage(new ImageIcon(getClass().getResource("/imgs/logo.jpg")).getImage());
        setTitle("\u871c\u96ea\u51b0\u57ce");
        this.setPreferredSize(new Dimension(1600, 795));
        JPanel contentPane = (JPanel) getContentPane();
        contentPane.setLayout(null);
        contentPane.setOpaque(false);

        //---- button ----   节日名称展示
        button.setText("");
        button.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 28));
        button.setForeground(Color.red);
        button.setOpaque(false);
        contentPane.add(button);
        button.setLineWrap(true);
        // this.getLayeredPane().add(button);
        button.setBounds(5,100,300,100);

        //---- eventButton ----   修改节日名称
        eventButton.setText("添加活动");
        eventButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitButton.setVisible(true);
                textArea.setVisible(true);
            }
        });
        contentPane.add(eventButton);
        eventButton.setBounds(1430,10,95,40);

        //---- textArea ----   接收节日名称的文本框
        textArea.setVisible(false);
        textArea.setAutoscrolls(true);
        contentPane.add(textArea);
        textArea.setBounds(1000,540,300,100);


        //----  ----   修改节日名称
        submitButton.setVisible(false);
        submitButton.setText("提交");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = textArea.getText();
                button.setText(text);
                submitButton.setVisible(false);
                textArea.setVisible(false);
            }
        });
        contentPane.add(submitButton);
        submitButton.setBounds(1300,600,95,40);


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
        addProduct.setBounds(440, 350, 95, 40);

        //======== scrollPane2 ========
        {
            scrollPane2.setViewportView(preOrderTable);
        }
        contentPane.add(scrollPane2);
        scrollPane2.setBounds(335, 450, 570, 190);

        //---- addShopCart ----
        addShopCart.setText("\u52a0\u5165\u8d2d\u7269\u8f66");
        addShopCart.addActionListener(e -> addShopCartActionPerformed(e));
        contentPane.add(addShopCart);
        addShopCart.setBounds(775, 340, 130, 55);

        //---- label2 ----
        label2.setText("\u603b\u4ef7\u94b1\uff1a");
        label2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 28));
        label2.setForeground(Color.red);
        contentPane.add(label2);
        label2.setBounds(610, 660, 120, 40);

        //---- label3 ----
        label3.setText("     ");
        label3.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 24));
        label3.setBackground(Color.white);
        label3.setForeground(new Color(255, 51, 51));
        contentPane.add(label3);
        label3.setBounds(715, 655, 170, 45);

        //---- payButton ----
        payButton.setText("\u5fae\u4fe1\u4e8c\u7ef4\u7801\u652f\u4ed8");
        payButton.addActionListener(e -> payButtonActionPerformed(e));
        contentPane.add(payButton);
        payButton.setBounds(330, 650, 135, 50);

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
        label4.setBounds(335, 400, 135, 55);

        //---- label5 ----
        label5.setText("\u5f53\u524d\u8ba2\u5355");
        label5.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 28));
        label5.setForeground(new Color(51, 255, 102));
        contentPane.add(label5);
        label5.setBounds(935, 55, 130, 45);

        //---- deleteProductButton ----
        deleteProductButton.setText("\u5220\u9664");
        deleteProductButton.addActionListener(e -> deleteProductButtonActionPerformed(e));
        contentPane.add(deleteProductButton);
        deleteProductButton.setBounds(540, 350, 95, 40);

        //---- deleteButton ----
        deleteButton.setText("\u4ece\u8d2d\u7269\u8f66\u79fb\u9664");
        deleteButton.addActionListener(e -> deleteButtonActionPerformed(e));
        contentPane.add(deleteButton);
        deleteButton.setBounds(470, 650, 135, 50);

        //---- searchDiscount ----
        searchDiscount.setText("打折产品");
        searchDiscount.addActionListener(e -> searchDiscountActionPerformed(e));
        contentPane.add(searchDiscount);
        searchDiscount.setBounds(1200, 10, 95, 40);

        //---- changeDiscountButton ----
        changeDiscountButton.setText("\u4fee\u6539");
        changeDiscountButton.addActionListener(e -> changeDiscountButtonActionPerformed(e));
        contentPane.add(changeDiscountButton);
        changeDiscountButton.setBounds(645, 350, 95, 40);

        //---- searchOrderButton ----
        searchOrderButton.setText("\u67e5\u8be2\u8ba2\u5355");
        searchOrderButton.addActionListener(e -> searchOrderButtonActionPerformed(e));
        contentPane.add(searchOrderButton);
        searchOrderButton.setBounds(1305, 435, 125, 50);

        //---- refreshButton ----
        refreshButton.setText("\u5237\u65b0\u8ba2\u5355");
        refreshButton.addActionListener(e -> refreshButtonActionPerformed(e));
        contentPane.add(refreshButton);
        refreshButton.setBounds(1315, 10, 95, 40);

        //---- paymentCodeButton ----
        paymentCodeButton.setText("\u5fae\u4fe1\u4ed8\u6b3e\u7801\u652f\u4ed8");
        paymentCodeButton.addActionListener(e -> paymentCodeButtonActionPerformed(e));
        contentPane.add(paymentCodeButton);
        paymentCodeButton.setBounds(765, 710, 135, 50);
        contentPane.add(textField2);
        textField2.setBounds(335, 710, 425, 50);
        contentPane.add(timeLabel);
        timeLabel.setBounds(1200, 60, 405, 40);
        new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeLabel.setText(new SimpleDateFormat("yyyy年MM月dd日 EEEE hh:mm:ss").format(new Date()));
            }
        }).start();
        timeLabel.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 24));

        //---- exportData ----
        exportData.setText("\u5bfc\u51fa\u9500\u552e\u6570\u636e");
        exportData.addActionListener(e -> exportDataActionPerformed(e));
        contentPane.add(exportData);
        exportData.setBounds(1160, 435, 125, 50);

        //---- history ----
        history.setText("\u5386\u53f2\u8ba2\u5355");
        history.addActionListener(e -> historyActionPerformed(e));
        contentPane.add(history);
        history.setBounds(1020, 435, 125, 50);


        contentPane.setPreferredSize(new Dimension(1590, 795));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
        this.setVisible(true);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
    private JButton deleteProductButton;
    private JButton deleteButton;
    private JButton searchDiscount;
    private JButton changeDiscountButton;
    private JButton searchOrderButton;
    private JButton refreshButton;
    private JButton paymentCodeButton;
    private JTextField textField2;
    private JLabel timeLabel;
    private JTextArea button;
    private JButton eventButton;
    private JTextArea textArea;
    private JButton submitButton;
    private JButton exportData;
    private JButton history;

    // JFormDesigner - End of variables declaration  //GEN-END:variables
    public static void main(String[] args) {
        Index index = new Index();

    }


}
