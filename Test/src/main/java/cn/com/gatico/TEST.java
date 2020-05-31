package cn.com.gatico;


public class TEST {
    public static void main(String[] args) {
        System.out.println(1 << 16);
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
