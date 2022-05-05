/*
 * Created by JFormDesigner on Tue May 03 23:11:26 CST 2022
 */

package com.guet.controller;


import com.alibaba.fastjson.JSON;
import com.eltima.components.ui.DatePicker;
import com.guet.entity.Order;
import com.guet.service.DiscountProductService;
import com.guet.service.Impl.DiscountProductServiceImpl;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 1
 */
public class ExportData extends JFrame {
    private DiscountProductService discountproductService=new DiscountProductServiceImpl();
    String start=null;
    String end =null;
    DatePicker startdatePicker=null;
    DatePicker enddatePicker=null;
    String coin[]={"商品编号","订单总价钱","订单内容","下单时间","订单状态","顾客等待号","统一编号","流水编号"};

    public ExportData() {
        initComponents();
    }

    private void exportDataActionPerformed(ActionEvent e) {
        // TODO add your code here
        start=startdatePicker.getText();
        end= enddatePicker.getText();
        List<Order> list=discountproductService.exportData(start,end);

        HSSFWorkbook wb=new HSSFWorkbook();//创建电子表格
        HSSFSheet sheet=wb.createSheet("销售数据");//创建表单的名字
        sheet.setDefaultColumnWidth(28);
        HSSFRow rowtop=null;
        HSSFCell celltop=null;
        rowtop= sheet.createRow(0);//创建表头
        for(int i=0;i< coin.length;i++){
            celltop=rowtop.createCell(i);
            celltop.setCellValue(coin[i]);
        }
        //写入数据
        int a=1;
        for(int i=0;i<list.size();i++){
            Order order=list.get(i);
            HSSFRow row= sheet.createRow(a++);
            for(int j=0;j< coin.length;j++){
                HSSFCell cell= row.createCell(j);
                if(j==0){
                    cell.setCellValue(String.valueOf(order.getOrderNumber()));
                }else if (j==1){
                    cell.setCellValue(String.valueOf(order.getOrderPrice()));
                }else if (j==2){
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
                    cell.setCellValue(s);
                }else if (j==3){
                    cell.setCellValue(String.valueOf(order.getOrderTime()));
                }else if (j==4){
                    if(order.getOrderStatus()==1){
                        cell.setCellValue("已完成订单");
                    }else {
                        cell.setCellValue("未完成订单");
                    }
                }else if (j==5){
                    cell.setCellValue(String.valueOf(order.getOrderId()));
                }else if (j==6){
                    cell.setCellValue(String.valueOf(order.getMchId()));
                }else if (j==7){
                    cell.setCellValue(String.valueOf(order.getTransactionId()));
                }
            }
        }


        File file=new File(FileSystemView.getFileSystemView() .getHomeDirectory().getAbsolutePath()+"/销售数据.xls");
        try {
            FileOutputStream fileOutputStream=new FileOutputStream(file);
            wb.write(fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        /**for(int i=0;i< list.size();i++){
            Order order=list.get(i);
            if(i==0){
                row=sheet.createRow(0);
                for(int j=0;j< coin.length;j++){
                    cell= row.createCell(j);
                    cell.setCellValue(coin[j]);
                    i--;
                }
            }else{
                row= sheet.createRow(i+1);
                for(int j=0;j< coin.length;j++){
                    cell= row.createCell(j);
                    cell.setCellValue(order.getOrderName());
                }
            }
        }*/
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();
        label2 = new JLabel();
        exportData = new JButton();
        textField1 = new JTextField();
        textField2 = new JTextField();
        startpanel=new JPanel();
        endpanel=new JPanel();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("\u8d77\u59cb\u65f6\u95f4\uff1a");
        contentPane.add(label1);
        label1.setBounds(new Rectangle(new Point(50, 110), label1.getPreferredSize()));

        //---- label2 ----
        label2.setText("\u7ec8\u6b62\u65f6\u95f4\uff1a");
        contentPane.add(label2);
        label2.setBounds(new Rectangle(new Point(265, 110), label2.getPreferredSize()));

        //---- exportData ----
        exportData.setText("\u5bfc\u51fa\u9500\u552e\u6570\u636e");
        exportData.addActionListener(e -> exportDataActionPerformed(e));
        contentPane.add(exportData);
        exportData.setBounds(new Rectangle(new Point(200, 240), exportData.getPreferredSize()));

        /**
         * 起始时间
         * */
        startpanel.setBounds(110,100,115,31);
        this.getContentPane().add(startpanel);
         startdatePicker=new DatePicker(new Date(),"yyyy-MM-dd",
                new Font("Timer New Roman",Font.BOLD,14),new Dimension(120,24));
        startdatePicker.getInnerTextField().setEditable(false);
        startpanel.add(startdatePicker);

        /**
         * 结束时间
         * */
        endpanel.setBounds(330,100,115,31);
        this.getContentPane().add(endpanel);
        enddatePicker=new DatePicker(new Date(),"yyyy-MM-dd",
                new Font("Timer New Roman",Font.BOLD,14),new Dimension(120,24));
        enddatePicker.getInnerTextField().setEditable(false);
        endpanel.add(enddatePicker);


        contentPane.setPreferredSize(new Dimension(500, 400));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents

        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);


    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JLabel label2;
    private JButton exportData;
    private JTextField textField1;
    private JTextField textField2;
    private JPanel startpanel;
    private JPanel endpanel;


    // JFormDesigner - End of variables declaration  //GEN-END:variables



    public static void main(String[] args) {
        new ExportData();
    }
}
