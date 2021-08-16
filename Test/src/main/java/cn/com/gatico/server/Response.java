package cn.com.gatico.server;

import com.sun.net.httpserver.HttpExchange;
import org.springframework.util.Assert;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Response {
    private Integer code;
    private String contentType;
    private Map<String, Object> heads = new HashMap<>();
    private byte[] body;

    public Response() {
        if (contentType == null || contentType.length() <= 0) {
            contentType = "text/html; charset=UTF8";
        }
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        if (contentType == null || contentType.length() <= 0) {
            contentType = "text/html; charset=UTF8";
        }
        this.contentType = contentType;
    }

    public void setContentType() {
        this.contentType = "text/html; charset=UTF8";
    }

    public byte[] getBody() {
        return body;
    }

    public void setBody(byte[] body) {
        this.body = body;
    }

    public Map<String, Object> getHeads() {
        return heads;
    }

    public void setHeads(Map<String, Object> heads) {
        this.heads = heads;
    }

    public Response getSuccess() {
        this.setCode(200);
        return this;
    }

    public Response getNotfound() {
        this.setCode(400);
        return this;
    }

    public Response getError() {
        this.setCode(500);
        return this;
    }

    public byte[] toBytes() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            if (this.body != null) {
                byteArrayOutputStream.write(this.body);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArrayOutputStream.toByteArray();
    }

    public void setContentDispositionFormData(String name, String filename) {
        Assert.notNull(name, "'name' must not be null");
        StringBuilder builder = new StringBuilder("form-data; name=\"");
        builder.append(name).append('"');
        if (filename != null) {
            builder.append("; filename=\"");
            builder.append(filename).append('"');
        }

        this.heads.put("Content-Disposition", builder.toString());
    }

    public void setRedirectHtml(String name) {
        this.code = 302;
        this.heads.put("Location", name);
    }

    public void setAttr(HttpExchange exchange) {
        exchange.getResponseHeaders().set("Content-Type", this.getContentType());
        if (this.heads.size() > 0) {
            for (String key : this.heads.keySet()) {
                exchange.getResponseHeaders().set(key, (String) this.heads.get(key));
            }
        }
    }

}
