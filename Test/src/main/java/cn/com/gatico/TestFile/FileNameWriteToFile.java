package cn.com.gatico.TestFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileNameWriteToFile {
    static String Path = "/home/tianci.gao/test/logger/log{index}.log";
    static String logPath = "/home/tianci.gao/test/logger/log{index}.log";
    static PrintWriter log;
    static int lineNumber;

    public static void main(String[] args) throws Exception {
        logPath = Path.replace("{index}", "0");
        log = new PrintWriter(new BufferedOutputStream(new FileOutputStream(logPath, false)));
        String path = "/home/tianci.gao/IdeaProjects/GaticoProject";
        File file = new File(path);
        getNames(file);

    }

    public static void getNames(File file) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null) {
                for (int i = 0; i < files.length; i++) {
                    if (files[i].isFile()) {
                        lineNumber++;
                        if (lineNumber % 100 == 0) {
                            try {
                                log.close();
                                logPath = Path.replace("{index}", lineNumber / 100 + "");
                                log = new PrintWriter(new BufferedOutputStream(new FileOutputStream(logPath, false)));
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                        }
                        log.print(files[i].getName() + "\n");
                        log.flush();
                    } else {
                        getNames(files[i]);
                    }
                }
            }
        }
    }

    static List<String> names = new ArrayList<>();
    public static void getName2(File file){
        if(file.isDirectory()){
            names.add(file.getAbsolutePath());
        }else{
            lineNumber++;
            if (lineNumber % 100 == 0) {
                try {
                    log.close();
                    logPath = Path.replace("{index}", lineNumber / 100 + "");
                    log = new PrintWriter(new BufferedOutputStream(new FileOutputStream(logPath, false)));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
            log.print(file.getName() + "\n");
            log.flush();
        }
    }
}
