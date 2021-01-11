package cn.com.gatico.TestPattern;

import java.util.regex.Pattern;

public class Test {
    public static void main(String[] args) {
        System.out.println(Pattern.compile("^[A-Za-z0-9_,]+$").matcher("____,FCWE").matches());
    }
}
