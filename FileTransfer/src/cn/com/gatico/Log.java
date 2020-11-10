package cn.com.gatico;

import java.io.File;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Log {
    public static SimpleDateFormat sdf;
    public static String path = "";
    public static String log_path = "_log.log";
    public static String err_path = "_err.log";
    private static PrintStream logps;
    private static PrintStream errps;
    private static File log;
    private static File err;

    public Log() {
    }

    public Log(String path) {
        Log.path = path;
    }

    public static void i(String tga, String msg) {
        try {
            String logpath = Log.path + "\\" + getDataTime().substring(0, 10) + log_path;
            if (log == null || !log.exists()) {
                log = new File(logpath);
                log.createNewFile();
                logps = new PrintStream(log);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        logps.append(getDataTime() + ":" + tga + ":" + msg + "\r\n");
        logps.flush();
        System.out.println(getDataTime() + ":" + tga + ":" + msg);
    }

    public static void e(String tga, Exception error) {
        try {
            String errpath = Log.path + "\\" + getDataTime().substring(0, 10) + err_path;
            if (err == null || !err.exists()) {
                err = new File(errpath);
                err.createNewFile();
                errps = new PrintStream(err);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        errps.append(getDataTime() + ":" + tga + ":" + error.getMessage() + "\r\n");
        error.printStackTrace(errps);
        error.printStackTrace();
        errps.flush();
    }

    public static String getDataTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

}