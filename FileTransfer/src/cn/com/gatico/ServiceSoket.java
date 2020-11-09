package cn.com.gatico;

import javax.net.ServerSocketFactory;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServiceSoket {
    public int port = FileServerStart.port;
    public ServerSocket serviceSocket;

    public void start() {
        String tag = "";
        try {
            serviceSocket = ServerSocketFactory.getDefault().createServerSocket(port);
            while (true) {
                Socket socket = serviceSocket.accept();
                Log.i(Thread.currentThread().getName(), "收到了：" + socket.getRemoteSocketAddress().toString());
                tag = socket.getRemoteSocketAddress().toString();
                FileServer fs = new FileServer(socket);
                fs.start();
            }
        } catch (IOException e) {
            Log.e(Thread.currentThread().getName() + "_" + tag, e);
        }

    }

}
