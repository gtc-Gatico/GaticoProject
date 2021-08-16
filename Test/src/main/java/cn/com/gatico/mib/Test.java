package cn.com.gatico.mib;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;

public class Test {
    public static void main(String[] args) throws Exception {
        String path = "D:\\Tool\\MIB\\ManageEngine\\MibBrowser Free Tool\\mibs\\7X-networks-MIB.my";
        BufferedReader br = new BufferedReader(new FileReader(new File(path)));
        String line = null;
        List<String> lines = new LinkedList<>();
        while ((line = br.readLine()) != null) {
            if(line.trim().startsWith("--")){
                continue;
            }
            if(line.replace(" ","").length()>0){
                lines.add(line);
            }
        }

        lines.forEach(System.out::println);

    }
}
