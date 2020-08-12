package cn.com.gatico.LinkListTest;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws Exception {
        int size = 10;
        Link root = new Link();
        root.setValue("");
        root.setId(0);
        Link tmp = root;
        for (int i = 1; i <= size; i++) {
            Link link = new Link("");
            link.setPrev(tmp);
            link.setId(i);
            tmp.setNext(link);
            tmp = link;
        }
        root.setPrev(tmp);
        tmp.setNext(root);
        Scanner scanner = new Scanner(System.in);
        Runtime runtime = Runtime.getRuntime();

        Process cmd = runtime.exec("cmd", new String[]{"/c"});
        InputStream errorStream = cmd.getInputStream();
        InputStream inputStream = cmd.getInputStream();
        OutputStream outputStream = cmd.getOutputStream();
        String error = null;
        String input = null;
        while (scanner.hasNext()) {
            BufferedReader inputReader = new BufferedReader(new InputStreamReader(inputStream));
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(errorStream));
            while ((error = errorReader.readLine()) != null) {
                System.out.println(error);
            }
            while ((input = inputReader.readLine()) != null) {
                System.out.println(input);
            }
            String value = scanner.next();
            outputStream.write(value.getBytes());
            tmp.getNext().setValue(value);
            System.out.println(tmp.getNext().toString());
            tmp = tmp.getNext();
        }
    }
}
