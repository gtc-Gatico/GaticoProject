package cn.com.gatico.learn;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LearnMain {
    public static void main(String[] args) {
        try {
            File file = new File(LearnMain.class.getResource("/").getPath() + "source/0.jpg");
            BufferedImage buff = ImageIO.read(file);
            System.out.println(buff.getWidth());
            System.out.println(buff.getHeight());

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(1);
    }
}
