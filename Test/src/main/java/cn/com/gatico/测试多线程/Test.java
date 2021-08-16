package cn.com.gatico.测试多线程;

import cn.com.gatico.控制台颜色.ColorFontPrint;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class Test {
    public static long e = 0L;
    public static long b = 0L;
    public static boolean flag = false;
    public static long c = 0;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue();
        linkedBlockingQueue.poll();
        List<Object> obj = new ArrayList<>();
        obj.add(1);
        obj.add(2);
        obj.add(3);
        obj.add(4);
        obj.add(5);
        obj.add(6);
        obj.add(7);
        obj.add(8);
        obj.add(9);
        obj.add(10);
        obj.forEach(o -> {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    //调用service 方法
                    System.out.println(Thread.currentThread().getName() + ":"+o);
                }
            });
        });

        Thread.sleep(1000l);
        System.exit(1);






        long size = Integer.MAX_VALUE;
        int[] arr = new int[]{0};


        /*ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(10);
        threadPoolTaskExecutor.setMaxPoolSize(200);
        threadPoolTaskExecutor.setQueueCapacity(20);
        threadPoolTaskExecutor.initialize();

        ColorFontPrint.setFontColor(ColorFontPrint.RED);
        b = System.currentTimeMillis();

        while (!flag) {
            c++;
            threadPoolTaskExecutor.execute(() -> {
                synchronized (arr) {
                    while (!flag) {
                        arr[0] = arr[0] + 1;
                        if (arr[0] >= size) {
                            e = System.currentTimeMillis();
                            flag = true;
                        }
                    }
                }
            });
        }
        new Thread(() -> {
            while (flag) {
                ColorFontPrint.println(flag);
                ColorFontPrint.println(e - b);
                ColorFontPrint.println(c);
                ColorFontPrint.println(arr[0]);
                System.exit(1);
            }
        }).start();*/


        ColorFontPrint.setFontColor(ColorFontPrint.GREEN);
        c = 0;
        b = System.currentTimeMillis();
        while (!flag) {
            c++;
            new Thread(() -> {
                synchronized (arr) {
                    while (!flag) {
                        arr[0] = arr[0] + 1;
                        if (arr[0] >= size) {
                            e = System.currentTimeMillis();
                            flag = true;
                        }
                    }
                }
            }).start();
        }
        new Thread(() -> {
            while (!flag) {
                if (flag) {
                    ColorFontPrint.println(flag);
                    ColorFontPrint.println(e - b);
                    ColorFontPrint.println(c);
                    ColorFontPrint.println(arr[0]);
                    System.exit(1);
                }
            }
        }).start();

    }

}
