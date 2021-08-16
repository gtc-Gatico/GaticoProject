package cn.com.gatico.UDP;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class Test {
    static int port = 1890;

    public static void main(String[] args) throws Exception {

        DatagramSocket datagramSocket = new DatagramSocket(port);
        byte arr[] = new byte[1024];
        DatagramPacket datagramPacket = new DatagramPacket(arr, arr.length);
        new Thread(() -> {
            while (true) {
                try {
                    datagramSocket.receive(datagramPacket);
                    byte[] data = datagramPacket.getData();
                    System.out.println(new String(data, 0, datagramPacket.getLength(), "UTF8"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            DatagramSocket client = null;
            try {
                client = new DatagramSocket();
            } catch (SocketException e) {
                e.printStackTrace();
            }
            try {
                InetAddress serverAddress = InetAddress.getByName("127.0.0.1");
                client.setSoTimeout(5000);
                DatagramPacket datagramPacket1;
                Scanner scanner = new Scanner(System.in);
                while (scanner.hasNextLine()) {
                    byte arr1[] = scanner.next().getBytes("UTF8");
                    datagramPacket1 = new DatagramPacket(arr1, arr1.length, serverAddress, port);
                    client.send(datagramPacket1);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                client.close();
            }
        }).start();
    }
}
