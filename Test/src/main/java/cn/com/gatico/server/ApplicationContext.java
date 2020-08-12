package cn.com.gatico.server;

import cn.com.gatico.server.annotattions.Urls;
import com.sun.net.httpserver.HttpExchange;

import java.io.*;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ApplicationContext {
    public static Integer port = 8080;
    public static String staticPath = "F:\\web\\MyWebSite\\";
    public static String uploadPath = "F:\\web\\MyWebSite\\upload\\";
    public static String _404Path = "404.html";
    public static String _500Path = "500.html";
    public static String default_500Path = "default_500.html";
    private static Map<String, Urls> mapping = new ConcurrentHashMap<>();

    public static Map<String, Urls> getMapping() {
        return mapping;
    }

    public static void invoke(Request request, HttpExchange exchange) {
        Response response = new Response();
        String urlKey = request.getUrl() + ";" + request.getMethod();
        if (mapping.containsKey(urlKey)) {
            Urls urls = mapping.get(urlKey);
            if (urls.getMethod().equals(request.getMethod())) {
                Method method = urls.getExecMethod();
                Object[] param = new Object[method.getParameterTypes().length];
                for (int i = 0; i < method.getParameters().length; i++) {
                    String paramName = method.getParameters()[i].getName();
                    if (request.getParams() != null && request.getParams().get(paramName) != null) {
                        param[i] = request.getParams().get(paramName);
                    }
                }
                try {
                    response = response.getSuccess();
                    response.setContentType(request.getContentType());
                    if (urls.getObj() == null) {
                        Object u = urls.getClazz().newInstance();
                        urls.setObj(u);
                    }
                    if (method.getGenericReturnType().getTypeName().equals(String.class.getName())) {
                        String res = (String) method.invoke(urls.getObj(), param);
                        response.setBody(res.getBytes());
                    }
                    if (method.getGenericReturnType().getTypeName().equals(Response.class.getName())) {
                        Response res = (Response) method.invoke(urls.getObj(), param);
                        response = res;
                    }

                    response.setAttr(exchange);
                    byte[] bytes = response.toBytes();
                    exchange.sendResponseHeaders(response.getCode(), bytes.length);
                    exchange.getResponseBody().write(bytes);
                    exchange.getResponseBody().flush();
                } catch (Exception e) {
                    response = response.getError();
                    response.setContentType(request.getContentType());
                    File file = new File(staticPath + _500Path);
                    if (!file.exists()) {
                        try {
                            File defaultFile = new File(staticPath + default_500Path);
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
                    writerFile(file, response, exchange);
                }
            } else {
                //404
                File file = new File(staticPath + _404Path);
                response = response.getNotfound();
                writerFile(file, response, exchange);
            }
        } else {
            File file = new File(staticPath + request.getUrl());
            response = response.getSuccess();
            if (!file.exists()) {
                //404
                file = new File(staticPath + _404Path);
                response = response.getNotfound();
            }
            writerFile(file, response, exchange);
        }
    }

    public static void writerFile(File file, Response response, HttpExchange exchange) {
        FileInputStream writeHtml = null;
        try {
            writeHtml = new FileInputStream(file);
            byte[] htmlBuffer = new byte[writeHtml.available()];
            if (writeHtml != null) {
                writeHtml.read(htmlBuffer);
                response.setBody(htmlBuffer);
                response.setAttr(exchange);
                exchange.sendResponseHeaders(response.getCode(), htmlBuffer.length);
                exchange.getResponseBody().write(response.getBody());
                exchange.getResponseBody().flush();
            }
        } catch (Exception e) {

        }

    }


}
