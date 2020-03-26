package cn.com.gatico.server;


import cn.com.gatico.server.conf.MIMEType;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class HttpServer {

    public static void Start(Integer port) {
        ServerSocket serverSocket;
        try {
            //建立服务器Socket,监听客户端请求
            serverSocket = new ServerSocket(port);
            System.out.println("Server is running on port:" + serverSocket.getLocalPort());
            //死循环不间断监听客户端请求
            while (true) {
                final Socket socket = serverSocket.accept();
                System.out.println("biuld a new tcp link with client,the cient address:" + socket.getInetAddress() + ":" + socket.getPort());
                //并发处理HTTP客户端请求
                service(socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void service(Socket socket) {
        new Thread() {
            public void run() {
                InputStream inSocket;
                try {
                    //获取HTTP请求头
                    inSocket = socket.getInputStream();
                    int size = inSocket.available();
                    byte[] buffer = new byte[size];
                    inSocket.read(buffer);
                    String request = new String(buffer);
                    String uri = "/index.html";
                    Map<String, String> paraMap = new HashMap<>();
                    if (request.length() > 0) {
                        String[] params = request.split("\r\n");
                        String[] heads = params[0].split(" ");
                        paraMap.put("method", heads[0]);
                        paraMap.put("path", heads[1]);
                        paraMap.put("protocol", heads[2]);
                        for (int i = 1; i < params.length; i++) {
                            String[] temp = params[i].split(": ");
                            paraMap.put(temp[0], temp[1]);
                        }
//                        socket.setKeepAlive(paraMap.get("Connection").equals("keep-alive"));
                        Request requestTemp = new Request();
                        requestTemp.setMethod(paraMap.get("method"));
                        requestTemp.setVersion(paraMap.get("protocol"));
                        String path = paraMap.get("path");
                        requestTemp.setPath(path);
                        requestTemp.setUrl(path);
                        if (path.equals("/")) {
                            requestTemp.setUrl(uri);
                        }
                        if (path.indexOf("?") != -1) {
                            requestTemp.setUrl(java.net.URLDecoder.decode(path.split("\\?")[0], "UTF-8"));
                            String[] paramArray = path.split("\\?")[1].split("&");
                            for (int i = 0; i < paramArray.length; i++) {
                                String key = paramArray[i].split("=")[0];
                                String value = paramArray[i].split("=")[1];
                                requestTemp.getParams().put(key, value == null ? "" : value);
                            }
                        }
                        requestTemp.setContentType(getMimeType(requestTemp.getUrl()));
                        paraMap.forEach((s, s2) -> {
                            requestTemp.getHeads().put(s, s2);
                        });
                        ApplicationContext.invoke(requestTemp, socket);
                        /*if (uri.indexOf("html") != -1) {
                            contentType = "text/html";
                        } else {
                            contentType = "application/octet-stream";
                        }*/
                    }
                    /*String responseFirstLine = "";
                    String responseHead = "Content-Type: " + contentType + "\r\n";
                    OutputStream outSocket = socket.getOutputStream();
                    File file = new File(path + uri);
                    if (!file.exists()) {
                        responseFirstLine = "HTTP/1.1 404 Not Found\r\n";
                        outSocket.write(responseFirstLine.getBytes());
                        outSocket.write("Content-Type: text/html;charset=utf8 \r\n".getBytes());
                        outSocket.write("\r\n".getBytes());
                        file = new File(path + "/404.html");
                    } else {
                        responseFirstLine = "HTTP/1.1 200 OK\r\n";
                        outSocket.write(responseFirstLine.getBytes());
                        outSocket.write(responseHead.getBytes());
                        outSocket.write("\r\n".getBytes());//通过HTTP请求中的uri读取相应文件发送给客户端
                    }
                    FileInputStream writehtml = new FileInputStream(file);
                    if (file.length() > 1024 * 2) {
                        byte[] arr = new byte[1024];
                        int len = 0;
                        while ((len = writehtml.read(arr)) != -1) {
                            outSocket.write(arr, 0, len);
                            outSocket.flush();
                        }
                    } else {
                        byte[] htmlbuffer = new byte[writehtml.available()];
                        if (writehtml != null) {
                            int len = 0;
                            while ((len = writehtml.read(htmlbuffer)) != -1) {
                                outSocket.write(htmlbuffer, 0, len);
                                outSocket.flush();
                            }
                        }
                    }*/
                    socket.close();
                } catch (IOException e) {
                    try {
                        String responseFirstLine = "HTTP/1.1 500 Internal Server Error\r\n";
                        OutputStream outputStream = socket.getOutputStream();
                        outputStream.write(responseFirstLine.getBytes());
                        outputStream.write("Content-Type: text/html;charset=utf8 \r\n".getBytes());
                        outputStream.write("\r\n".getBytes());//通过HTTP请求中的uri读取相应文件发送给客户端
                        socket.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                }
            }
        }.start();
    }

    public static String getMimeType(String fileUrl) {
        /*FileNameMap fileNameMap = URLConnection.getFileNameMap();
        String type = fileNameMap.getContentTypeFor(fileUrl);*/
        String type = fileUrl.substring(fileUrl.lastIndexOf(".") + 1);
        return MIMEType.getMime(type);
    }
}
