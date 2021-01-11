package cn.com.gatico;


import cn.com.gatico.entity.Test2;

import java.nio.ByteBuffer;

public class TEST {
    private static ByteBuffer buffer = ByteBuffer.allocate(8);



    public static int countDigitOne(int n) {
        if(n==0){
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
        System.out.println(Integer.toBinaryString(5));
        System.out.println(Integer.toBinaryString(-6));
        System.out.println(Integer.toBinaryString(1));
        System.out.println(Integer.valueOf("010",2));


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
}
