package cn.com.gatico.FutureTaskTest;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Test {

    public static void main(String[] args) {

        FutureTaskBuilder.addTask(new FutureTask<>(() -> {
            int x = 0;
            for (int i = 0; i < 100; i++) {
                Thread.sleep(100l);
                x++;
            }
            return x;
        }));
        FutureTaskUtils.addTask(new FutureTask<>(() -> {
            int x = 0;
            for (int i = 0; i < 100; i++) {
                Thread.sleep(200l);
                x++;
            }
            return x;
        }));
        FutureTaskUtils.start();

        while (FutureTaskUtils.getDoneTask().isEmpty()) {
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
