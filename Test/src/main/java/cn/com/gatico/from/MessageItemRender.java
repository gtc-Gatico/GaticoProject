package cn.com.gatico.from;

import javax.swing.*;
import java.awt.*;

public class MessageItemRender extends DefaultListCellRenderer {
    private Message message;
    private boolean isSelected;
    private JComponent jlist;

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        jlist = list;
        if (value instanceof Message) {
            message = (Message) value;
        }
        setBackground(Color.WHITE);
        this.isSelected = isSelected;
        return this;
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (isSelected) {
            g.setColor(Color.GREEN);
            g.fillRect(jlist.getX(), jlist.getY(), jlist.getWidth(), jlist.getHeight());
            g.setColor(Color.BLACK);
        }
//        try {
//            g.drawImage(ImageIO.read(new File(getClass().getResource("/").getPath() + message.getImg())), 0, 0, 50, 50, null);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        int width = g.getFontMetrics().stringWidth(message.getMsg());
        if (message.getMsg().contains("自己")) {
            for (int i = 0; i < message.getMsg().split("\n").length; i++) {
                g.drawString(message.getMsg(), 480 - 50 - width, 25 + (i * 25));
            }
        } else {
            for (int i = 0; i < message.getMsg().split("\n").length; i++) {
                g.drawString(message.getMsg(), 10, 25 + (i * 25));
            }
        }
        super.paintComponent(g);
    }
}