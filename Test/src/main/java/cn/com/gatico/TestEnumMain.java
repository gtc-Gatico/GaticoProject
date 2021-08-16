package cn.com.gatico;

import cn.com.gatico.enum2.TestEnums;
import cn.com.gatico.enum2.Test;

public class TestEnumMain {
    public static void main(String[] args) {
        System.out.println(TestEnums.GREEN.getCode());
        System.out.println(new Test().getEnums());
    }
}
