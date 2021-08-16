package cn.com.gatico;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StepRecorder extends JFrame {
    public static Image icon = Toolkit.getDefaultToolkit().getImage(StepRecorder.class.getResource("/").getPath() + "source/icon.jpg");
    public static Robot robot;

    public static void main(String[] args) {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }


        JFrame jFrame = new JFrame("步骤记录器");
        jFrame.setIconImage(icon);
        JFrame.setDefaultLookAndFeelDecorated(true);
        jFrame.setLayout(null);
        jFrame.setBounds(0, 0, 300, 70);
        Button startButton = new Button("开始")
                .setBound(0, 0, 70, 30)
                .addMouseListeners(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        super.mouseClicked(e);
                        jFrame.setState(ICONIFIED);
                    }
                }).addTo(jFrame);
        Button endButton = new Button("结束")
                .setBound(70, 0, 70, 30)
                .addMouseListeners(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        super.mouseClicked(e);
                        JOptionPane.showMessageDialog(jFrame, "结束");
                    }
                }).addTo(jFrame);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            SwingUtilities.updateComponentTreeUI(jFrame);
        } catch (Exception e) {
            e.printStackTrace();
        }
        jFrame.setVisible(true);
//        BufferedImage screenCapture = robot.createScreenCapture(new Rectangle(Window.getOwnerlessWindows()[0].getBounds()));

        final TrayIcon trayIcon;

        if (SystemTray.isSupported()) {

            SystemTray tray = SystemTray.getSystemTray();
            ActionListener exitListener = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Exiting...");
                    System.exit(0);
                }
            };

            PopupMenu popup = new PopupMenu();
            MenuItem defaultItem = new MenuItem("退出");
            defaultItem.addActionListener(exitListener);
            popup.add(defaultItem);

            trayIcon = new TrayIcon(icon, "步骤记录器", popup);

            ActionListener actionListener = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if(jFrame.getState() == ICONIFIED){
                        jFrame.setState(NORMAL);
                    }
                    if(jFrame.getState() == NORMAL){
                        jFrame.setState(ICONIFIED);
                        jFrame.setState(NORMAL);
                    }
                }
            };

            trayIcon.setImageAutoSize(true);
            trayIcon.addActionListener(actionListener);

            try {
                tray.add(trayIcon);
            } catch (AWTException e1) {
                e1.printStackTrace();
            }

        }
    }

}

class Button extends JButton {
    public Button() {
        super("");
    }

    public Button(String text) {
        super(text);
        this.setMargin(new Insets(0, 0, 0, 0));
        this.setFont(new Font("微软雅黑", 0, 15));
        this.setHorizontalTextPosition(JButton.CENTER);
        this.setVerticalTextPosition(JButton.CENTER);
    }

    public Button setBound(int x, int y, int width, int height) {
        super.setBounds(x, y, width, height);
        return this;
    }

    public Button addTo(Container jPanel) {
        jPanel.add(this);
        return this;
    }

    public synchronized Button addMouseListeners(MouseListener l) {
        super.addMouseListener(l);
        return this;
    }
}


