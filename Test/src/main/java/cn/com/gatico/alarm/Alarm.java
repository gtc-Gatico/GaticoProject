package cn.com.gatico.alarm;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

/**
 * @author Gatico
 * @version 1.0
 * @date 2019/11/6 16:41
 */
public class Alarm {
    public static void main(String[] args) {

        String c = "tasklist ";
        try {
            Process p = Runtime.getRuntime().exec(new String[]{"cmd", "/c", c});
            InputStream is = p.getErrorStream();
            InputStream is2 = p.getInputStream();
            OutputStream os = p.getOutputStream();
            while (p.isAlive()) {
                System.out.println(p.isAlive());
                Thread.sleep(100);
            }
            if (is.available() > 0) {
                char[] arr = new char[is.available()];
                new BufferedReader(new InputStreamReader(is)).read(arr);
                System.out.println(new String(arr));
            }

            if (is2.available() > 0) {
                int i;
                byte[] arr = new byte[is2.available()];
                is2.read(arr);
                //System.out.println(arr.toString());
                System.out.println(new String(arr, "GBK"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
