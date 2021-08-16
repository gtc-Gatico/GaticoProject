package cn.com.gatico.窗体;

import javax.swing.*;
import java.awt.*;

public class Test extends JFrame {
    public Test(String title) {
        this.setTitle(title);
        this.setSize(1065, 600);
        this.setLayout(null);
        this.setLocationRelativeTo(null);// 设置居中显示
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        Test test = new Test("测试");
        JPanel jPanel = new JPanel();
        jPanel.setBounds(0,0,100,100);
        jPanel.setBackground(Color.GREEN);
        jPanel.setVisible(true);
        test.add(jPanel);
    }

}
