package cn.com.gatico.TestThread;

import java.util.concurrent.CountDownLatch;

public class TestCountDownLatch {
    public static void main(String[] args) {
        CountDownLatch countDownLatch1 = new CountDownLatch(1);
        CountDownLatch countDownLatch2 = new CountDownLatch(12);
        for (int i = 0; i < 12; i++) {
            new Thread(() -> {
                try {
                    countDownLatch2.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("第二批："+System.nanoTime() + ":" + Thread.currentThread().getName());
            }).start();
        }

        for (int i = 0; i < 12; i++) {
            new Thread(() -> {
                try {
                    countDownLatch1.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("第一批："+System.nanoTime() + ":" + Thread.currentThread().getName());
                countDownLatch2.countDown();
            }).start();
        }
        countDownLatch1.countDown();
        System.out.println("main end");
    }
}
