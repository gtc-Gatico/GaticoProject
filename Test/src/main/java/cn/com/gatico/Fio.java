package cn.com.gatico;

import java.nio.channels.FileChannel;

public class Fio {
    public static void main(String[] args) {
        int x = 50;
        long b = System.currentTimeMillis();
        System.out.println(fio1(x));
        long e = System.currentTimeMillis();
        System.out.println(e - b);
        b = System.currentTimeMillis();
        System.out.println(fio2(x));
        e = System.currentTimeMillis();
        System.out.println(e - b);
        System.out.println(count);

    }

    //fn = f(n-1)+ f(n-2)
    //f1=1;
    //f2=1
    //1、1、2、3、5、8、13、21、34
    public static long fio1(int x) {
        long a1 = 1, a2 = 1, a3 = 0;
        if (x <= 2) {
            return 1;
        }
        for (int i = 3; i <= x; i++) {
            a3 = a1 + a2;
            a1 = a2;
            a2 = a3;
        }
        return a3;
    }

    static long count = 0;

    public static long fio2(int x) {
        count++;
        if (x <= 2) {
            return 1;
        }
        return fio2(x - 1) + fio2(x - 2);
        //1,2,
        //1,1,
    }
}
