package cn.com.gatico.FutureTaskTest;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Test {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(10);
        AtomicInteger x1 = new AtomicInteger(0);
        BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>(2);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 5, 60, TimeUnit.SECONDS, workQueue);
        for (int i = 0; i < 10; i++) {
            threadPoolExecutor.execute(() -> {
                try {
                    System.out.println(Thread.currentThread().getName());
                    countDownLatch.countDown();
                    x1.getAndAdd(1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        countDownLatch.await();
        System.out.println(x1.get());
        System.exit(2);

        FutureTaskBuilder.addTask(new FutureTask<>(() -> {
            int x = 0;
            for (int i = 0; i < 100; i++) {
                Thread.sleep(10L);
                x++;
            }
            return x;
        }));

        FutureTaskUtils.addTask(new FutureTask<>(() -> {
            int x = 0;
            for (int i = 0; i < 200; i++) {
                Thread.sleep(10L);
                x++;
            }
            return x;
        }));
        FutureTaskUtils.start();
        System.out.println("start");
        while (true) {
            FutureTaskUtils.getDoneTask().forEach(futureTask -> {
                try {
                    System.out.println(futureTask.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
