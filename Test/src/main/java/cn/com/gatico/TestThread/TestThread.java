package cn.com.gatico.TestThread;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class TestThread {
    public static void main(String[] args) {
        Map map = new HashMap();
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入字符执行动作：1 Thread,2 Gc");
        while (scanner.hasNext()) {
            String str = scanner.next();
            if (str.equals("1")) {
                System.out.println(str + ",ok,run Thread");
                for (int i = 0; i < 100; i++) {
                    new Thread(() -> {
                        String str1 = new String();
                        for (int j = 0; j < 100; j++) {
                            str1 += getRandomString(10);
                        }
                        byte[] a = str1.getBytes();
                        ByteBuffer byteBuffer = ByteBuffer.allocate(a.length);
                        byteBuffer.put(a);
                        map.put(Thread.currentThread().getName(), byteBuffer);
                        System.out.println(Thread.currentThread().getName() + str1);
                    }).start();
                }
            } else if (str.equals("2")) {
                String str2 = scanner.next();
                System.out.println(str2 + ",ok,run Gc");
                System.gc();
            } else if (str.equals("3")) {
                System.out.println(map.size());
            } else if (str.equals("4")) {
                System.exit(1);
            }
        }

    }

    //length用户要求产生字符串的长度
    public static String getRandomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }
}
