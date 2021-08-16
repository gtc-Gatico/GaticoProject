package cn.com.gatico.TestFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class SplitFile {
    public static void main(String[] args) throws Exception {
        String path = "C:\\Users\\48909\\Desktop\\matrix-redtea.2021-04-08.log";
        String tmpPath = "D:\\tmp\\matrix-redtea.2021-04-08_{hour}.log";
        File file = new File(path);
        int hour = 0;
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String line = null;
        FileWriter fileWriter = new FileWriter(new File(tmpPath.replace("{hour}", String.format("%02d", hour))));
        while ((line = bufferedReader.readLine()) != null) {
            if (line.startsWith(String.format("2021-04-08 %02d", hour + 1))) {
                fileWriter.close();
                hour++;
                System.out.println(tmpPath.replace("{hour}", String.format("%02d", hour)));
                fileWriter = new FileWriter(new File(tmpPath.replace("{hour}", String.format("%02d", hour))));
            } else {
                fileWriter.write(new String((line + System.lineSeparator()).getBytes(), "UTF8"));
                fileWriter.flush();
            }
        }
        fileWriter.close();
    }
}
