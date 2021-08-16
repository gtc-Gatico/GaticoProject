package cn.com.gatico;

public class HashTest {
    public static void main(String[] args) {
        int n = 1026 - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        System.out.println(n+1);
        System.out.println(Integer.toBinaryString(2));
        System.out.println(Integer.toBinaryString(~2+1));
        System.out.println(Integer.toBinaryString(-2));
        System.out.println(Integer.toBinaryString(~-2+1));
        String str = "www.runoob.com";
        System.out.println(str.hashCode());
        System.out.println(getHash(str));
    }

    public static int getHash(String s) {
        char[] chars = s.toCharArray();
        int sum = 0;
        for (int i = 0; i < chars.length; i++) {
            sum = 31 * sum + chars[i];
        }
        return sum;
    }
}
