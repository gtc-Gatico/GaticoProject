package cn.com.gatico;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TestDate {
    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        if (calendar.get(Calendar.DAY_OF_MONTH) == 1 && calendar.get(Calendar.HOUR) <= 1) {
            String to = new SimpleDateFormat("yyyyMM01").format(new Date());
            calendar.set(Calendar.MONTH,calendar.get(Calendar.MONTH) -1);
            String from = new SimpleDateFormat("yyyyMM01").format(calendar.getTime());
            System.out.println("to = " + to);
            System.out.println("from = " + from);
        }
    }
}
