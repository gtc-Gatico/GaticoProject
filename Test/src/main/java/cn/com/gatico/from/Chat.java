package cn.com.gatico.from;

import org.bytedeco.librealsense.frame;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Set;

public class Chat {
    public String UserName = "";

    private SocketChannel socketChannel;
    JTextArea sendTextMsg;
    JScrollPane showMsg;
    private final ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
    DefaultListModel<Message> dlm = new DefaultListModel<>();

    public void init() {
        JFrame jFrame = new JFrame();
        jFrame.setTitle("与" + UserName + "聊天中");
        jFrame.setSize(500, 500);
        ImageIcon icon = new ImageIcon(Chat.class.getResource("/").getPath() + "/source/icon.jpg");
        Image image = icon.getImage();
        jFrame.setIconImage(image);
        jFrame.setResizable(false);
        jFrame.setLocationRelativeTo(null);// 设置居中显示
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //添加主布局页面
        JPanel main = new JPanel();
        main.setLayout(null);
        main.setLocation(0, 0);
        main.setSize(jFrame.getSize());
        jFrame.setContentPane(main);
        //添加信息框
        JList<Message> messageJList = new JList<>(dlm);
        messageJList.setCellRenderer(new MessageItemRender());
        messageJList.setFixedCellHeight(50);

        showMsg = new JScrollPane(messageJList);
        showMsg.setBackground(Color.WHITE);
//        showMsg.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//        showMsg.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        showMsg.setBounds(0, 0, 485, 300);
//        showMsg.setAutoscrolls(true);
        showMsg.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                e.getAdjustable().setValue(e.getAdjustable().getMaximum());
            }
        });
        main.add(showMsg);

        //添加发送信息框
        JPanel sendMsg = new JPanel();
        sendMsg.setBackground(Color.WHITE);
        sendMsg.setLayout(null);
        sendMsg.setBounds(0, 300, 485, 200);
        main.add(sendMsg);

        //文本框
        JScrollPane jScrollPane = new JScrollPane();
        jScrollPane.setBounds(0, 0, 480, (200 - 25 * 2 - 20));
        jScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane.setBorder(new LineBorder(Color.WHITE));
        sendTextMsg = new JTextArea("");
        sendTextMsg.setColumns(240);
        sendTextMsg.setLineWrap(true);
        sendTextMsg.setWrapStyleWord(true);
        sendTextMsg.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == 10) {
                    if (sendTextMsg.getText().trim().length() <= 0) {
                        return;
                    }
                    onWrite(sendTextMsg.getText());
                }
            }
        });

        jScrollPane.getViewport().add(sendTextMsg);
        sendMsg.add(jScrollPane);

        //添加发送按钮
        JButton jButton = new JButton("发送");
        jButton.setBounds((480 - 65 - 5), (200 - 25 * 2 - 20), 65, 25);
        sendMsg.add(jButton);

        jButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                //发送
                onWrite(sendTextMsg.getText());
            }
        });
        //添加广告栏
//        JPanel ad = new JPanel();
//        ad.setBackground(Color.WHITE);
//        ad.setBounds(400, 0, 100, 500);
//        main.add(ad);

        jFrame.addWindowListener(new WindowAdapter() {
            public void windowDeactivated(WindowEvent e) {
                jFrame.setVisible(true);
            }
        });
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            SwingUtilities.updateComponentTreeUI(jFrame);
        } catch (Exception e) {
            e.printStackTrace();
        }
        jFrame.setVisible(true);

        initSocket();
    }

    public void initSocket() {
        new Thread(() -> {
            try {
                System.out.println("正在连接");
                socketChannel = SocketChannel.open();
                socketChannel.configureBlocking(false);
                socketChannel.connect(new InetSocketAddress("192.168.10.153", 7000));
                Selector selector = Selector.open();
                socketChannel.register(selector, SelectionKey.OP_CONNECT);
                System.out.println("已连接");
                while (true) {
                    int select = selector.select();
                    if (select > 0) {
                        Set<SelectionKey> selectedKeys = selector.selectedKeys();
                        for (SelectionKey key : selectedKeys) {
                            SocketChannel client = (SocketChannel) key.channel();
                            if (key.isConnectable()) {
                                if (client.isConnectionPending()) {
                                    client.finishConnect();
                                }
                                client.register(selector, SelectionKey.OP_READ);
                            } else if (key.isReadable()) {
                                byteBuffer.clear();
                                socketChannel.read(byteBuffer);

                                String data = new String(byteBuffer.array(), 0, byteBuffer.position());
                                System.out.println("读取：" + data);
                                //if( key.channel()!= socketChannel){
                                onRead(data);
                                client.register(selector, SelectionKey.OP_READ);
                                //}
                            }
                        }
                        selectedKeys.clear();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public static void main(String[] args) {
        new Chat().init();
    }

    public void onRead(String str) {
        dlm.addElement(new Message(str, "", "other"));
    }

    public void onWrite(String msg) {
        try {

            this.byteBuffer.clear();
            this.byteBuffer.put(msg.trim().getBytes());
            this.byteBuffer.put("\n".getBytes());
            this.byteBuffer.flip();
            this.socketChannel.write(this.byteBuffer);
            sendTextMsg.setText("");
            this.sendTextMsg.setCaretPosition(0);
            this.byteBuffer.clear();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
