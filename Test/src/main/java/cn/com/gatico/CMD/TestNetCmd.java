package cn.com.gatico.CMD;

import sun.net.NetworkClient;
import sun.net.NetworkServer;

import java.io.IOException;
import java.util.Scanner;

public class TestNetCmd {
    public static void read(byte arr[]) {
        System.out.println(arr);
    }
    public static void main(String[] args){
        if (args == null || args.length < 2) {
            System.out.println("缺少参数：ip port");
            return;
        }
        NetworkClient networkClient = new NetworkClient();
        try {
            networkClient.openServer(args[0], Integer.parseInt(args[1]));
            byte[] arr = new byte[1024];
            Scanner scanner = new Scanner(System.in);
            new Thread(() -> {
                int length = 0;
                while (true) {
                    try {
                        if ((length = networkClient.serverInput.read(arr)) > 0) {
                            System.out.println(new String(arr, 0, length));
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
            while (scanner.hasNext()) {
                String c = scanner.next();
                networkClient.serverOutput.write(c.getBytes());
                if (scanner.hasNextLine()) {
                    networkClient.serverOutput.write((c + "\r\n").getBytes());
                }
            }
            //System.out.println(new String(arr));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
