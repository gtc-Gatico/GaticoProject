package cn.com.gatico.测试多线程;

import cn.com.gatico.控制台颜色.ColorFontPrint;

public class Test {
    public static long e = 0L;
    public static long b = 0L;
    public static boolean flag = false;
    public static long c = 0;

    public static void main(String[] args) {
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
