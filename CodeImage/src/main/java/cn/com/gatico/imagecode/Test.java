package cn.com.gatico.imagecode;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Base64;

public class Test {
    public static void main(String[] args) {
        createCodeImg2();

    }

    public static void createCodeImg2() {
        int width = 400, height = 400;
        BufferedImage bufferimg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bufferimg.createGraphics();
        g2d.setBackground(Color.green);
        g2d.drawString("I love China",1,1);
        bufferimg.flush();
        try {
            ImageIO.write(bufferimg, "PNG", new File("/home/tianci.gao/test/test2.PNG"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createCodeImg() {
        try {
            int width = 400, height = 400;
            String test = "I love China ";
            byte[] arr = test.getBytes();
            String baseStr = Base64.getEncoder().encodeToString(arr);
            System.out.println(baseStr);
            byte[] arr2 = baseStr.getBytes();
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < arr2.length; i++) {
                System.out.print(arr2[i] + "\t" + String.format("%08d", Integer.valueOf(Integer.toBinaryString(arr2[i]))) + "\n");
                stringBuffer.append(String.format("%08d", Integer.valueOf(Integer.toBinaryString(arr2[i]))));
            }
            char[] bit = stringBuffer.toString().toCharArray();
            System.out.println(stringBuffer.toString());
            System.out.println();
            BufferedImage bufferimg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2D = bufferimg.createGraphics();
            System.out.println(bit.length);
            int size = (int) Math.round((Math.sqrt(bit.length)));

            for (int i = 0, y = 0; i < bit.length && y <= size; y++) {
                for (int x = 0; x <= size && i < bit.length; x++, i++) {
                    if (bit[i] == '1') {
                        g2D.setColor(Color.black);
                    } else {
                        g2D.setColor(Color.white);
                    }
                    g2D.fillRect(x * size, y * size, size, size);
                }
            }

            bufferimg.flush();
            ImageIO.write(bufferimg, "PNG", new File("/home/tianci.gao/test/test.PNG"));
            /*File file=new File("/tmp/设备操作系统统计表_" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+".xlsx");
            System.out.println(file.getAbsoluteFile());
            System.out.println("no file:"+file.exists());
            file.createNewFile();
            System.out.println("create file:"+file.exists());
            file.delete();
            System.out.println("delete file:"+file.exists());
            */
           /* Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.WEEK_OF_MONTH, calendar.get(Calendar.WEEK_OF_MONTH) - 1);
            System.out.println(calendar.getTime());
            System.out.println(calendar.getTimeInMillis());
            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime()));*/


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 将byte[]转为各种进制的字符串
     *
     * @param bytes byte[]
     * @param radix 基数可以转换进制的范围，从Character.MIN_RADIX到Character.MAX_RADIX，超出范围后变为10进制
     * @return 转换后的字符串
     */
    public static String binary(byte[] bytes, int radix) {
        return new BigInteger(1, bytes).toString(radix);// 这里的1代表正数
    }
}
