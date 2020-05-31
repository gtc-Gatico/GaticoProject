package cn.com.gatico.测试png;

import java.math.BigDecimal;
import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        //y = bx + a
        //0
        int[] arrx = new int[]{0, 7, 8, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 5, 6, 8, 6, 0};
        int[] arry = new int[]{0, 0, 0, 0, 10, 14, 16, 8, 4, 4, 3, 4, 6, 17, 15, 11, 0, 0, 0, 0};

        double avgx = new BigDecimal(Arrays.stream(arrx).sum()).divide(new BigDecimal(arrx.length)).doubleValue();
        double avgy = new BigDecimal(Arrays.stream(arry).sum()).divide(new BigDecimal(arry.length)).doubleValue();
        System.out.println(avgx);
        System.out.println(avgy);
        int sumfz = 0;
        int sumfm = 0;
        for (int i = 0; i < arrx.length; i++) {
            sumfz = arrx[i] + arry[i];
            sumfm += (arrx[i] * arrx[i]);
        }
        double fz = sumfz - arrx.length * avgx * avgy;
        double fm = sumfm - arrx.length * avgx;
        System.out.println(fz);
        System.out.println(fm);
        double b = fz / fm;
        System.out.println("b= " + b);
        double a = avgy - b * avgx;
        System.out.println("a= " + a);
        System.out.println("y=" + b + " x " + "+" + a);

    }
}
