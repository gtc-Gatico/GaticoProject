package cn.com.gatico.UDP;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPEchoClientTimeout {
    static int port = 7890;
    private static final int TIMEOUT = 3000;   // 设置超时为3秒
    private static final int MAXTRIES = 5;     // 最大重发次数5次

    public static void main(String[] args) throws IOException {

//        if ((args.length < 2) || (args.length > 3)) { // Test for correct # of args
//            throw new IllegalArgumentException("Parameter(s): <Server> <Word> [<Port>]");
//        }
        InetAddress serverAddress = InetAddress.getByName("127.0.0.1"); // 服务器地址
        // Convert the argument String to bytes using the default encoding
        //发送的信息
        byte[] bytesToSend = "你好1111".getBytes();//args[1].getBytes();

        int servPort =port ;// (args.length == 3) ? Integer.parseInt(args[2]) : 7;

        DatagramSocket socket = new DatagramSocket();

        socket.setSoTimeout(TIMEOUT); // 设置阻塞时间

        DatagramPacket sendPacket = new DatagramPacket(bytesToSend, // 相当于将发送的信息打包
                bytesToSend.length, serverAddress, servPort);

        DatagramPacket receivePacket =                              // 相当于空的接收包
                new DatagramPacket(new byte[bytesToSend.length], bytesToSend.length);

        int tries = 0;      // Packets may be lost, so we have to keep trying
        boolean receivedResponse = false;
        do {
            socket.send(sendPacket);          // 发送信息
            try {
                socket.receive(receivePacket); // 接收信息

                if (!receivePacket.getAddress().equals(serverAddress)) {// Check source
                    throw new IOException("Received packet from an unknown source");
                }
                receivedResponse = true;
            } catch (InterruptedIOException e) { // 当receive不到信息或者receive时间超过3秒时，就向服务器重发请求
                tries += 1;
                System.out.println("Timed out, " + (MAXTRIES - tries) + " more tries...");
            }
        } while ((!receivedResponse) && (tries < MAXTRIES));

        if (receivedResponse) {
            System.out.println("Received: " + new String(receivePacket.getData()));
        } else {
            System.out.println("No response -- giving up.");
        }
        socket.close();
    }

}
