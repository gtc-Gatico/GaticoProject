package cn.com.gatico.CMD;

import java.io.*;
import java.util.concurrent.TimeUnit;

/**
 * @author Gatico
 * @version 1.0
 * @date 2020/1/2 14:56
 */
public class Test {
    public static void main(String[] args) {
        String command = "ping 114.114.114.114 ";
        long timeout = 10*1000;
        System.out.println(command);
        byte[] isArr = null;
        byte[] esArr = null;
        ByteArrayOutputStream isOutputStream = new ByteArrayOutputStream();
        ByteArrayOutputStream esOutputStream = new ByteArrayOutputStream();
        try {
            Process exec = Runtime.getRuntime().exec(command);
            InputStream is = exec.getInputStream();
            InputStream es = exec.getErrorStream();
            exec.waitFor(100, TimeUnit.MILLISECONDS);
            Thread thread0 =  new Thread(() -> {
                try {
                    int v1;
                    while ((v1 = is.read()) != -1) {
                        isOutputStream.write(v1);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            thread0.setDaemon(true);
            thread0.start();
            Thread thread = new Thread(() -> {
                try {
                    int v2;
                    while ((v2 = es.read()) != -1) {
                        esOutputStream.write(v2);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            thread.setDaemon(true);
            thread.start();
            boolean flag = false;
            int count = 0;
            while (exec.isAlive() && count <= timeout / 100) {
                Thread.sleep(100);
                count++;
            }

            if (count >= timeout / 100) {
                flag = true;
            }
            isArr = isOutputStream.toByteArray();
            esArr = esOutputStream.toByteArray();
            if (flag) {
                esOutputStream.write("读取超时".getBytes("GBK"));
                esArr = esOutputStream.toByteArray();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String msg = "";
        String err = "";
        try {
            if (isArr != null && isArr.length >= 0) {
                msg = new String(new String(isArr, "GBK").getBytes("UTF8"),"UTF8");
            }
            if (esArr != null && esArr.length >= 0) {
                err =new String(new String(esArr, "GBK").getBytes("UTF8"),"UTF8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(msg);
        System.out.println("---------------------------------");
        System.out.println(err);
    }
}
