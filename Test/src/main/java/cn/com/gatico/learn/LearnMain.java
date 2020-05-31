package cn.com.gatico.learn;

import com.alibaba.fastjson.JSONObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

public class LearnMain {
    public static void main(String[] args) {
        int x = 1;
        System.out.println(x++);
        System.out.println(x);
        Map<String, Object> map = new ConcurrentHashMap<>();
        try {
            for (int i = 0; i < 10; i++) {
                File file = new File(LearnMain.class.getResource("/").getPath() + "source/" + i + ".jpg");//字体
                //File file = new File("F:\\imgs\\tmp\\"+i+".png");//手写体
                BufferedImage buff = ImageIO.read(file);
                data data = getTmp(file.getPath());
                map.put(String.valueOf(i), data);
            }

            System.out.println(new JSONObject(map).toJSONString());


            //匹配项
            String name = "0";
            data tmpdata = getTmp("F:\\imgs\\tmp\\" + name + ".png");
            Map<String, Double> xsd = new ConcurrentHashMap<>();
            System.out.println("重心x：\t" + tmpdata.getZxx());
            System.out.println("重心y：\t" + tmpdata.getZxy());
            for (String key : map.keySet()) {
                data o = (data) map.get(key);
                int[] arrx = new int[o.getSumX().size()];
                for (int i = 0; i < arrx.length; i++) {
                    arrx[i] = o.getSumX().get(i);
                }
                int[] arry = new int[o.getSumY().size()];
                for (int i = 0; i < arry.length; i++) {
                    arry[i] = o.getSumY().get(i);
                }


                int[] tmpx = new int[tmpdata.getSumX().size()];
                for (int i = 0; i < tmpx.length; i++) {
                    tmpx[i] = tmpdata.getSumX().get(i);
                }

                int[] tmpy = new int[tmpdata.getSumY().size()];
                for (int i = 0; i < tmpy.length; i++) {
                    tmpy[i] = tmpdata.getSumY().get(i);
                }
                double dx = xiangliangjuli(key, arrx, tmpx);
                double dy = xiangliangjuli(key, arry, tmpy);
                System.out.println("投影相似度：" + key + ":" + (100 - (dx + dy) / 2));

                //重心相似度
                double zxx = Math.sqrt(Math.pow(o.getZxx() - tmpdata.getZxx(), 2));
                double zxy = Math.sqrt(Math.pow(o.getZxy() - tmpdata.getZxy(), 2));

                System.out.println("重心相似度：" + ((zxx + zxy) / 2));

                double sum = (100 - (dx + dy)) / 2 + (100 - ((zxx + zxy) / 2));
                xsd.put(key, sum / 200);
                //hanshishi(key, arrx, arry);
                //xiangliangjuli(key, arrx, arry);
            }

            System.out.println(name);
            xsd.forEach((s, aDouble) -> {
                System.out.println(s + " = " + aDouble);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static double xiangliangjuli(String key, int[] arrx, int[] arry) {
        //d = √（x1-y1)2
        double sum = 0;
        for (int i = 0; i < arrx.length; i++) {
            sum += (arrx[i] - arry[i]) * (arrx[i] - arry[i]);
        }
        return Math.sqrt(sum);
    }

    public static void hanshishi(String key, int[] arrx, int[] arry) {

        double avgx = new BigDecimal(Arrays.stream(arrx).sum()).divide(new BigDecimal(arrx.length)).doubleValue();
        double avgy = new BigDecimal(Arrays.stream(arry).sum()).divide(new BigDecimal(arry.length)).doubleValue();
        int sumfz = 0;
        int sumfm = 0;
        for (int i = 0; i < arrx.length; i++) {
            sumfz = arrx[i] + arry[i];
            sumfm += (arrx[i] * arrx[i]);
        }
        double fz = sumfz - arrx.length * avgx * avgy;
        double fm = sumfm - arrx.length * avgx;
        double b = fz / fm;
        double a = avgy - b * avgx;
        System.out.println(key + ":y=" + b + " x " + " + " + a);
    }


    public static data getTmp(String name) {
        try {
            File file = new File(name);//手写体
            BufferedImage buff = ImageIO.read(file);
            data data = new data();
            for (int j = 0; j < buff.getWidth(); j++) {
                for (int k = 0; k < buff.getHeight(); k++) {
                    Color c = new Color(buff.getRGB(j, k));
                    if (c.getRed() >= 127 && c.getGreen() >= 127 && c.getBlue() >= 127) {
                        buff.setRGB(j, k, Color.WHITE.getRGB());
                    } else {
                        buff.setRGB(j, k, Color.BLACK.getRGB());
                    }
                }
            }
            int yValue = 0;
            int yCount = 0;
            for (int j = 0; j < buff.getWidth(); j++) {
                int sumY = 0;
                for (int k = 0; k < buff.getHeight(); k++) {
                    //|||
                    Color c = new Color(buff.getRGB(j, k));
                    if (c.getRed() < 10 && c.getGreen() < 10 && c.getBlue() < 10) {
                        sumY++;
                        yValue += k;
                        yCount++;
                    }
                }
                data.sumY.add(sumY);
            }
            double avgy = yCount > 0 ? yValue / yCount : 0;
            data.setZxy(avgy / buff.getHeight());

            int xValue = 0;
            int xCount = 0;
            for (int j = 0; j < buff.getHeight(); j++) {
                int sumX = 0;
                for (int k = 0; k < buff.getWidth(); k++) {
                    //|||
                    Color c = new Color(buff.getRGB(k, j));
                    if (c.getRed() < 10 && c.getGreen() < 10 && c.getBlue() < 10) {
                        sumX++;
                        xValue += k;
                        xCount++;
                    }
                }
                data.sumX.add(sumX);
            }

            double avgx = xCount > 0 ? xValue / xCount : 0;
            data.setZxx(avgx / buff.getWidth());
            return data;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        LinkedHashMap<K, V> result = new LinkedHashMap<>();
        Stream<Map.Entry<K, V>> st = map.entrySet().stream();
        st.sorted(Comparator.comparing(e -> e.getValue())).forEach(e -> result.put(e.getKey(), e.getValue()));
        return result;
    }

    public static <K, V> Map.Entry<K, V> getHead(Map<K, V> map) {
        return map.entrySet().iterator().next();
    }
}

class data {
    double zxx;
    double zxy;
    List<Integer> sumY = new ArrayList<>();
    List<Integer> sumX = new ArrayList<>();

    public double getZxx() {
        return zxx;
    }

    public void setZxx(double zxx) {
        this.zxx = zxx;
    }

    public double getZxy() {
        return zxy;
    }

    public void setZxy(double zxy) {
        this.zxy = zxy;
    }

    public List<Integer> getSumY() {
        return sumY;
    }

    public void setSumY(List<Integer> sumY) {
        this.sumY = sumY;
    }

    public List<Integer> getSumX() {
        return sumX;
    }

    public void setSumX(List<Integer> sumX) {
        this.sumX = sumX;
    }
}