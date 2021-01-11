package cn.com.gatico.TestFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;

public class Hook {
    public static void main(String[] args) throws IOException {
        // 需要监听的文件目录（只能监听目录）
        String path = "F:/test";

        WatchService watchService = FileSystems.getDefault().newWatchService();
        Path p = Paths.get(path);
        p.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY,
                StandardWatchEventKinds.ENTRY_DELETE,
                StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.OVERFLOW
        );
        Thread thread = new Thread(() -> {
            try {
                while (true) {
                    WatchKey watchKey = watchService.take();
                    List<WatchEvent<?>> watchEvents = watchKey.pollEvents();
                    for (WatchEvent<?> event : watchEvents) {
                        //TODO 根据事件类型采取不同的操作。。。。。。。
                        Path name = ((WatchEvent<Path>) event).context();
                        Path child = p.resolve(name);
                        //f 是p下面的文件夹或者目录，下面的文件自己处理
                        File f = child.toFile();
                        System.out.println("[" + f.getAbsolutePath() + "]文件发生了[" + event.kind() + "]事件");
                    }
                    watchKey.reset();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.setDaemon(false);
        thread.start();

        // 增加jvm关闭的钩子来关闭监听
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                watchService.close();
            } catch (Exception e) {
            }
        }));
    }

    public static void modify() {

    }
}
