package cn.com.gatico.窗体;

import javax.swing.*;
import java.awt.*;

public class MainWindow1 extends JFrame {
    private JPanel main;
    private JLabel labTitle;
    private JList listUsers;
    private JScrollPane sp;

    public void init() {
        this.setLayout(null);
        this.setTitle("XXX聊天系统【在线】");
        this.setSize(500, 500);
        ImageIcon icon = new ImageIcon(MainWindow1.class.getResource("/").getPath() + "/source/icon.jpg");
        Image image = icon.getImage();
        this.setIconImage(image);
        labTitle.setBounds(0, 0, 60, 60);
        labTitle.setIcon(icon);
        labTitle.setMinimumSize(new Dimension(60, 60));
        labTitle.setMaximumSize(new Dimension(60, 60));
        this.setContentPane(main);
        DefaultListModel dlm = new DefaultListModel<>();
        listUsers.setModel(dlm);
        listUsers.setCellRenderer(new UserItemRender());
        listUsers.setFixedCellHeight(50);
        listUsers.setBounds(0, 0, 100, 200);
        main.add(listUsers);
        String path = "/source/icon.jpg";
        dlm.addElement(new UserItem(path, "2018-6-20", "18:17", "张三", "张三", "200", "570"));
        dlm.addElement(new UserItem(path, "2018-6-20", "18:17", "李四", "李四", "200", "570"));
        dlm.addElement(new UserItem(path, "2018-6-20", "18:17", "王五", "王五", "200", "570"));
        dlm.addElement(new UserItem(path, "2018-6-20", "18:17", "赵六", "赵六", "200", "570"));
        dlm.addElement(new UserItem(path, "2018-6-20", "18:17", "田七", "田七", "200", "570"));
        dlm.addElement(new UserItem(path, "2018-6-20", "18:17", "孙八", "孙八", "200", "570"));

        sp.setViewportView(listUsers);
        sp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        sp.setBounds(listUsers.getX(), listUsers.getY(), listUsers.getWidth(), main.getHeight());
        sp.setBounds(0, 0, 100, 200);
        main.add(sp);
        //Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        //this.setLocation(Double.valueOf(screenSize.getWidth() / 2 - this.getWidth() / 2).intValue(), Double.valueOf(screenSize.getHeight() / 2 - this.getHeight() / 2).intValue());
        this.setLocationRelativeTo(null);// 设置居中显示
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

}
