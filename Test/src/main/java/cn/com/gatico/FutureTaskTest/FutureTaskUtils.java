package cn.com.gatico.FutureTaskTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.FutureTask;
import java.util.stream.Collectors;

public class FutureTaskUtils {
    private static boolean autoRun = false;
    private static ConcurrentHashMap<Object, FutureTask> futureTaskMap = new ConcurrentHashMap<>();
    private static List<FutureTask> doneFutureTaskList = Collections.synchronizedList(new ArrayList<>());
    private static List<FutureTask> cancelFutureTaskList = Collections.synchronizedList(new ArrayList<>());
    public static void addTask(FutureTask futureTask) {
        addTask(futureTask.hashCode(), futureTask);
    }

    public static boolean addTask(Object key, FutureTask futureTask) {
        if (key == null || futureTask == null) {
            throw new NullPointerException();
        }
        if (futureTaskMap.get(key) != null) {
            return false;
        }
        futureTaskMap.put(key, futureTask);
        if (isAutoRun()) {
            new Thread(() -> {
                futureTask.run();
            }).start();
        }
        new Thread(()->{
            futureTaskMap.values().forEach(futureTask1 -> {
                if(futureTask1.isDone()){
                    doneFutureTaskList.add(futureTask);
                }
                if(futureTask1.isCancelled()){
                    cancelFutureTaskList.add(futureTask);
                }
            });
        }).start();
        return true;
    }

    public static List<FutureTask> getDoneTask() {
        return futureTaskMap.values().stream().filter(FutureTask::isDone).collect(Collectors.toList());
    }

    public static List<FutureTask> getCancelTask() {
        return futureTaskMap.values().stream().filter(FutureTask::isCancelled).collect(Collectors.toList());
    }

    public static void start() {
        futureTaskMap.values().stream().forEach(futureTask -> {
            new Thread(() -> {
                futureTask.run();
            }).start();
        });
    }

    public static FutureTask getTask(Object key) {
        if (key == null) {
            throw new NullPointerException();
        }
        if (key instanceof FutureTask) {
            FutureTask tmp = (FutureTask) key;
            return futureTaskMap.get(tmp.hashCode());
        }
        return futureTaskMap.get(key);
    }


    public static ConcurrentHashMap<Object, FutureTask> getFutureTaskMap() {
        return futureTaskMap;
    }

    public static void setFutureTaskMap(ConcurrentHashMap<Object, FutureTask> futureTaskMap) {
        FutureTaskUtils.futureTaskMap = futureTaskMap;
    }

    public static boolean isAutoRun() {
        return autoRun;
    }

    public static void setAutoRun(boolean auto) {
        autoRun = auto;
    }

}
