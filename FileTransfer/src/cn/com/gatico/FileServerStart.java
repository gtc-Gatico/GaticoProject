package cn.com.gatico;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class FileServerStart {
    public static String path = "";
    public static int port = 0;

    public static void main(String[] args) {
        try {
            if (args.length <= 0) {
                Properties config = new Properties();
                String path0 = FileServerStart.class.getResource("/").getPath() + "config.properties";
                config.load(new FileInputStream(path0));
                Log.writerFileFlag = true;
                Log.path = config.getProperty("logpath");
                FileServerStart.path = config.getProperty("path");
                FileServerStart.port = Integer.parseInt(config.getProperty("port"));
            } else {
                Log.path = args[1];
                FileServerStart.path = args[0];
                FileServerStart.port = Integer.parseInt(args[2]);
            }
            File files = new File(FileServerStart.path);
            File fileLog = new File(Log.path);
            if (!files.exists()) {
                files.mkdirs();
            }
            if (!fileLog.exists()) {
                fileLog.mkdirs();
            }
            Log.i("main", "文件存放目录:" + FileServerStart.path);
            Log.i("main", "日志目录:" + Log.path);
            //Log.i("main", "访问文件:http://gatico.com.cn/FileCould/server/文件名");
            Log.i("main", "开始服务。");
            new ServiceSoket().start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
