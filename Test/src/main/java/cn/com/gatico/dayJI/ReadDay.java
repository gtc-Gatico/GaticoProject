package cn.com.gatico.dayJI;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * @author Gatico
 * @version 1.0
 * @date 2019/11/12 14:55
 */
public class ReadDay {
    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("F:\\日记\\20180424.edf")));
        System.out.println(new String(bufferedReader.readLine().getBytes(), "UTF-8"));
    }
}


