package cn.com.gatico.robot;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class RobotUtil1 {


    public static void main(String[] args) throws Exception {

        String tabType = "req";
        //tabType ="res";
        RobotUtil1.robot.setAutoDelay(1);

        Thread.sleep(2000);
        String str ="  `id` bigint(20) '小盒子ID',\n" +
                "  `serial_number` varchar(255) '序列号',\n" +
                "  `alias` varchar(255) '别名',\n" +
                "  `model` varchar(50) '盒子型号',\n" +
                "  `create_time` datetime '出厂时间',\n" +
                "  `active_status` tinyint(1) '当前状态：inactive|connected|disconnected|repairing',\n" +
                "  `active_time` datetime '激活时间',\n" +
                "  `software_version` varchar(255) '软件版本',\n" +
                "  `wan_count` int(2) 'WAN口数量',\n" +
                "  `oauth_password` varchar(255) 'oauth密码',\n" +
                "  `user_id` bigint(20) '所属用户ID（security_user.id）',\n" +
                "  `server_addr` varchar(100) '服务器地址（域名或IP）',\n" +
                "  `server_port` bigint(10) '服务器端口',\n" +
                "  `is_enable` tinyint(1) '是否可用',\n" +
                "  `removed` tinyint(1) '标记删除',\n" +
                "  `need_sync` bigint(63) '同步标志位',\n" +
                "  `support_4g` tinyint(1) '4G开关',\n" +
                "  `support_5g` tinyint(1) '5G开关',\n" +
                "  `hope_id` bigint(20) 'emid',\n" +
                "  `status` varchar(10) '状态（102：在线、103：离线）',\n" +
                "  `level_id` bigint(20) '等级Id',\n" +
                "  `alarm_authority` bigint(20) '告警权限',\n" +
                "  `enable_remote` tinyint(1) '是否开启远程调试',\n" +
                "  `comment` varchar(255) '备注',\n" +
                "  `authority` longtext '功能包',\n";
        List<String> collect = Arrays.stream(str.split("\n")).filter(s -> !s.isEmpty()).collect(Collectors.toList());
        for (int i = 0; i < collect.size(); i++) {
            if (collect.get(i).trim().startsWith("//")) {
                continue;
            }
            String[] tmp = collect.get(i).trim()
                    .replace("`", "")
                    .replace("'", "")
                    .replace(",", "")
                    .trim()
                    .split(" ");

            RobotUtil.setClipboard(tmp[0]);
            RobotUtil.keys(KeyEvent.VK_CONTROL, KeyEvent.VK_V);
            RobotUtil.key(KeyEvent.VK_TAB);
            System.out.print("参数名\t");
            RobotUtil.sleep(500);

            RobotUtil.setClipboard(tmp[1]);
            RobotUtil.keys(KeyEvent.VK_CONTROL, KeyEvent.VK_V);
            RobotUtil.key(KeyEvent.VK_TAB);
            System.out.print("参数类型\t");
            RobotUtil.sleep(500);

            RobotUtil.setClipboard(tmp[2]);
            RobotUtil.keys(KeyEvent.VK_CONTROL, KeyEvent.VK_V);
            RobotUtil.key(KeyEvent.VK_TAB);
            System.out.print("描述\t");
            RobotUtil.sleep(500);

            RobotUtil.setClipboard("");
            RobotUtil.keys(KeyEvent.VK_CONTROL, KeyEvent.VK_V);
            RobotUtil.key(KeyEvent.VK_TAB);
            System.out.print("备注\t");
            if (i != collect.size() - 1) {
                RobotUtil.key(KeyEvent.VK_TAB);
            }
            RobotUtil.sleep(500);
//
//            //RobotUtil.key(KeyEvent.VK_TAB);
//            RobotUtil.sleep(500);
//
//            System.out.println();
        }
    }

    static Logger logger = Logger.getGlobal();

    /**
     * 自动化工具：自动操作鼠标、键盘等
     * 包含鼠标单击、双击、右击、按键、组合按键、剪贴板、提示框、提示音、截屏、屏幕大小、坐标等等。
     *
     * @author cheng839
     * @2018年11月16日
     */
    private static Robot robot = null;

    static {
        try {
            robot = new Robot();
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (AWTException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 点击
     *
     * @param x
     * @param y
     * @author cheng2839
     * @2018年11月16日
     */
    public static void leftClick(int x, int y) {
        robot.mouseMove(x, y);
        robot.mousePress(16);
        sleep(10L);
        robot.mouseRelease(16);
    }

    /**
     * 右击
     *
     * @param x
     * @param y
     * @author cheng2839
     * @2018年11月16日
     */
    public static void rightClick(int x, int y) {
        robot.mouseMove(x, y);
        robot.mousePress(8);
        sleep(10L);
        robot.mouseRelease(8);
    }

    /**
     * 按键
     *
     * @param keyCode
     * @author cheng2839
     * @2018年11月16日
     */
    public static void key(int keyCode) {
        robot.keyPress(keyCode);
        sleep(10L);
        robot.keyRelease(keyCode);
    }

    /**
     * 组合按键
     *
     * @param keyCodes
     * @author cheng2839
     * @2018年11月16日
     */
    public static void keys(int... keyCodes) {
        for (int i = 0; i < keyCodes.length; i++) {
            robot.keyPress(keyCodes[i]);
        }
        sleep(10L);
        for (int i = keyCodes.length - 1; i >= 0; i--) {
            robot.keyRelease(keyCodes[i]);
        }
    }

    /**
     * 获取当前鼠标的坐标
     *
     * @return
     * @author cheng2839
     * @2018年11月16日
     */
    public static Point x_y() {
        return MouseInfo.getPointerInfo().getLocation();
    }

    /**
     * 获取剪贴板内容
     *
     * @return
     * @author cheng2839
     * @2018年11月16日
     */
    public static String getClipboard() {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable content = clipboard.getContents(null);
        if (content.isDataFlavorSupported(DataFlavor.stringFlavor)) {
            try {
                return (String) content.getTransferData(DataFlavor.stringFlavor);
            } catch (UnsupportedFlavorException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 提示音
     *
     * @author cheng2839
     * @2018年11月16日
     */
    public static void beep() {
        Toolkit.getDefaultToolkit().beep();
    }

    /**
     * 设置剪贴板
     *
     * @param data
     * @author cheng2839
     * @2018年11月16日
     */
    public static void setClipboard(String data) {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        StringSelection selection = new StringSelection(data);
        clipboard.setContents(selection, null);
    }

    /**
     * 鼠标移动监听
     *
     * @param mills
     * @author cheng2839
     * @2018年11月16日
     */
    public static void mouseMove(long mills) {
        long time = System.currentTimeMillis();
        Point p = x_y();
        while (System.currentTimeMillis() - time <= mills) {
            Point newp = x_y();
            if ((newp.x != p.x) || (newp.y != p.y)) {
                logger.info("( " + newp.x + ", " + newp.y + " )");
            }
            p = newp;
            sleep(100L);
        }
    }


    /**
     * sleep
     *
     * @param millis
     * @author cheng2839
     * @2018年11月16日
     */
    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 屏幕大小
     *
     * @return
     * @author cheng2839
     * @2018年11月16日
     */
    public static Dimension getScreenSize() {
        return Toolkit.getDefaultToolkit().getScreenSize();
    }

    /**
     * 弹框提示
     *
     * @param message
     * @author cheng2839
     * @2018年11月16日
     */
    public static void showTip(String message) {
        JOptionPane.showMessageDialog(null, message, "tip", JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * 截取全屏并保存为图片
     *
     * @param imgPath
     * @return
     * @author cheng2839
     * @2018年11月16日
     */
    public static File getScreenImg(String imgPath) {
        long b = System.currentTimeMillis();
        BufferedImage image = robot.createScreenCapture(new Rectangle(getScreenSize()));
        long e = System.currentTimeMillis();
        System.out.println(e - b);
        try {
            File imgFile = new File(imgPath);
            ImageIO.write(image, "PNG", imgFile);
            return imgFile;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        System.out.println(System.currentTimeMillis() - e);
        return null;
    }

    public static void getImageForUrl(String url) {
        try {
            BufferedImage bufferedImage = ImageIO.read(new URL(url));
            ImageIO.write(bufferedImage, "jpg", new File("D:\\" + UUID.randomUUID().toString().replace("-", "") + ".jpg"));
            System.out.println("ok");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
