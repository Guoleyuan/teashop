/*
 * Created by JFormDesigner on Thu Apr 14 20:03:26 CST 2022
 */
package com.guet.controller;
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

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();
        scrollPane1 = new JScrollPane();
        table = new JTable(dmt){
            //设置表格的单元格不可编辑
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        searchAll = new JButton();

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
    // JFormDesigner - End of variables declaration  //GEN-END:variables
    public static void main(String[] args) {
        Index index = new Index();

    }


}
