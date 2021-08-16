package cn.com.gatico.TestFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;

public class Test {
    public static void main(String[] args) throws Exception {
        File file = new File("D:\\test.txt");
        byte[] arr = new byte[(int)file.length()];
        new FileInputStream(file).read(arr, 0, arr.length);
        System.out.println(new String(arr));

        System.out.println(String.format("2021-04-08 %02d", 1));
        System.out.println(String.format("2021-04-08 %.3f", 1.1539));
        System.out.println(String.format("%tF %<tT", new Date()));

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, 0);

        int year = calendar.get(Calendar.YEAR);
        System.out.println(year);
        int month = calendar.get(Calendar.MONTH) + 1;
        System.out.println(year + String.format("%02d", month));

        calendar.add(Calendar.MONTH, -1);
        year = calendar.get(Calendar.YEAR);
        System.out.println(year);
        month = calendar.get(Calendar.MONTH) + 1;
        System.out.println(year + String.format("%02d", month));


    }
}
