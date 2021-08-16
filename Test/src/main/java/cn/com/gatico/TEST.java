package cn.com.gatico;


import cn.com.gatico.entity.Test2;
import com.sun.org.apache.bcel.internal.util.ClassPath;
import jdk.nashorn.api.scripting.ScriptUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.nio.file.Paths;
import java.sql.SQLOutput;
import java.util.*;

public class TEST {
    private static ByteBuffer buffer = ByteBuffer.allocate(8);


    public static int countDigitOne(int n) {
        if (n == 0) {
            return n;
        }
        StringBuffer s = new StringBuffer();
        for (int i = 1; i <= n; i++) {
            s.append(i);
        }
        String s1 = s.toString();
        return s1.length() - s1.replaceAll("1", "").length();
    }

    public static void main(String[] args) throws Exception {
        String str1 = "通话";
        String str2 = "重地";
        Map map = new HashMap();
        map.put(str1, str1);
        map.put(str2, str2);
        Collection values = map.values();
        String str3 = (String)map.get(str2);
        System.out.println(str3);
        exit();
        System.out.println(UUID.randomUUID().toString());
        List<String> argsList = Arrays.asList(args);
        if(!argsList.isEmpty()){
            argsList.forEach(System.out::println);
        }

//        System.out.println(new File("Test/src/main/resources/application.properties").getAbsolutePath());
//        System.out.println(new File(Class.forName(TEST.class.getName()).getResource("/application.properties").getPath()).getPath());
        System.getProperties().load(new BufferedReader(new FileReader(Class.class.getResource("/").getPath()+"application.properties")));
        System.getProperties().setProperty("gtc1", "123");

        String[] data = new BufferedReader(new FileReader(new File("D:\\2进制文字.txt"))).readLine().split(" ");
        System.out.println(data.length);
        byte arr[] = new byte[data.length];
        for (int i = 0; i < data.length; i++) {
            String[] si = data[i].split("");
            for (int i1 = 0; i1 < si.length; i1++) {
                arr[i] = (byte)Integer.parseInt(si[i1]);
            }
            System.out.println(new String(arr));
        }
        System.out.println(System.getProperties().get("gtc"));
        System.out.println(System.getProperties().get("gtc2"));
        System.getProperties().forEach((o, o2) -> {
            System.out.print(o);
            System.out.println("\t" + o2);
        });
        System.getenv().forEach((s, s2) -> {
            System.out.print(s);
            System.out.println("\t" + s2);
        });

        System.out.println(Integer.toBinaryString(5));
        System.out.println(Integer.toBinaryString(-6));
        System.out.println(Integer.toBinaryString(1));
        System.out.println(Integer.valueOf("010", 2));


        //System.out.println(countDigitOne(20000));

//        Test2 test = Test2.class.newInstance();
//        test.setT2(11L);
//        System.out.println(test.getT2());
    }

    //byte 数组与 int 的相互转换
    public static int byteArrayToInt(byte[] b) {
        return b[3] & 0xFF |
                (b[2] & 0xFF) << 8 |
                (b[1] & 0xFF) << 16 |
                (b[0] & 0xFF) << 24;
    }

    public static byte[] intToByteArray(int a) {
        return new byte[]{
                (byte) ((a >> 24) & 0xFF),
                (byte) ((a >> 16) & 0xFF),
                (byte) ((a >> 8) & 0xFF),
                (byte) (a & 0xFF)
        };
    }

    //byte 数组与 long 的相互转换
    public static byte[] longToBytes(long x) {
        buffer.putLong(0, x);
        return buffer.array();
    }

    public static long bytesToLong(byte[] bytes) {
        buffer.put(bytes, 0, bytes.length);
        buffer.flip();//need flip
        return buffer.getLong();
    }

    private static int tableSizeFor(int c) {
        int x = 1 << 16;
        System.out.println(x + "=" + Integer.toBinaryString(x));
        int n = c - 1;
        System.out.println(n + "=" + Integer.toBinaryString(n));
        n |= n >>> 1;
        System.out.println(n + "=" + Integer.toBinaryString(n));
        n |= n >>> 2;
        System.out.println(n + "=" + Integer.toBinaryString(n));
        n |= n >>> 4;
        System.out.println(n + "=" + Integer.toBinaryString(n));
        n |= n >>> 8;
        System.out.println(n + "=" + Integer.toBinaryString(n));
        n |= n >>> 16;
        System.out.println(n + "=" + Integer.toBinaryString(n));
        return n;
    }
    public static void exit(){
        System.exit(0);
    }
}
