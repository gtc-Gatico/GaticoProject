package cn.com.gatico.testForeach;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        List<String> stringList = new ArrayList<>();
        stringList.add("11");
        stringList.add("12s");
        stringList.add("13");
        stringList.forEach(s -> {
            try {
                int res = Integer.parseInt(s);
                System.out.println(res);
            } catch (Exception ex) {
                System.out.println("ex");
            }
        });
    }
}
