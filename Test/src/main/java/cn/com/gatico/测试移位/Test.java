package cn.com.gatico.测试移位;

public class Test {
    public static void main(String[] args) {
        System.out.println(String.format("%08d",1));
        System.out.println(Integer.toBinaryString(Integer.valueOf("ff",16)));
        int aa = (int) (1 & 0xff);
        System.out.println(aa);
        int num = Integer.MAX_VALUE;
        System.out.println(num);
        System.out.println(Integer.toBinaryString((num)));

        byte[] arr = itoba(num);
        System.out.println(batoi(arr));

        byte[] arr1 = IntToByte(num);
        System.out.println(Byte2Int(arr1));

        byte[] a = "👍".getBytes();
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);

        }
        System.out.println(batoi("👍".getBytes()) + "---------------");
        System.out.println((1024 >> 8));
        System.out.println((1024 >> 8) & 0xff);
    }

    public static byte[] itoba(int num) {
        byte[] arr = new byte[4];
        arr[0] = (byte) ((num >> 24) & 0xff);
        arr[1] = (byte) ((num >> 16) & 0xff);
        arr[2] = (byte) ((num >> 8) & 0xff);
        arr[3] = (byte) (num & 0xff);
        return arr;
    }

    public static int batoi(byte[] arr) {
        return (arr[0] & 0xff) << 24
                | (arr[1] & 0xff) << 16
                | (arr[2] & 0xff) << 8
                | (arr[3] & 0xff);
    }

    public static int Byte2Int(byte[] bytes) {
        return (bytes[0] & 0xff) << 24
                | (bytes[1] & 0xff) << 16
                | (bytes[2] & 0xff) << 8
                | (bytes[3] & 0xff);
    }

    public static byte[] IntToByte(int num) {
        byte[] bytes = new byte[4];
        bytes[0] = (byte) ((num >> 24) & 0xff);
        bytes[1] = (byte) ((num >> 16) & 0xff);
        bytes[2] = (byte) ((num >> 8) & 0xff);
        bytes[3] = (byte) (num & 0xff);
        return bytes;
    }

    public static byte[] i2b(int num) {
        byte[] arr = new byte[4];
        arr[0] =(byte)((num >> 24) & 0xff);
        return new byte[10];
    }
}
