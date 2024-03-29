package cn.com.gatico.smailRest;

import cn.com.gatico.utils.ClassUtil;
import com.sun.net.httpserver.HttpServer;
import sun.net.httpserver.HttpServerImpl;

import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.Executors;

public class Server {
    public static void main(String[] args) throws Exception {

        AbsRestful absRestful = new AbsRestful();
        //解析所有Restful的类
        Set<Class<?>> classes = ClassUtil.getClasses("cn.com.gatico.smailRest");
        classes.forEach(aClass -> {
            Restful annotation = aClass.getAnnotation(Restful.class);
            if (annotation != null) {
                String url = annotation.url();
                try {
                    UrlClass urlClass = new UrlClass();
                    Object o = aClass.newInstance();
                    urlClass.setClasszz(o);
                    urlClass.setUrl(url);
                    Method[] methods = o.getClass().getMethods();
                    HashMap methodMaps = new HashMap();
                    HashMap parameterMaps = new HashMap();
                    for (int i = 0; i < methods.length; i++) {
                        methodMaps.put(methods[i].getName(), methods[i]);
                        parameterMaps.put(methods[i].getName(), methods[i].getParameters());
                    }
                    urlClass.setMethodMap(methodMaps);
                    urlClass.setParameterMap(parameterMaps);
                    absRestful.urlMap.put(url, urlClass);
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });
        HttpServer httpServer = HttpServerImpl.create(new InetSocketAddress(8080), 0);
        httpServer.setExecutor(Executors.newScheduledThreadPool(10));
        httpServer.createContext("/", absRestful);
        httpServer.start();
        System.out.println("服务启动了");
//        HttpServer httpServer = HttpServerImpl.create(new InetSocketAddress(8080), 0);
//        httpServer.setExecutor(Executors.newScheduledThreadPool(10));
//
//        httpServer.createContext("/test.js", httpExchange -> {
//            System.out.print(Thread.currentThread().getName() + ":");
//            String query = httpExchange.getRequestURI().getQuery();
//            System.out.println(query);
//            String[] split = query.split("&");
//            Map<String, String> map = new HashMap();
//            for (int i = 0; i < split.length; i++) {
//                String key = split[i].split("=")[0];
//                String value = split[i].split("=")[1];
//                map.put(key, value);
//            }
//            String callback = map.get("callback");
//            byte[] bytes = (callback + "({data:{name:'123'}})").getBytes();
//            httpExchange.sendResponseHeaders(200, 0);
//            httpExchange.getResponseBody().write(bytes);
//            httpExchange.getResponseBody().flush();
//            httpExchange.getResponseBody().close();
//        });
//        httpServer.start();
//        System.out.println("服务启动了");
    }
}
