package cn.com.gatico.smailRest;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Map;

public class UrlClass {
    Object classzz;//实例化的restful类
    String url;//类名对应的url
    Map<String, Method> methodMap;
    Map<String, Parameter[]>parameterMap;

    public Object getClasszz() {
        return classzz;
    }

    public void setClasszz(Object classzz) {
        this.classzz = classzz;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, Method> getMethodMap() {
        return methodMap;
    }

    public void setMethodMap(Map<String, Method> methodMap) {
        this.methodMap = methodMap;
    }

    public Map<String, Parameter[]> getParameterMap() {
        return parameterMap;
    }

    public void setParameterMap(Map<String, Parameter[]> parameterMap) {
        this.parameterMap = parameterMap;
    }
}
