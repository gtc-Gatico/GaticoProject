package cn.com.gatico.smailRest;

import com.alibaba.fastjson.JSONObject;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class AbsRestful implements HttpHandler {
    static ThreadLocal<HttpExchange> threadLocal = new ThreadLocal<>();
    public Map<String, UrlClass> urlMap = new HashMap<>();

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        threadLocal.set(httpExchange);
        String url = httpExchange.getRequestURI().getPath();
        Map<String, Object> query = getQuery();
        String method1 = (String) query.get("method");

        UrlClass aClass = urlMap.get(url);
        if (aClass == null) {
            httpExchange.sendResponseHeaders(404, 0);
            httpExchange.getResponseBody().write("{msg:'资源不存在',code:404}".getBytes());
            httpExchange.getResponseBody().flush();
            httpExchange.getResponseBody().close();
            return;
        }
        Method method = aClass.getMethodMap().get(method1);
        if (method == null) {
            httpExchange.sendResponseHeaders(404, 0);
            httpExchange.getResponseBody().write("{msg:'函数不存在',code:404}".getBytes());
            httpExchange.getResponseBody().flush();
            httpExchange.getResponseBody().close();
            return;
        }
        Parameter[] parameters = aClass.getParameterMap().get(method1);
        Object[] param = new Object[parameters.length];
        for (int i = 0; i < parameters.length; i++) {
            String paramName = parameters[i].getName();
            if (paramName != null && query.containsKey(paramName)) {
                param[i] = query.get(paramName);
            }
        }
        try {
            Object returnValue = method.invoke(aClass.classzz, param);
            httpExchange.sendResponseHeaders(200, 0);
            String res = JSONObject.toJSONString(returnValue);
            if (query.get("callback") != null) {
                res = query.get("callback") + "(" + res + ");";
            }
            httpExchange.getResponseBody().write(res.getBytes("UTF8"));
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            httpExchange.sendResponseHeaders(500, 0);
            httpExchange.getResponseBody().write(JSONObject.toJSONString(e).getBytes("UTF8"));
        }
        httpExchange.getResponseBody().flush();
        httpExchange.getResponseBody().close();
    }

    public static HttpExchange getHttpExchange() {
        return threadLocal.get();
    }

    public static Map<String, Object> getQuery() {
        HttpExchange httpExchange = threadLocal.get();
        String query = httpExchange.getRequestURI().getQuery();
        String[] split = query.split("&");
        Map<String, Object> map = new HashMap<>();
        for (int i = 0; i < split.length; i++) {
            String key = split[i].split("=")[0];
            String value = split[i].split("=")[1];
            if (map.containsKey(key)) {
                Object o = map.get(key);
                if (o instanceof LinkedList) {
                    LinkedList linkedList = (LinkedList) map.get(key);
                    linkedList.add(value);
                    map.put(key, linkedList);
                } else {
                    List<Object> valueList = new LinkedList<>();
                    valueList.add(map.get(key));
                    map.put(key, valueList);
                }
            }
            map.put(key, value);
        }
        return map;
    }

}
