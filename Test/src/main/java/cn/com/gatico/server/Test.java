package cn.com.gatico.server;

import sun.net.NetworkClient;
import sun.net.NetworkServer;

import javax.net.ServerSocketFactory;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLDecoder;

public class Test {


    public static void main(String[] args) throws  Exception{
        ServerSocket serverSocket = ServerSocketFactory.getDefault().createServerSocket(10000);
        while(true){
            Socket accept = serverSocket.accept();
            InputStream inputStream = accept.getInputStream();
            int bytes;
            byte []sb = new byte[1024];
            int index=0;
            while((bytes = inputStream.read())!=-1){
                sb[index++] = (byte)bytes;
                if('\n'==(char) bytes){
                    System.out.println(new String(sb,"UTF8"));
                    String sd = new String("大傻逼\n");
                    accept.getOutputStream().write(sd.getBytes("UTF8"));
                    sb = new byte[1024];
                    index=0;
                }

            }
        }


//        new Thread(() -> {
//            NetworkServer networkServer = new NetworkServer();
//            try {
//                networkServer.startServer(9090);
//                networkServer.run();
//                Socket clientSocket = networkServer.clientSocket;
//                clientSocket.getOutputStream().write("你好".getBytes());
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }).start();
//
//        new Thread(() -> {
//            try {
//                Thread.sleep(1000L);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            NetworkClient networkClient = new NetworkClient();
//            try {
//                networkClient.openServer("127.0.0.1",9090);
//                byte[]arr = new byte[1024];
//                networkClient.serverInput.read(arr);
//                System.out.println(new String(arr));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }).start();
    }

    /**
     * unicode 转字符串
     */
    public static String revert(String unicode) {

        StringBuffer string = new StringBuffer();

        String[] hex = unicode.split("\\\\x");

        for (int i = 1; i < hex.length; i++) {

            // 转换出每一个代码点
            int data = Integer.parseInt(hex[i], 16);

            // 追加成string
            string.append((char) data);
        }

        return string.toString();
    }
    public static byte revertInt(String unicode) {
        return Integer.valueOf(unicode, 16).byteValue();
    }
}
