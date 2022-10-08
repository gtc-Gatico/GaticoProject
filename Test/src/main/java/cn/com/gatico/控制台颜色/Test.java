package cn.com.gatico.控制台颜色;

import cn.hutool.core.lang.Console;

import java.io.InputStream;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Test {
    public static void main(String[] args) throws Exception {
        String str = ColorFontPrint.convert("666",ColorFontPrint.BLUE);
        System.out.println(str);

        Set<String> set = new HashSet<>();
        set.add("select");
//        InputStream in = System.in;

//        char c;
//        StringBuffer line = new StringBuffer();
//        while ((c = (char) in.read()) != 0) {
//            line.append(c);
//            if (c == 10) {
//                System.out.print(line);
//                line.setLength(0);
//            }
//        }


    }
}
