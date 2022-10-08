package cn.com.gatico;

import java.math.BigInteger;

public class BigDecimalTest {
    public static void main(String[] args) {
        BigInteger a = new BigInteger("344365755323989725024935102720949429993471");
        BigInteger b =a.and(BigInteger.ONE.shiftLeft(86));
        System.out.println("b = " + b);
        System.out.println(a.testBit(86));
    }
}