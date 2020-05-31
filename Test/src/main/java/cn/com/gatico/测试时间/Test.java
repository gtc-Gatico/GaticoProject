package cn.com.gatico.测试时间;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {
    public static void main(String[] args) throws Exception {
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse("2020-04-07");
        String flowDate = new SimpleDateFormat("yyyyMM").format(date);
        System.out.println(flowDate);
    }
}
