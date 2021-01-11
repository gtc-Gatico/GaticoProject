package cn.com.gatico.日历;

import java.text.SimpleDateFormat;
import java.util.BitSet;
import java.util.Calendar;

public class Test {
    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        System.out.println(calendar.after(calendar));
        System.out.println(new SimpleDateFormat("yyyy-MM-dd@HH:mm:ss").format(calendar.getTime()).replace("@","T")+"Z");

        BitSet bitSet = new BitSet();
        bitSet.set(80,100);
        System.out.println(bitSet.toString());
        System.out.println(bitSet.get(88));
    }
}
