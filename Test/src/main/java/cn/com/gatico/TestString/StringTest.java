package cn.com.gatico.TestString;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author Gatico
 * @version 1.0
 * @date 2020/1/6 11:57
 */
public class StringTest {
    public static void main(String[] args) {
        String res = "212121";
        res = res.concat("213");
        System.out.println(res);

        ArrayList<Integer> objects = new ArrayList<>();
        objects.add(1);
        objects.add(2);
        objects.add(3);
        String join = objects.stream().map(Object::toString).collect(Collectors.joining(","));
        System.out.println(join);
        System.exit(1);

        String s = new String(new byte[]{123, 34, 117, 115, 101, 114, 73, 100, 34, 58, 34, 49, 48, 48, 48, 49, 34, 44, 34, 116, 121, 112, 101, 34, 58, 48, 125});
        System.out.println(s);

        System.out.println(",0,23".contains(",2"));

        String tmp = "1 2 3  4  5  6";
        System.out.println(tmp.trim());
        String[] setValues = tmp.split(" ");
        System.out.println(setValues.length);
        List<String> values = new ArrayList<>();
        for (int i = 0; i < setValues.length; i++) {
            System.out.println(setValues[i]);
            if (!setValues[i].equals("")) {
                values.add(setValues[i].trim());
            }
        }
        System.out.println(String.join(" ", values));
        System.out.println(tmp.replace(' ', '|'));

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
