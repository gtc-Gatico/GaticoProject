package cn.com.gatico;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Gatico
 * @version 1.0
 * @date 2020/1/19 14:55
 */
public class ListTest {
    public static void main(String[] args) {
        List<Temp> temps = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Temp temp = new Temp();
            if (i % 10 == 0) {
                temp.setTimestamp(new Timestamp(System.currentTimeMillis()).getTime());
                temp.setValue(null);
                temps.add(temp);
            } else {
                temp.setTimestamp(new Timestamp(System.currentTimeMillis()).getTime());
                temp.setValue((double) i);
                temps.add(temp);
            }

        }
        temps.forEach(temp -> {
            System.out.println(temp.getValue() != -1);
            System.out.println(isNotBlank(temp.getValue()));
            System.out.println(temp.getTimestamp() + "\t" + temp.getValue());
        });
    }

    public static boolean isNotBlank(Object target) {
        return !isBlank(target);
    }

    public static boolean isBlank(Object target) {
        if (String.valueOf(target).equals("null")) {
            return true;
        } else {
            Pattern pattern = Pattern.compile("^[\\s]*$");
            Matcher matcher = pattern.matcher(String.valueOf(target));
            return matcher.matches();
        }
    }
}
