package cn.com.gatico.LongConn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ServerSocketFactory;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/**
 * @author Gatico
 * @version 1.0
 * @date 2020/1/6 16:26
 */
public class LongConnTest {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    int port = 1120;


    public void testLongConn() throws Exception {
        final ServerSocket serverSocket = ServerSocketFactory.getDefault().createServerSocket(port);
        new Thread(() -> {
            try {
                while (true) {
                    Socket accept = serverSocket.accept();
                    new Thread(() -> {
                        try {
                            //读数据
                            InputStream inputStream = accept.getInputStream();
                            OutputStream outputStream = accept.getOutputStream();
                            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                            byte d = -1;
                            while (accept.isConnected()) {
                                d = (byte) inputStream.read();
                                if (d != -1) {
                                    byteArrayOutputStream.write(d);
                                } else {
                                    logger.info(Thread.currentThread().getName() + "readData:[" + new String(byteArrayOutputStream.toByteArray(), Charset.forName("UTF-8").name()) + "]");
                                    ByteBuffer buf = ByteBuffer.allocate(2);
                                    buf.put((byte) 0);
                                    buf.put((byte) -1);
                                    outputStream.write(buf.array());
                                    outputStream.flush();
                                    byteArrayOutputStream.reset();
                                }
                            }
                        } catch (Exception ex) {
                            try {
                                accept.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }).start();
        logger.info("服务端启动成功");
        /*final Socket socket = new Socket();
        socket.connect(new InetSocketAddress(host, port));
        InputStream inputStream = socket.getInputStream();
        new Thread(() -> {
            while (true) {
                try {
                    byte[] input = new byte[64];
                    inputStream.read(input);
                    System.out.println("readByte " + new String(input));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();*/
//        while (true) {
//            code = scanner.nextInt();
//            System.out.println("input code:" + code);
//            if (code == 0) {
//                break;
//            } else if (code == 1) {
//                ByteBuffer byteBuffer = ByteBuffer.allocate(5);
//                byteBuffer.put((byte) 1);
//                byteBuffer.putInt(0);
//                socket.getOutputStream().write(byteBuffer.array());
//                System.out.println("write heart finish!");
//            } else if (code == 2) {
//                byte[] content = ("hello, I'm" + hashCode()).getBytes();
//                ByteBuffer byteBuffer = ByteBuffer.allocate(content.length + 5);
//                byteBuffer.put((byte) 2);
//                byteBuffer.putInt(content.length);
//                byteBuffer.put(content);
//                socket.getOutputStream().write(byteBuffer.array());
//                System.out.println("write content finish!");
//            }
//        }
        //socket.close();
    }

    // 因为Junit不支持用户输入,所以用main的方式来执行用例
    public static void main(String[] args) throws Exception {
        new LongConnTest().testLongConn();
    }
}
