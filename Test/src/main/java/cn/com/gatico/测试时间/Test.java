package cn.com.gatico.测试时间;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {
    public static void main(String[] args) throws Exception {
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse("2020-04-07");
        String flowDate = new SimpleDateFormat("yyyyMM").format(date);
        System.out.println(flowDate);

        try {
            BufferedReader br = new BufferedReader(new FileReader(new File("D:/ip.txt")));

            try (BufferedReader br1 = br) {
                br1.readLine();
            }

            StringBuffer stringBuffer = new StringBuffer();
            String tmp;
            br.lines().forEach(s -> {
                stringBuffer.append(s + "\n");
            });
            System.out.println(stringBuffer.toString());
        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }
}
