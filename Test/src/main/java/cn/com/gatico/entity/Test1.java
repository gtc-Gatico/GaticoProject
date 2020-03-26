package cn.com.gatico.entity;

import java.util.Arrays;

/**
 * @author Gatico
 * @version 1.0
 * @date 2019/11/19 10:29
 */
public class Test1 {

    public static void main(String[] args) {
        Arrays.asList("111").forEach(s -> {
            c:
            ;
            Arrays.asList(111, 222, 3333).forEach(integer -> {
                if (integer.equals(Integer.parseInt(s))) {
                    System.out.println(integer);
                } else {
                    System.out.println("？");
                    c:
                    ;
                }
                System.out.println("end");
            });
        });
    }

    private long a1;

    public long getA1() {
        return a1;
    }

    public void setA1(long a1) {
        this.a1 = a1;
    }
}
