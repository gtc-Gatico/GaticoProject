package cn.com.gatico.TestByte;


import java.io.*;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Test {
    public static void main(String[] args) throws Exception {


//        byte[] arr = intToByte(180);
//        for (int i = 0; i < arr.length; i++) {
//            System.out.print(arr[i] + " ");
//        }
//        System.out.println();
//        System.out.println(byteToInt(arr));
//        System.out.println();
//        ByteBuffer btf = ByteBuffer.allocate(8);
//        btf.putLong(4714162176l);
//        btf.flip();
//        byte[] array = btf.array();
//        for (int i = 0; i < array.length; i++) {
//            System.out.print(array[i] + " ");
//        }
//        System.out.println(btf.getLong());
//        String s1 = "111001001011110110100000111001101001100010101111111001011000001010111011111010011000000010111100";
//        byte[] abb = new byte[1024];
//        List<Byte> ll = new ArrayList<>();
//        int index = 0;
//        while (s1.length() > 0) {
//            s1.substring(index * 8, index * 9);
//
//        }


        Writer writer = new BufferedWriter(new FileWriter(new File("D://2进制文字.txt")));

        String string = "我们";
        Stream.of(string).map(String::getBytes).forEach(bytes -> {
            try {
                for (int i = 0; i < bytes.length; i++) {
                    String 字 = Integer.toBinaryString(bytes[i]);
                    System.out.println(字);
                    writer.write(字);
                    writer.write(" ");
                }
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        writer.close();


        byte ca = 'a';//97  01100001
        byte cb = 'b';//98  01100010
        byte cc = 'c';//99  01100011
        byte cd = 'd';//100  01100100

        int ci = 0;
        ci = ci | ca << 24;
        ci = ci | cb << 16;
        ci = ci | cc << 8;
        ci = ci | cd << 0;
        System.out.println(Integer.toBinaryString(ci));
        System.out.println(ci);

        byte[] str = new byte[4];
        str[3] = (byte) (ci & 0xff);
        str[2] = (byte) (ci >> 8 & 0xff);
        str[1] = (byte) (ci >> 16 & 0xff);
        str[0] = (byte) (ci >> 24 & 0xff);
        System.out.println(str.length);
        System.out.println(new String(str));

        Stream.of("高").map(String::getBytes).forEach(bytes -> {
            for (int i = 0; i < bytes.length; i++) {
                System.out.println(bytes[i] + " " + Integer.toBinaryString(bytes[i]));
            }
        });

        try {
            System.out.println(new String(new byte[]{(byte) 0b11101001, (byte) 0b10101011, (byte) 0b10011000}, "utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        byte[] zc = new byte[2];
        zc[0] = 0b01001111;
        zc[1] = 0b01100000;
        ByteBuffer byteBuffer0 = ByteBuffer.allocate(4);
        byteBuffer0.clear();
        byteBuffer0.put((byte) 0);
        byteBuffer0.put((byte) 0);
        byteBuffer0.put(zc);
        byteBuffer0.flip();
        int code = byteBuffer0.getInt();
        System.out.println((char) code);
        char c = '你';
        System.out.format("%s\n", (char) 0x4f60);
        System.out.println(Integer.toBinaryString((int) c));
        System.out.println(Integer.toBinaryString((byte) c));
        byte[] a = "你".getBytes();
        System.out.println(a.length);
        ByteBuffer byteBuffer = ByteBuffer.allocate(4);
        byteBuffer.clear();
        byteBuffer.putInt(20320);
        byteBuffer.flip();
        System.out.println(a.length);
        byte c1 = byteBuffer.get();
        byte c2 = byteBuffer.get();
        byte c3 = byteBuffer.get();
        byte c4 = byteBuffer.get();
        System.out.println("--" + Integer.toBinaryString(c1));
        System.out.println("--" + Integer.toBinaryString(c2));
        System.out.println("--" + Integer.toBinaryString(c3));
        System.out.println("--" + Integer.toBinaryString(c4));
//        System.out.println(String.format("%s", Integer.toBinaryString(byteBuffer.get())));
    }

    public static byte[] longToByte(long num) {
        byte[] arr = new byte[8];
        for (int j = 0; j < arr.length; j++) {
            arr[j] = (byte) ((num >> (8 - j - 1) * 8) & 0xFF);
        }
        return arr;
    }

    public static long byteToLong(byte[] b) {
        long value = 0;
        value |= b[7] << 56;
        value |= b[6] << 48;
        value |= b[5] << 40;
        value |= b[4] << 32;
        value |= b[3] << 24;
        value |= b[2] << 16;
        value |= b[1] << 8;
        value |= b[0] << 0;
        return value;
    }

    public static long BytesToLong(byte[] buffer) {
        long  values = 0;
        for (int i = 0; i < 8; i++) {
            values <<= 8;
            values|= (buffer[i] & 0xff);
        }
        return values;
    }

    public static int byteToInt(byte[] b) {
        int value = 0;
        for (int i = 0; i < 4; i++) {
            int shift = (4 - 1 - i) * 8;
            value += (b[i] & 0x000000FF) << shift;
        }
        return value;
    }

    /**
     * 字节数组转成int 4位
     *
     * @param b
     * @return
     */
    public static byte[] intToByte(int b) {
        return new byte[]{(byte) (b & 0xff), (byte) (b >> 8 & 0xff)};
    }

}
