package cn.com.gatico.robot;

import com.sun.imageio.plugins.common.ImageUtil;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Iterator;
import java.util.UUID;
import java.util.logging.Logger;

public class RobotUtil {


    public static void main(String[] args) throws Exception {
        RobotUtil.getImageForUrl("https://gatico.com.cn/image/icon2.gif");
//        String str[] = {
//                " 今天的你也是宇宙独一无二的可爱，与众不同的可爱，表里如一的可爱，迷倒万千少男的可爱，说了再见也还是挥之不去的可爱，世界第一未解之谜，万千科学家用尽一生，所有的科学道理也解释不出的可爱◡̈⃝", "想给你从清晨点赞到日暮，从北极跨越到南极，你放心去飞(•̀⌓•́)！", "你像夏至的分界线，是我一生里最长的那个白天", "上一秒想问你牛奶糖果布丁巧克力果汁甜甜圈冰淇淋要哪一个，下一秒算了算了全都给你", "你的眼中有春与秋，胜过我见过爱过的一切山川与河流。", " | ᐕ)⁾⁾ 突然明白这世上所有丹青水墨，山遥水阔，都是为了铺垫你这人间绝色", "月亮藏进云里，如果你已经睡了，那我要偷偷吻吻你。你比一百只猫猫加起来可爱，我呢，比一百只猫猫加起来好睡，你要不要来试试 (//∇//)", "今天是九宫格装不下的好看呀•͈˽•͈", "老天爷，就让我嫁给他吧。•́.•̀ 拜托拜托，我好喜欢他，他是宇宙最可爱的小男孩", " 快停止发散魅力吧୧⍢⃝୨ ，你这个浑身充满魅力的家伙", "我的心是旷野的鸟，在你的眼里找到了天空", "盛意以山河，山河不及你", "如果你穿越回古希腊时期，那么就能避免那喀索斯的悲剧了吧！”(那喀索斯因为长得太好看而爱上水中自己的倒影后溺亡)", " 想给自己颁一个最佳进步奖，毕竟我每天都爱你比昨天多一点₍˄·͈༝·͈˄₎ฅ˒˒", " 你称得上一切美好 你就是美好本身☪︎ ⋆｡˚ ✩", " 这个世界美好的事情不多，立秋傍晚河对岸吹来的风，和笑起来甜得要命的你", " 万物枯荣皆为你眼，我目眩神驰。而你一笑清明，潦倒我的众生。", "如果每一片空气，都带着可以看见的味道，那你周围就是我最爱的水蜜桃软糖味儿( ⁼̴̀ .̫ ⁼̴́ )", "你的侧脸应该制定为国家一级宝物", "你太自私了，每次你发自拍，会有多少人睡不着觉，你不在乎，有多少人饱受相思之痛，你不在乎，有多少人承受着所爱不得之苦，你不在乎，你只在乎你自己，就像我只在乎你一样自私", "老公好帅 老公的腿不是腿 是塞纳河畔的春水 老公的背不是背 是保加利亚的玫瑰 哥哥的皮肤是华东平…是光 逃不出声音的引力 老公的腹肌是丘陵 一不小心就摔在里面 老公的手指是麻醉针 碰一下，便沉醉了", "这是什么绝世美少女，万年一遇的颜啊，今天也是仔细体味姐姐盛世美颜的一天。姐姐的眼眸就像珍宝匣里最…是真实存在的珍宝吗 ？我看姐姐是从拉斐尔画里走出来的自带光环的绝世贵族吧！所以上帝才会更偏心一些！", "第一次看到宇宙，是和你四目相对的时候。", "你是空气中的橘子汽水，你是自由而不必被抓住的风☄", "哇！这发光的美貌是真实的吗？ (๑ ꒪̇ꌂ̇꒪̇๑)明明可以靠颜值，偏偏要靠才华！存在就是完美本…给他买了只导盲犬！你这辈子最遗憾的事就是没法亲吻自己好看到爆炸的脸蛋……今天的美貌也认真工作了呢~", "原本觉得困难的事，只要看看你呀，就觉得还能再坚持一下", "你长得这么好看为什么不当我男朋友ʚ⃛ɞ？", "世界需要讲讲道理 但我最偏心你(人 •͈ᴗ•͈) ♡♡", "脸蛋的第四次革命让地球不知道如何是好的脸蛋天才 神界的美貌 存在本身就可以创造幸福的幸福病毒 全体人类的希望爱情今天也因为xxx变得幸福了", "你叫冬天❄️，我就喜欢冬天，你叫夏天 ，我就喜欢夏天，看吧，你叫什么没关系，主要是我喜欢你。", " ̑̑ෆ⃛ 你简直是人类美学的奇迹✨", "嘴甜是因为心甜⚗︎·̫⚗︎ 心甜是因为你在里面", "我看什么都像你，我看月亮 ，像你，看星星⭐️，也像你。那些白亮透澈、温柔冷清的光，它们都让我想起你。其实我不太懂喜欢，可我想走向你 ´•ﻌ•`"
//        };
//
//        Thread.sleep(5000);
//        System.out.println("开始");
//        RobotUtil.showTip("点击确定开始");
//        for (int i = 0; i < str.length ; i++) {
//            RobotUtil.setClipboard(str[i]);
//            RobotUtil.keys(KeyEvent.VK_CONTROL, KeyEvent.VK_V);
//            RobotUtil.key(KeyEvent.VK_ENTER);
//            System.out.println(str[i]);
//            RobotUtil.sleep(500);
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
            ImageIO.write(bufferedImage, "jpg", new File("D:\\" + UUID.randomUUID().toString().replace("-","")+".jpg" ));
            System.out.println("ok");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
