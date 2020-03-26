package cn.com.gatico;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Gatico
 * @version 1.0
 * @date 2020/1/6 11:57
 */
public class StringTest {
    public static void main(String[] args) {
        String str = "WTCCN-SC-6153";
        //str = "上海仓库";
        Pattern pattern = Pattern.compile("\\D+");
        Matcher matcher = pattern.matcher(str);
        boolean matches = matcher.matches();
        System.out.println(matches);
        System.out.println(Pattern.matches("\\D+", str));

        boolean b1 = false;
        boolean b2 = false;
        if (!(b1 && b2)) {
            System.out.println("11");
        }

        b1 = false;
        b2 = false;
        if (b1 && b2) {
            System.out.println("22");
        } else {
            System.out.println("11");
        }
    }
}
