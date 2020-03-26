package cn.com.gatico.server;

import java.util.HashMap;
import java.util.Map;

public class Request {
    private String method;
    private String version;
    private Map<String, Object> params = new HashMap<>();
    private String url;
    private String path;
    private String contentType;
    private Map<String, Object> heads = new HashMap<>();

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Map<String, Object> getHeads() {
        return heads;
    }

    public void setHeads(Map<String, Object> heads) {
        this.heads = heads;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
