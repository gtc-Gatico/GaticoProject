import com.sun.net.httpserver.HttpServer;
import sun.net.httpserver.HttpServerImpl;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

public class Server {
    public static void main(String[] args) throws Exception {



        HttpServer httpServer = HttpServerImpl.create(new InetSocketAddress(8080), 0);
        httpServer.setExecutor(Executors.newScheduledThreadPool(10));
        httpServer.createContext("/test.js", httpExchange -> {
            System.out.print(Thread.currentThread().getName()+":");
            String query = httpExchange.getRequestURI().getQuery();
            System.out.println(query);
            String[] split = query.split("&");
            Map<String, String> map = new HashMap();
            for (int i = 0; i < split.length; i++) {
                String key = split[i].split("=")[0];
                String value = split[i].split("=")[1];
                map.put(key, value);
            }
//            byte[] bytes = Files.readAllBytes(Paths.get("f:\\tmpimage.png"));
            String callback = map.get("callback");
            byte[] bytes = (callback + "({data:{name:'123'}})").getBytes();
            httpExchange.sendResponseHeaders(200, 0);
            httpExchange.getResponseBody().write(bytes);
            httpExchange.getResponseBody().flush();
            httpExchange.getResponseBody().close();
        });
        httpServer.createContext("/1", httpExchange -> httpExchange.sendResponseHeaders(200, 0));
        httpServer.start();
        System.out.println("服务启动了");
    }
}
