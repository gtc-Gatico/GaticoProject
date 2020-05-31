package cn.com.gatico.server;

import org.springframework.util.Assert;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Response {
    private String protocol = "HTTP/1.1";
    private Integer code;
    private String codeMsg;
    private String contentType;
    private Map<String, Object> heads = new HashMap<>();
    private byte[] body;

    public Response() {
        if (contentType == null || contentType.length() <= 0) {
            contentType = "text/html;charset=utf8";
        }
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getCodeMsg() {
        return codeMsg;
    }

    public void setCodeMsg(String codeMsg) {
        this.codeMsg = codeMsg;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        if (contentType == null || contentType.length() <= 0) {
            contentType = "text/html;charset=utf8";
        }
        this.contentType = contentType;
    }

    public void setContentType() {
        this.contentType = "text/html;charset=utf8";
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
        this.setCodeMsg("OK");
        return this;
    }

    public Response getNotfound() {
        this.setCode(400);
        this.setCodeMsg("Not Found");
        return this;
    }

    public Response getError() {
        this.setCode(500);
        this.setCodeMsg("server error");
        return this;
    }

    public byte[] toBytes() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byteArrayOutputStream.write(this.protocol.getBytes());
            byteArrayOutputStream.write(" ".getBytes());
            byteArrayOutputStream.write(String.valueOf(this.code).getBytes());
            byteArrayOutputStream.write(" ".getBytes());
            byteArrayOutputStream.write(this.codeMsg.getBytes());
            byteArrayOutputStream.write("\r\n".getBytes());
            byteArrayOutputStream.write(("Content-Type: " + this.contentType).getBytes());
            byteArrayOutputStream.write("\r\n".getBytes());
            if (this.heads.size() > 0) {
                for (String key : this.heads.keySet()) {
                    byteArrayOutputStream.write((key + ": " + this.heads.get(key)).getBytes());
                }
                byteArrayOutputStream.write("\r\n".getBytes());
            }
            byteArrayOutputStream.write("\r\n".getBytes());
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
        this.setContentType();
        this.codeMsg = "Redirect";
        this.heads.put("Location", name);
    }

}
