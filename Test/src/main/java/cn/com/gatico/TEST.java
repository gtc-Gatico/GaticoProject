package cn.com.gatico;


import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.ByteBuffer;
import java.util.*;

public class TEST  {
    public static void main(String[] args) {
        ComThread.InitSTA();

        ActiveXComponent xl = new ActiveXComponent("Excel.Application");
        try {
            System.out.println("version=" + xl.getProperty("Version"));
            System.out.println("version=" + Dispatch.get(xl, "Version"));
            Dispatch.put(xl, "Visible", new Variant(true));
            Dispatch workbooks = xl.getProperty("Workbooks").toDispatch();
            Dispatch workbook = Dispatch.get(workbooks, "Add").toDispatch();
            Dispatch sheet = Dispatch.get(workbook, "ActiveSheet").toDispatch();
            Dispatch a1 = Dispatch.invoke(sheet, "Range", Dispatch.Get,
                    new Object[]{"A1"}, new int[1]).toDispatch();
            Dispatch a2 = Dispatch.invoke(sheet, "Range", Dispatch.Get,
                    new Object[]{"A2"}, new int[1]).toDispatch();
            Dispatch.put(a1, "Value", "123.456");
            Dispatch.put(a2, "Formula", "=A1*2");
            System.out.println("a1 from excel:" + Dispatch.get(a1, "Value"));
            System.out.println("a2 from excel:" + Dispatch.get(a2, "Value"));
            Variant f = new Variant(false);
            Dispatch.call(workbook, "Close", f);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            xl.invoke("Quit", new Variant[]{});
            ComThread.Release();
        }


    }

    public static boolean check(int i) {
        return i > 10;
    }

    public static void out(int i) {
        System.out.println(i);
    }


    public static int numberOfWeakCharacters(int[][] properties) {
        int count = 0;
        int index = 1;
        while (index < properties.length) {
            for (int i = index; i < properties.length; i++) {
                if (intersection(properties[index - 1], properties[i])) {
                    count++;
                }
            }
            index++;
        }
        return count;
    }

    public static boolean intersection(int[] role1, int[] role2) {
        if (role1[0] > role2[0] && role1[1] > role2[1]) {
            return true;
        } else if (role1[0] < role2[0] && role1[1] < role2[0]) {
            return true;
        }
        return false;
    }

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

    public static void main1(String[] args) throws Exception {
        BitSet bitSet = new BitSet();

        String str1 = "通话";
        String str2 = "重地";
        System.out.println(str1.hashCode());
        System.out.println(str2.hashCode());
        Map<String, String> map = new HashMap<>();
        map.put(str1, str1);
        map.put(str2, str2);
        Collection<String> values = map.values();
        System.out.println(values);
        String str3 = map.get(str2);
        System.out.println(str3);
        exit();
        System.out.println(UUID.randomUUID().toString());
        List<String> argsList = Arrays.asList(args);
        if (!argsList.isEmpty()) {
            argsList.forEach(System.out::println);
        }

//        System.out.println(new File("Test/src/main/resources/application.properties").getAbsolutePath());
//        System.out.println(new File(Class.forName(TEST.class.getName()).getResource("/application.properties").getPath()).getPath());
        System.getProperties().load(new BufferedReader(new FileReader(Class.class.getResource("/").getPath() + "application.properties")));
        System.getProperties().setProperty("gtc1", "123");

        String[] data = new BufferedReader(new FileReader(new File("D:\\2进制文字.txt"))).readLine().split(" ");
        System.out.println(data.length);
        byte arr[] = new byte[data.length];
        for (int i = 0; i < data.length; i++) {
            String[] si = data[i].split("");
            for (int i1 = 0; i1 < si.length; i1++) {
                arr[i] = (byte) Integer.parseInt(si[i1]);
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

    public static void exit() {
        System.exit(0);
    }
}

