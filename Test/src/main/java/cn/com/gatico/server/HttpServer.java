package cn.com.gatico.server;


import cn.com.gatico.server.conf.MIMEType;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLDecoder;

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
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String uri = "/index.html";
                    String headStr = bufferedReader.readLine();
                    if (headStr != null && headStr.length() > 0) {
                        Request requestTemp = new Request();
                        String[] heads = headStr.split(" ");
                        requestTemp.setMethod(heads[0]);
                        requestTemp.setPath(heads[1]);
                        requestTemp.setVersion(heads[2]);
                        requestTemp.setUrl(requestTemp.getPath());
                        if (requestTemp.getPath().equals("/")) {
                            requestTemp.setUrl(uri);
                        }
                        if (requestTemp.getPath().indexOf("?") != -1) {
                            requestTemp.setUrl(URLDecoder.decode(requestTemp.getPath().split("\\?")[0], "UTF-8"));
                            String[] paramArray = requestTemp.getPath().split("\\?")[1].split("&");
                            for (int i = 0; i < paramArray.length; i++) {
                                String key = paramArray[i].split("=")[0];
                                String value = paramArray[i].split("=")[1];
                                requestTemp.getParams().put(key, value == null ? "" : value);
                            }
                        }
                        requestTemp.setContentType(getMimeType(requestTemp.getUrl()));

                        String headLineStr = bufferedReader.readLine();
                        while (headLineStr.length() > 0 && !headLineStr.equals("\r\n")) {
                            String[] headParam = headLineStr.split(": ");
                            requestTemp.getHeads().put(headParam[0], headParam[1]);
                            headLineStr = bufferedReader.readLine();
                        }
                        //POST请求处理
                        String contentType = null;
                        if ("POST".equals(requestTemp.getMethod()) && ((contentType = (String) requestTemp.getHeads().get("Content-Type")) != null
                                && requestTemp.getHeads().get("Content-Type").toString().startsWith("multipart/form-data"))) {
                            //文件上传的分割位 这里只处理单个文件的上传
                            String boundary = contentType.substring(contentType.indexOf("boundary") +
                                    "boundary=".length());
                            //解析消息体
                            String str;
                            while ((str = bufferedReader.readLine()) != null) {
                                //解析结束的标记
                                do {
                                    //读取boundary中的内容
                                    //读取Content-Disposition
                                    str = bufferedReader.readLine();
                                    //说明是文件上传
                                    if (str.indexOf("Content-Disposition:") >= 0 && str.indexOf("filename") > 0) {
                                        int contentLength = Integer.valueOf(requestTemp.getHeads().get("Content-Length").toString());
                                        int bl = (str + "\r\n").length();
                                        str = str.substring("Content-Disposition:".length());
                                        String[] strs = str.split(";");
                                        String valueName = strs[1].split("=")[1].replace("\"", "");//页面的name值在这里面
                                        String fileName = strs[2].split("=")[1].replace("\"", "");//fileName
                                        System.out.println("fileName = " + fileName);
                                        //这一行是Content-Type
                                        String contentTypeLength = bufferedReader.readLine();
                                        bl += (contentTypeLength + "\r\n").length();
                                        //这一行是换行
                                        bufferedReader.readLine();
                                        bl += ("\r\n--" + boundary).length();
                                        bl += ("\r\n--" + boundary + "--").length();
                                        //正式去读文件的内容
                                        FileOutputStream fileOutputStream = null;
                                        System.out.println(contentLength);
                                        System.out.println(bl);
                                        try {
                                            File fileTemp = File.createTempFile("myServer", fileName);
                                            fileOutputStream = new FileOutputStream(fileTemp);
                                            int d = -1;
                                            int index = 0;
                                            while (contentLength - index >= bl) {
                                                d = bufferedReader.read();
                                                fileOutputStream.write(d);
                                                if (index % 1000 == 0) {
                                                    fileOutputStream.flush();
                                                }
                                                index++;
                                            }
                                            fileOutputStream.flush();
                                            File file = new File(fileTemp.getParent() + File.separator, fileName);
                                            if (file.exists()) {
                                                file.delete();
                                            }
                                            System.out.println(fileTemp.length());
                                            FileUtils.copyFile(fileTemp, file);
                                            System.out.println(file.length());
                                            requestTemp.getParams().put(valueName, file);
                                        } catch (Exception e) {

                                        } finally {
                                            if (fileOutputStream != null) {
                                                fileOutputStream.close();
                                            }
                                        }

                                    }
                                    if (str.indexOf("Content-Disposition:") >= 0) {
                                        str = str.substring("Content-Disposition:".length());
                                        String[] strs = str.split(";");
                                        String name = strs[strs.length - 1].replace("\"", "").split("=")[1];
                                        bufferedReader.readLine();
                                        StringBuilder stringBuilder = new StringBuilder();
                                        while (true) {
                                            str = bufferedReader.readLine();
                                            if (str.startsWith("--" + boundary)) {
                                                break;
                                            }
                                            stringBuilder.append(str);
                                        }
                                        requestTemp.getParams().put(name, stringBuilder.toString());
                                        //parameters.put(name, stringBuilder.toString());
                                    }
                                } while (("--" + boundary).equals(str));
                                //解析结束
                                if (str.equals("--" + boundary + "--")) {
                                    break;
                                }
                            }
                        }



                            /*String bodyLineStr = bufferedReader.readLine();
                            while (bodyLineStr != null && bodyLineStr.length() > 0) {
                                if (bodyLineStr.equals("--" + contentType)) {
                                    String content_disposition = bufferedReader.readLine();
                                    String valueName = content_disposition.split(";")[1].split("=")[1].replace("\"", "");//页面的name值在这里面
                                    String fileName = content_disposition.split(";")[2].split("=")[1].replace("\"", "");//fileName
                                    String content_Type = bufferedReader.readLine();
                                    System.out.println(content_Type);
                                    bodyLineStr = bufferedReader.readLine();//换行符
                                    if (fileName != null && fileName.length() > 0) {
                                        //读文件
                                        int x = -1;
                                        int index = 0;
                                        File temp = File.createTempFile("myServer", fileName);
                                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

                                        while ((x = bufferedReader.read()) != -1 && index < contentLength) {
                                            byteArrayOutputStream.write(x);
                                            byteArrayOutputStream.flush();
                                            index++;
                                        }
                                        System.out.println("end:"+bodyLineStr);
                                        byte[] fileBytes = byteArrayOutputStream.toByteArray();
                                        System.out.println(fileBytes.length);
                                        FileUtils.writeByteArrayToFile(temp, fileBytes, 0, fileBytes.length );
                                        File file = new File(temp.getParent(), fileName);
                                        if (file.exists()) {
                                            file.delete();
                                        }
                                        temp.renameTo(file);
                                        requestTemp.getParams().put(valueName, file);
                                        bodyLineStr = bufferedReader.readLine();
                                    } else {
                                        //读参数
                                    }
                                }
                            }
                            bodyLineStr = bufferedReader.readLine();*/
                        ApplicationContext.invoke(requestTemp, socket);
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
                    OutputStream outputStream = null;
                    try {
                        outputStream = socket.getOutputStream();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    Response response = new Response();
                    response = response.getError();
                    File file = new File(ApplicationContext.staticPath + ApplicationContext._500Path);
                    if (!file.exists()) {
                        try {
                            File defaultFile = new File(ApplicationContext.staticPath + ApplicationContext.default_500Path);
                            BufferedReader bufferedReader = new BufferedReader(new FileReader(defaultFile));
                            StringBuffer stringBuffer = new StringBuffer();
                            String html = "";
                            while ((html = bufferedReader.readLine()) != null) {
                                stringBuffer.append(html);
                            }
                            html = stringBuffer.toString();
                            String errorStr = "";

                            errorStr += e;

                            String htmlTemplate = html.replace("${errormsg}", errorStr);
                            File tempFile = File.createTempFile("500", ".html");
                            e.printStackTrace(new PrintWriter(tempFile));
                            FileWriter fileWriter = new FileWriter(tempFile);
                            fileWriter.write(htmlTemplate);
                            fileWriter.flush();
                            file = tempFile;
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                    ApplicationContext.writerFile(file, response, outputStream);

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
