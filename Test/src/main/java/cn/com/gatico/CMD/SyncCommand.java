package cn.com.gatico.CMD;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/**
 * @author Gatico
 * @version 1.0
 * @date 2020/1/9 18:13
 */
public class SyncCommand {
    public static void main(String[] args) {
        String command = "chdir";
        System.out.println(command);
        ByteBuffer byteBuffer = ByteBuffer.allocate(100);
        byte[] isArr = null;
        byte[] esArr = null;
        ByteArrayOutputStream isOutputStream = new ByteArrayOutputStream();
        ByteArrayOutputStream esOutputStream = new ByteArrayOutputStream();
        try {
            Process exec = Runtime.getRuntime().exec("cmd -start", new String[]{command});
            InputStream is = exec.getInputStream();
            InputStream es = exec.getErrorStream();
            new Thread(() -> {
                try {
                    int v1;
                    while ((v1 = is.read()) != -1) {
                        isOutputStream.write(v1);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
            new Thread(() -> {
                try {
                    int v2;
                    while ((v2 = es.read()) != -1) {
                        esOutputStream.write(v2);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
            while (exec.isAlive()) {
                Thread.sleep(100);
            }

            isArr = isOutputStream.toByteArray();
            esArr = esOutputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String msg = "";
        String err = "";
        try {
            if (isArr != null && isArr.length >= 0) {
                msg = new String(isArr, "GBK");
            }
            if (esArr != null && esArr.length >= 0) {
                err = new String(esArr, "GBK");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(msg);
        System.out.println("---------------------------------");
        System.out.println(err);
    }
}
