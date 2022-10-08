package cn.com.gatico.TestByte;

import cn.com.gatico.LinkListTest.Link;

import java.io.BufferedReader;
import java.io.Console;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.Scanner;

public class TestByte {
    public static void main(String[] args) throws Exception {
        byte[] arr = "100110001100001011011100110001101100101".getBytes();
        System.console().flush();;
        boolean flag = true;
        new Thread(() -> {
            Console console = System.console();
            if (console == null) {
                System.out.println(console == null);
                return;
            }
            Reader reader = console.reader();
            BufferedReader bufferedReader = new BufferedReader(reader);
            try {
                while (flag) {
                    String tmp = bufferedReader.readLine();
                    System.out.println(tmp);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }).start();
        PrintWriter writer = System.console().writer();
        int size = 10;
//        Link root = new Link();
//        root.setValue("");
//        root.setId(0);
//        Link tmp = root;
//        for (int i = 1; i <= size; i++) {
//            Link link = new Link("");
//            link.setPrev(tmp);
//            link.setId(i);
//            tmp.setNext(link);
//            tmp = link;
//        }
//        root.setPrev(tmp);
//        tmp.setNext(root);
//        Scanner scanner = new Scanner(System.in);
//        while (scanner.hasNext()) {
//            String value = scanner.next();
//            tmp.getNext().setValue(value);
//            writer.write(value);
//            writer.flush();
//            System.out.println(tmp.getNext().toString());
//            tmp = tmp.getNext();
//        }

    }
}
