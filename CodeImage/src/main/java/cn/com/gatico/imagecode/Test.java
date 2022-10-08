package cn.com.gatico.imagecode;


import com.alibaba.fastjson.JSONObject;

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
        int size = 400;
        int imgSize = 500;
        BufferedImage bufferimg = new BufferedImage(imgSize, imgSize, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bufferimg.createGraphics();
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, imgSize, imgSize);
        g2d.setColor(Color.BLACK);
        Square[][] arr = new Square[size / 5][size / 5];
        for (int i = ((imgSize - size) / 2); i < size + ((imgSize - size) / 2); i += 5) {
            g2d.drawLine(i, ((imgSize - size) / 2), i, size + ((imgSize - size) / 2));
            g2d.drawLine(((imgSize - size) / 2), i, size + ((imgSize - size) / 2), i);
        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (i == 0 && j == 0) {
                    Square square = new Square();
                    square.setX(i * 5);
                    square.setY(j * 5);
                    square.setP(square);
                    arr[i][j] = square;
                } else if (j == 0) {
                    Square square = new Square();
                    square.setX(i * 5);
                    square.setY(j * 5);
                    square.setP(arr[i - 1][arr[i].length - 1]);
                    arr[i][j] = square;
                } else {
                    Square square = new Square();
                    square.setX(i * 5);
                    square.setY(j * 5);
                    square.setP(arr[i][j - 1]);
                    arr[i][j] = square;
                }
                if (i == j) {
                    Square square = arr[i][j];
                    square.setB(1);
                    arr[i][j] = square;
                }
                if (i + 1 == j) {
                    Square square = arr[i][j];
                    square.setL(1);
                    arr[i][j] = square;
                }
                g2d.drawRect(i, j, size, size);
            }
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data", arr);
        System.out.println(jsonObject.toJSONString());
        g2d.drawRect(((imgSize - size) / 2), ((imgSize - size) / 2), size, size);
        bufferimg.flush();
        try {
            ImageIO.write(bufferimg, "png", new File("D:\\test2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void drawSquare(Graphics2D graphics2D, Square square) {
        graphics2D.setColor(Color.BLACK);
        graphics2D.drawRect(square.x,square.y,5,5);
        graphics2D.setColor(Color.BLACK);
        if(square.b==1){
            graphics2D.drawLine(square.x,square.y+5,square.x+5,square.y+5);
        }
        if(square.r==1){
            graphics2D.drawLine(square.x,square.y+5,square.x+5,square.y+5);
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
