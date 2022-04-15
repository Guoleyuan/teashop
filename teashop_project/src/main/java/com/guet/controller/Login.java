/*
 * Created by JFormDesigner on Wed Apr 13 12:23:46 CST 2022
 */

package com.guet.controller;

import com.guet.service.Impl.UserServiceImpl;
import com.guet.service.UserService;
import com.guet.util.StringUtil;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.lang.*;
import java.net.URL;

/**
 * @author 1
 */
public class Login extends JFrame {

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JTextField username;
    private JLabel label2;
    private JPasswordField password;
    private JButton button1;
    private JButton button2;
    private JTextField logo;

    private UserService userService=new UserServiceImpl();



    public Login() {
        initComponents();
    }

    private void initComponents() {

        /*
        设置窗口logo
         */
        Container contentPane = getContentPane();
        URL url = getClass().getResource("/imgs/logo.jpg"); //当前编译后class文件所在目录查找
        ImageIcon icon = new ImageIcon(url);
        this.setIconImage(icon.getImage());// 给窗体设置图标方法
        this.setTitle("蜜雪冰城");

        // URL url1 = getClass().getResource("/imgs/login-background.jpeg"); //当前编译后class文件所在目录查找
        // ImageIcon icon1 = new ImageIcon(url1);
        // JLabel lbBg = new JLabel(icon1);
        // lbBg.setBounds(0, 0, 630, 300);
        // contentPane.add(lbBg);



        // 改变系统默认字体
        Font font = new Font("Dialog", Font.PLAIN, 14);
        java.util.Enumeration keys = UIManager.getDefaults().keys();
        while(keys.hasMoreElements()){
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if(value instanceof javax.swing.plaf.FontUIResource){
                UIManager.put(key, font);
            }
        }


        label1 = new JLabel();
        username = new JTextField("guet");
        label2 = new JLabel();
        password = new JPasswordField("guet1234");
        button1 = new JButton();
        button2 = new JButton();

        //======== this ========
        setBackground(Color.white);
        setResizable(false);


        contentPane.setLayout(null);
        setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //---- label1 ----
        label1.setText("\u7528\u6237\u540d\uff1a");
        contentPane.add(label1);
        label1.setBounds(185, 135, 60, 25);
        contentPane.add(username);
        username.setBounds(240, 130, 170, 33);

        //---- label2 ----
        label2.setText("\u5bc6\u7801\uff1a");
        label2.setBackground(Color.white);
        contentPane.add(label2);
        label2.setBounds(185, 210, 60, 25);
        contentPane.add(password);
        password.setBounds(240, 205, 170, 33);

        //---- button1 ----
        button1.setText("\u767b\u5f55");
        contentPane.add(button1);
        button1.setBounds(new Rectangle(new Point(190, 275), button1.getPreferredSize()));
        button1.addActionListener(e -> { button1ActionPerformed(e);});

        //---- button2 ----
        button2.setText("\u91cd\u7f6e");
        contentPane.add(button2);
        button2.setBounds(new Rectangle(new Point(315, 275), button2.getPreferredSize()));
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //重置用户名和密码
                username.setText("");
                password.setText("");
            }
        });
        logo=new JTextField();
        logo.setText("蜜雪冰城收银系统");
        logo.setForeground(Color.red);
        logo.setEditable(false);
        logo.setHorizontalAlignment(JTextField.CENTER);
        logo.setFont(new Font("宋体",Font.BOLD,40));
        logo.setBounds(80,0,480,70);
        logo.setBorder(null);
        logo.setOpaque(false);
        contentPane.add(logo);



        contentPane.setPreferredSize(new Dimension(625, 450));
        pack();
        setLocationRelativeTo(getOwner());
    }

    /**
     * 点击登录之后判断正确之后进行跳转页面
     * @param e
     */
    private void button1ActionPerformed(ActionEvent e) {
        String usernameText = username.getText();
        String passwordText = new String(password.getPassword());
        // System.out.println(usernameText);
        // System.out.println(passwordText);
        //1.获取文本框的内容
        //2.判断是否是空
        //3.和数据库的比较  如果正确跳转首页  如果错误给出提示框
        if (StringUtil.isStringNull(usernameText)||StringUtil.isStringNull(passwordText)){
            //给出提示框
            JOptionPane.showMessageDialog(null,"用户名或者密码不能为空");
        }else {
            //判断用户名和密码是否正确
            System.out.println("正在登录");
            boolean accountTrue = userService.isAccountTrue(usernameText, passwordText);
            // System.out.println(accountTrue);
            // System.out.println(usernameText);
            // System.out.println(passwordText);
            if (accountTrue){
                //用户名密码正确，跳转页面
                System.out.println("跳转主页面");
                this.dispose();
                new Index();

            }else {
                JOptionPane.showMessageDialog(null,"用户名或者密码输入错误，请重新输入");
            }

        }
    }


    // JFormDesigner - End of variables declaration  //GEN-END:variables
    public static void main(String[] args) {
        Login login = new Login();
    }

}
