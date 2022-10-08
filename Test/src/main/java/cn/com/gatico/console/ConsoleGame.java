package cn.com.gatico.console;


import java.io.Console;
import java.io.IOException;
import java.io.Reader;

public class ConsoleGame {
    static int[][] arr = new int[10][10];

    public static void main(String[] args) throws IOException {
//        new Thread(() -> {
//            while (true) {
//                print();
//                try {
//                    Thread.sleep(1000 / 60L);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        }).start();
        Console console = System.console();
        if (console != null) {
            Reader reader = console.reader();
            int key = -1;
            while (true) {
                key = reader.read();
                System.out.println((char) key);
            }
        }

    }

    static void clear() {
        //windows
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
        //linux
        //new ProcessBuilder("clear").inheritIO().start().waitFor();
    }

    public static void print() {
        clear();
        for (int i = 0; i < arr.length; i++) {
            System.out.print("\r");
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] == 0) {
                    System.out.print("0");
                } else {
                    System.out.print("1");
                }
            }
            System.out.println();
        }
    }

}
