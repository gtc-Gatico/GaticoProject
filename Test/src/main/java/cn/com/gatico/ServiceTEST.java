package cn.com.gatico;


import javax.net.ServerSocketFactory;
import java.io.*;
import java.net.FileNameMap;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLConnection;
import java.util.Collection;

public class ServiceTEST {
    private static final Integer port = 8080;//HTTP默认端口80
    private static final String path = "/home/tianci.gao/test/";

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = ServerSocketFactory.getDefault().createServerSocket(port);
            while (true) {
                final Socket socket = serverSocket.accept();
                new Thread(() -> {
                    try {
                        InputStream inputStream = socket.getInputStream();
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        while(byteArrayOutputStream.size()<inputStream.available()){
                            byteArrayOutputStream.write(inputStream.read());
                        }
                        byte[] arr = byteArrayOutputStream.toByteArray();
                        String request = new String(arr);
                        String[] param = request.split("\r\n");
                        for (int i = 0; i < param.length; i++) {
                            System.out.println(param[i]);
                        }
                        String urlpath = path;
                        try {
                            urlpath = param[0].split(" ")[1];
                            if (urlpath.equals("/")) {
                                urlpath = path;
                            } else if (urlpath.equals("/favicon.ico")) {
                                return;
                            } else {
                                urlpath = path + urlpath;
                            }
                        } catch (Exception e) {
                            System.out.println(param.length);
                        }
                        String contentType = "text/html";
                        OutputStream outSocket = socket.getOutputStream();
                        StringBuffer stringBuffer = new StringBuffer();
                        stringBuffer.append("<html>");
                        stringBuffer.append("<head>");
                        stringBuffer.append("<title>");
                        stringBuffer.append(urlpath);
                        stringBuffer.append("</title>");
                        stringBuffer.append("</head>");
                        stringBuffer.append("<body>");
                        File file = new File(urlpath);
                        if (file.isDirectory()) {
                            File[] files = file.listFiles();
                            for (int i = 0; i < files.length; i++) {
                                String filePath = files[i].getAbsolutePath().replace(path, "");
                                stringBuffer.append("<a href='/" + filePath + "'>" + files[i].getName() + "</a></br>");
                            }
                            stringBuffer.append("</body>");
                            stringBuffer.append("</html>");
                            arr = stringBuffer.toString().getBytes("UTF-8");
                        }else if(file.isFile()){
                            /*MimeUtil.registerMimeDetector("eu.medsea.mimeutil.detector.MagicMimeMimeDetector");
                            Collection<?> mimeTypes = MimeUtil.getMimeTypes(file);*/
                            String mimeTypes =  getMimeType(file.getAbsolutePath());
                            System.out.println(mimeTypes);
                            contentType=mimeTypes;
                            FileInputStream fi = new FileInputStream(file);
                            arr= new byte[fi.available()];
                            fi.read(arr);
                            fi.close();
                        }
                        String responseFirstLine = "HTTP/1.1 200 OK\r\n";
                        String responseHead = "Content-Type:" + contentType + "\r\n";
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
    public static String getMimeType(String fileUrl)
            throws java.io.IOException
    {
        /*FileNameMap fileNameMap = URLConnection.getFileNameMap();
        String type = fileNameMap.getContentTypeFor(fileUrl);*/
        String type="";
        if(fileUrl.endsWith(".xls")||fileUrl.endsWith(".xlsx")){
            type="application/vnd.ms-excel";
        }else if(fileUrl.endsWith(".png")){
            type="application/x-png";
        }else if(fileUrl.endsWith(".txt")){
            type="text/plain";
        }else if(fileUrl.endsWith(".jpg")){
            type="application/x-jpg";
        }else if(fileUrl.endsWith(".doc")){
            type="application/msword";
        }else if(fileUrl.endsWith(".xls")){
            type="application/vnd.ms-excel";
        }else if(fileUrl.endsWith(".xls")){
            type="application/vnd.ms-excel";
        }else if(fileUrl.endsWith(".xls")){
            type="application/vnd.ms-excel";
        }else if(fileUrl.endsWith(".xls")){
            type="application/vnd.ms-excel";
        }

        return type;
    }
}
