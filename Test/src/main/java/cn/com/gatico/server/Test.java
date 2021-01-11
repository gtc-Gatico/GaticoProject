package cn.com.gatico.server;

import sun.net.NetworkClient;
import sun.net.NetworkServer;

import java.io.IOException;
import java.net.Socket;

public class Test {


    public static void main(String[] args) {
        new Thread(() -> {
            NetworkServer networkServer = new NetworkServer();
            try {
                networkServer.startServer(9090);
                networkServer.run();
                Socket clientSocket = networkServer.clientSocket;
                clientSocket.getOutputStream().write("你好".getBytes());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            NetworkClient networkClient = new NetworkClient();
            try {
                networkClient.openServer("127.0.0.1",9090);
                byte[]arr = new byte[1024];
                networkClient.serverInput.read(arr);
                System.out.println(new String(arr));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
