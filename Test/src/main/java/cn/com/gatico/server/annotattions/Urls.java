package cn.com.gatico.server.annotattions;

import java.lang.reflect.Method;

public class Urls {
    private String url;
    private String method;
    private Class<?> clazz;
    private Method execMethod;
    private Object obj;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }

    public Method getExecMethod() {
        return execMethod;
    }

    public void setExecMethod(Method execMethod) {
        this.execMethod = execMethod;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}
