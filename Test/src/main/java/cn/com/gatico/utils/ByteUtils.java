package cn.com.gatico.utils;

import java.nio.ByteBuffer;

public class ByteUtils {
    public static void main(String[] args) {


        byte[]arr = intToByte(1024);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
        }
        System.out.println();
        System.out.println(byteToInt(arr));
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.putInt(1024);
        byteBuffer.flip();
        byteBuffer.mark();
        System.out.println(byteBuffer.getInt());
        byteBuffer.reset();
        System.out.print(byteBuffer.get());
        System.out.print(byteBuffer.get());
        System.out.print(byteBuffer.get());
        System.out.print(byteBuffer.get());
    }

    public static byte[] longToByte(long num) {
        byte[] arr = new byte[8];
        for (int j = 0; j < arr.length; j++) {
            arr[j] = (byte) ((num >> (8 - j - 1) * 8) & 0xFF);
        }
        return arr;
    }

    public static long bytesToLong(byte[] buffer) {
        long values = 0;
        for (int i = 0; i < 8; i++) {
            values <<= 8;
            values |= (buffer[i] & 0xff);
        }
        return values;
    }

    public static byte[] intToByte(int b) {
        return new byte[]{
                (byte) (b >> 24 & 0xff),
                (byte) (b >> 16 & 0xff),
                (byte) (b >> 8 & 0xff),
                (byte) (b & 0xff),
               };
    }

    public static int byteToInt(byte[] b) {
        int value = 0;
        for (int i = 0; i < 4; i++) {
            int shift = (4 - 1 - i) * 8;
            value += (b[i] & 0x000000FF) << shift;
        }
        return value;
    }
}
