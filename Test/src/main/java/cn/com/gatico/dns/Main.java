package cn.com.gatico.dns;


import javax.net.ServerSocketFactory;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        ServerSocket socketServer = ServerSocketFactory.getDefault().createServerSocket(53);
        while (true){
            final Socket accept = socketServer.accept();
            new Thread(() -> {
                try {
                    InputStream in = accept.getInputStream();
                    OutputStream out = accept.getOutputStream();
                    byte []arr = new byte[12];
                    while(accept.isConnected()){
                        in.read(arr);
                        System.out.println(Arrays.toString(arr));
                        out .write("ok\n".getBytes());
                        out.flush();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }

    }
}
