package cn.com.gatico.FutureTaskTest;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Test {

    public static void main(String[] args) {
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
        while(true){
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
