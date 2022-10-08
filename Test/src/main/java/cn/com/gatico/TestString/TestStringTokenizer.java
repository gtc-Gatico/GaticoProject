package cn.com.gatico.TestString;

import java.util.StringTokenizer;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

public class TestStringTokenizer {
    public static void main(String[] args) throws Exception {
        StringTokenizer stringTokenizer = new StringTokenizer("111 12122121         wdqwdqwd        wdqwd       " +
                "wqdqwd\nwdqwqdqwdqw");

        while (stringTokenizer.hasMoreElements()) {
            System.out.println(stringTokenizer.nextToken());
        }
        AtomicInteger a = new AtomicInteger(1);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println(System.currentTimeMillis());
                while (a.get() < 100) {
                    a.getAndAdd(1);
                }
                a.set(0);
            }
        }, 0, 1000);
    }
}
