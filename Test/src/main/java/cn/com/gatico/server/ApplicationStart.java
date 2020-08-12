package cn.com.gatico.server;

import cn.com.gatico.server.annotattions.API;
import cn.com.gatico.server.annotattions.Mapping;
import cn.com.gatico.server.annotattions.Urls;
import cn.com.gatico.控制台颜色.ColorFontPrint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

public class ApplicationStart {
    public static Logger logger = LoggerFactory.getLogger(ApplicationStart.class);

    public static void start(Class<?> clazz, String args[]) {
        Set<Class<?>> classSet = Scanner.getClasses(clazz.getPackage().getName());
        Set<Class<?>> apis = new HashSet<>();
        classSet.forEach(aClass -> {
            if (aClass.getAnnotation(API.class) != null) {
                apis.add(aClass);
            }
        });
        System.out.println(apis.size());
        apis.forEach(aClass -> {
            API annotation = aClass.getAnnotation(API.class);
            String url = "";
            if (annotation.url() != null) {
                url = annotation.url();
            }
            Method[] methods = aClass.getDeclaredMethods();

            for (int i = 0; i < methods.length; i++) {
                Mapping mapping = methods[i].getAnnotation(Mapping.class);
                if (mapping != null) {
                    Urls urls = new Urls();
                    urls.setMethod(mapping.method());
                    urls.setUrl(url + mapping.url());
                    urls.setExecMethod(methods[i]);
                    urls.setClazz(aClass);
                    ApplicationContext.getMapping().put(url + mapping.url() + ";" + mapping.method(), urls);
                    logger.info(ColorFontPrint.convertLine(url + mapping.url() + ";" + mapping.method(), ";", ColorFontPrint.GREEN, ColorFontPrint.RED));
                }
            }
        });
        new Thread(() -> {
            HttpServer1.Start(ApplicationContext.port);
        }).start();

           /* urlsSet.forEach(urls -> {
                System.out.println(urls.getUrl());
                System.out.println(urls.getMethod());
                System.out.println(urls.getExecMethod());
                System.out.println(urls.getClazz());
                try {
                    Method method = urls.getExecMethod();
                    System.out.println(method.getName());
                    System.out.println(method.getReturnType());
                    Object type = method.getGenericReturnType();
                    System.out.println(type);
                    Object[] param = new Object[method.getParameterTypes().length];
                    for (int i = 0; i < method.getParameters().length; i++) {
                        String paramName = method.getParameters()[i].getName();
                        param[i] = pMap.get(paramName);
                    }
                    Object clazz = urls.getClazz().newInstance();
                    String json = JSONObject.toJSONString(method.invoke(clazz, param));
                    System.out.println(json);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });*/
    }

}
