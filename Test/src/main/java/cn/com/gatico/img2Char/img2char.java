package cn.com.gatico.img2Char;

import cn.com.gatico.utils.ImageUtil;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 * @author Gatico
 * @version 1.0
 * @date 2019/11/2 15:49
 */
public class img2char {
    public static void main(String[] args) throws Exception {

        File originalImage = new File("C:\\Users\\48909\\Desktop\\微信图片_20191031141346.jpg");
        File targetImage = new File("C:\\Users\\48909\\Desktop\\微信图片_20191031141346_2.jpg");
        ImageUtil.resize(originalImage, targetImage, 200, 150, 1f);
        BufferedImage bufferedImage = ImageIO.read(targetImage);
        Graphics2D graphics = bufferedImage.createGraphics();
        graphics.setColor(Color.WHITE);
        graphics.drawString("333", 20, 10);
        graphics.dispose();
//        int height = bufferedImage.getHeight();
//        int width = bufferedImage.getWidth();
//        StringBuffer stringBuffer = new StringBuffer("<html><body center ><div style=\"font-family:'Monospace';font-size: x-small;background-color:black;text-align:center;line-height: 40px;\"><pre>");
//        for (int h = 0; h < height; h++) {
//            for (int w = 0; w < width; w++) {
//                Color c = new Color(bufferedImage.getRGB(w, h));
//                stringBuffer.append("<font style=\"color:rgba(" + c.getRed() + "," + c.getGreen() + "," + c.getBlue() + "," + c.getAlpha() + ");\">" + "■" + "</font>");
//            }
//            stringBuffer.append("</br>");
//        }0
        bufferedImage.flush();
//        stringBuffer.append("</pre></div></body></html>");
//
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File("C:\\Users\\48909\\Desktop\\imgtest.html")));
//        bufferedWriter.write(stringBuffer.toString(), 0, stringBuffer.length());
//        bufferedWriter.flush();
//        bufferedWriter.close();
    }
}
