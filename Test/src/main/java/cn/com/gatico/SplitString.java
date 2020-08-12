package cn.com.gatico;

import java.io.*;

public class SplitString {
    public static void main(String[] args) throws Exception {
        String path = "C:\\Users\\48909\\Desktop\\matrix-watsons-nexus.2020-06-07\\matrix-watsons-nexus.2020-06-07.log";
        String tmpPath = "C:\\Users\\48909\\Desktop\\matrix-watsons-nexus.2020-06-07\\matrix-watsons-nexus.2020-06-07_${i}.txt";
        File file = new File(path);
        BufferedReader fis = new BufferedReader(new FileReader(file));
        int index = 0;
        String str = "";
        String tmp = String.format("%02d", index++);
        PrintWriter printWriter = new PrintWriter(new BufferedOutputStream(new FileOutputStream(tmpPath.replace("${i}", tmp), false)));
        while ((str = fis.readLine()) != null) {
            //2020-06-07 00
            if (str.indexOf("2020-06-07 " + tmp) != -1) {
                System.out.println("第" + tmp + "文件");
                printWriter = new PrintWriter(new BufferedOutputStream(new FileOutputStream(tmpPath.replace("${i}", tmp), false)));
                tmp = String.format("%02d", index++);
            }
            printWriter.write(str + "\r\n");
            printWriter.flush();

        }

    }
}
