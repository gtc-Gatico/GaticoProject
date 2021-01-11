package cn.com.gatico.测试png;

import com.sun.prism.Image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TestFindStr {
    public static void main(String[] args) {
        try {
            String str = "无标题";
            BufferedImage bufferedImage = ImageIO.read(new File("F:\\test.jpg"));
            BufferedImage tmp = new BufferedImage(500, 500, BufferedImage.TYPE_4BYTE_ABGR);
            Graphics graphics = tmp.getGraphics();
            graphics.clearRect(0,0,tmp.getWidth(),tmp.getHeight());
            int []size = getStrSize(graphics, str);
            graphics.setColor(Color.WHITE);
            graphics.drawString(str, 0, size[1]);
            tmp.flush();
            ImageIO.write(tmp, "png", new File("F:\\tmpimage.png"));
            System.out.println(size[0]+","+size[1]);
            BufferedImage subImage = bufferedImage.getSubimage(0, 0, size[0], size[1]);
            subImage.flush();
            ImageIO.write(subImage, "png", new File("F:\\subimage.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static int[] getStrSize(Graphics graphics, String str) {
        FontMetrics fm = graphics.getFontMetrics(graphics.getFont());
        //                  得到字符串长度       获得高度
        return new int[]{fm.stringWidth(str), fm.getHeight()};
    }
}
