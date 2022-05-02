/*
 * Created by JFormDesigner on Mon May 02 15:37:02 CST 2022
 */

package com.guet.controller;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * @author 1
 */
public class CodePay extends JFrame {
    public CodePay() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        codeLabel = new JLabel();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- codeLabel ----
        codeLabel.setText("text");
        ImageIcon image = null;
        try {
            image = new ImageIcon(ImageIO.read(new File(System.getProperty("user.dir")+"/teashop_project/src/main/resources/new.jpg")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        codeLabel.setIcon(image);
        contentPane.add(codeLabel);
        codeLabel.setBounds(130, 30, 300, 300);

        contentPane.setPreferredSize(new Dimension(565, 425));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents


        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel codeLabel;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
