package cn.com.gatico.robot;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class RobotUtil {

    public static void main(String[] args) throws Exception {
        RobotUtil.robot.setAutoDelay(1);
        Thread.sleep(3000L);
        RobotUtil.robot.delay(1);
        System.exit(1);
        System.out.println();

        String tabType = "req";
        //tabType ="res";
        RobotUtil.robot.setAutoDelay(1);
        int index = 206;
        Map<String, String> methodName = new HashMap<>();
        methodName.put("GET", "查看");
        methodName.put("PUT", "修改");
        methodName.put("POST", "创建");
        methodName.put("DELETE", "删除");
        Thread.sleep(2000);
        String str = RobotUtil.getClipboard();
        List<String> collect = Arrays.stream(str.split("\n")).filter(s -> !s.isEmpty()).collect(Collectors.toList());
        int integer = 0;
        for (int i = 0; i < collect.size(); i++) {
            String s = collect.get(i);
            if (s.isEmpty()) {
                continue;
            }
            String[] s1 = s.split("\t");
            String method="";
            String url="";
            if(!s1[0].isEmpty()){
                integer = Integer.parseInt(s1[0]);
                if(s1.length<2){
                    continue;
                }
                url = s1[1].trim();
                method = s1[2].trim().toUpperCase();
            }else{
                url = s1[1].trim();
                method = s1[2].trim().toUpperCase();
            }
            method = method.replaceAll("\"", "");
            url = url.replaceAll("\\$\\{\\S*?}", "*");
            url = url.replaceAll("\"", "");
            url = url.replaceAll("/api", "");
            System.out.printf("%s(\"%s\", \"%s\", \"%s\", BigInteger.ONE.shiftLeft(%s)),%n"
                    , method.toUpperCase() + "_ENUM" + index++, methodName.get(method), url, method, ("" + integer));
        }

//        for (int i = 0; i < collect.size(); i++) {
//            if(collect.get(i).trim().startsWith("//")){
//                continue;
//            }
//            String[] tmp = collect.get(i).trim().replace("private", "").replace(";", "").replace("boxId", "cpeId").trim().split(" ");
//            System.out.println(tmp[0] + " " + tmp[1]);
//            RobotUtil.setClipboard(tmp[1]);
//            RobotUtil.keys(KeyEvent.VK_CONTROL, KeyEvent.VK_V);
//            RobotUtil.key(KeyEvent.VK_TAB);
//            System.out.print("参数名\t");
//            RobotUtil.sleep(500);
//
//            if (tabType.equals("res")) {
//                RobotUtil.setClipboard("");
//                RobotUtil.keys(KeyEvent.VK_CONTROL, KeyEvent.VK_V);
//                RobotUtil.key(KeyEvent.VK_TAB);
//                System.out.print("参数名二\t");
//                RobotUtil.sleep(500);
//            }
//
//            RobotUtil.setClipboard(tmp[0]);
//            RobotUtil.keys(KeyEvent.VK_CONTROL, KeyEvent.VK_V);
//            RobotUtil.key(KeyEvent.VK_TAB);
//            System.out.print("参数类型\t");
//            RobotUtil.sleep(500);
//
//            RobotUtil.setClipboard("True");
//            RobotUtil.keys(KeyEvent.VK_CONTROL, KeyEvent.VK_V);
//            RobotUtil.key(KeyEvent.VK_TAB);
//            System.out.print("必选\t");
//            RobotUtil.sleep(500);
//
//            RobotUtil.setClipboard("");
//            RobotUtil.keys(KeyEvent.VK_CONTROL, KeyEvent.VK_V);
//            RobotUtil.key(KeyEvent.VK_TAB);
//            System.out.print("描述\t");
//            RobotUtil.sleep(500);
//
//            RobotUtil.setClipboard("");
//            RobotUtil.keys(KeyEvent.VK_CONTROL, KeyEvent.VK_V);
//            if (i != collect.size() - 1) {
//                RobotUtil.key(KeyEvent.VK_TAB);
//            }
//            System.out.print("备注\t");
//            RobotUtil.sleep(500);
//
//            System.out.println();
//        }


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
