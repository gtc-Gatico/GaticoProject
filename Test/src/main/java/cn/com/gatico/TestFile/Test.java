package cn.com.gatico.TestFile;

import java.io.File;
import java.io.UnsupportedEncodingException;

public class Test {
    public static void main(String[] args) throws UnsupportedEncodingException {
        File file = new File(new String("/var/你好".getBytes("utf-8")));
        file.mkdirs();
    }
}
