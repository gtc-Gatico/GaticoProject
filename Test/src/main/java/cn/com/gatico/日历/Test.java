package cn.com.gatico.日历;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.TemporalField;
import java.util.BitSet;
import java.util.Calendar;

public class Test {
    public static void main(String[] args) {



        Instant instant = Instant.now();

        Calendar calendar = Calendar.getInstance();
        System.out.println(calendar.get(Calendar.WEEK_OF_YEAR));
        System.out.println(calendar.getWeekYear());
        calendar.set(Calendar.WEEK_OF_YEAR, calendar.get(Calendar.WEEK_OF_YEAR) - 1);
        calendar.set(Calendar.WEEK_OF_YEAR, calendar.getWeekYear() - 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        System.out.println(calendar.after(calendar));
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime()));

        BitSet bitSet = new BitSet();
        bitSet.set(80, 100);
        System.out.println(bitSet);
        System.out.println(bitSet.get(88));
    }
}
