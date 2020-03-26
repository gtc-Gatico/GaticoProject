package cn.com.gatico.server.controller;

import cn.com.gatico.server.Response;
import cn.com.gatico.server.annotattions.API;
import cn.com.gatico.server.annotattions.Mapping;
import com.google.gson.JsonArray;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

@API(url = "/api")
public class APIController {

    @Mapping(url = "/hello")
    public String hello(String str) {
        return "hello:" + str;
    }

    @Mapping(url = "/hello", method = "POST")
    public String hello2() {
        JsonArray jsonArray = new JsonArray();
        for (int i = 0; i < 100; i++) {
            jsonArray.add(String.valueOf(Double.valueOf(Math.random() * 100).intValue()));
        }
        return jsonArray.toString();
    }

    @Mapping(url = "/download")
    public Response hello3() throws Exception {
        Response response = new Response();
        response = response.getSuccess();
        response.setContentType("application/octet-stream");
        File file = new File("F:\\test2.PNG");
        FileInputStream writehtml = new FileInputStream(file);
        byte[] htmlbuffer = new byte[writehtml.available()];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if (writehtml != null) {
            int len = 0;
            while ((len = writehtml.read(htmlbuffer)) != -1) {
                byteArrayOutputStream.write(htmlbuffer, 0, len);
            }
        }
        response.setContentDispositionFormData("attachment", new String(file.getName().getBytes("utf-8"), "ISO8859-1"));
        response.setBody(byteArrayOutputStream.toByteArray());
        return response;
    }

    @Mapping(url = "/forward")
    public Response hello4() {
        Response response = new Response();
        response.setRedirectHtml("/index2.html");
        return response;
    }

    @Mapping(url = "/error")
    public String hello5() {
        int a = 10 / 0;
        return a + "";
    }
}
