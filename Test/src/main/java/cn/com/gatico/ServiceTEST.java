package cn.com.gatico;

import javax.net.ServerSocketFactory;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServiceTEST {
    private static final Integer port = 8080;//HTTP默认端口80
    private static final String path = "/home/tianci.gao/wwwroot/";

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = ServerSocketFactory.getDefault().createServerSocket(port);
            while (true) {
                final Socket socket = serverSocket.accept();
                new Thread(() -> {
                    try {
                        InputStream inputStream = socket.getInputStream();
                        byte[] arr = new byte[inputStream.available()];
                        inputStream.read(arr);
                        String request=new String(arr);
                        System.out.println(request);
                        String []param=request.split("\r\n");
                        for (int i = 0; i < param.length; i++) {
                            System.out.println(param[i]);
                        }
                        String contentType = "text/html";
                        String responseFirstLine = "HTTP/1.1 200 OK\r\n";

                        String responseHead = "Content-Type:" + contentType + "\r\n";

                        OutputStream outSocket = socket.getOutputStream();
                        System.out.println("ServerResponse:\n" + responseFirstLine + "\n" + responseHead + "\n"
                                + "--------------------------------------------------------------------");
                        outSocket.write(responseFirstLine.getBytes());
                        outSocket.write(responseHead.getBytes());
                        outSocket.write("\r\n".getBytes());
                        outSocket.write(arr, 0, arr.length);
                        outSocket.close();
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
