package cn.com.gatico.FutureTaskTest;

import java.util.concurrent.FutureTask;

public class FutureTaskBuilder {
    FutureTask futureTask;

    public FutureTaskBuilder(FutureTask futureTask) {
        this.futureTask = futureTask;
    }

    public static FutureTaskBuilder addTask(FutureTask futureTask) {
        FutureTaskUtils.getFutureTaskMap().put("", futureTask);
        if (FutureTaskUtils.isAutoRun()) {
            futureTask.run();
        }
        return new FutureTaskBuilder(futureTask);
    }

    public void get() throws Exception {
        futureTask.get();
    }

    public void run() {
        futureTask.run();
    }
}
