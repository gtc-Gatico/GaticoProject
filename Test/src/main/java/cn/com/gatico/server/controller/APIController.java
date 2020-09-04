package cn.com.gatico.server.controller;

import cn.com.gatico.server.ApplicationContext;
import cn.com.gatico.server.Response;
import cn.com.gatico.server.annotattions.API;
import cn.com.gatico.server.annotattions.Mapping;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonArray;
import org.apache.commons.io.FileUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@API(url = "/api")
public class APIController {

    @Mapping(url = "/getUser")
    public String getUser(String user) {
        if (user.equals("zhangsan")) {
            System.out.println(user);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("serverPort", "9999");
            jsonObject.put("serverHost", "192.168.1.177");
            JSONObject userInfo = new JSONObject();
            userInfo.put("userId", "10001");
            userInfo.put("name", "zhangsan");
            userInfo.put("nickName", "张三");
            userInfo.put("password", "123456");
            jsonObject.put("resource", userInfo);
            return jsonObject.toJSONString();
        } else if (user.equals("lisi")) {
            System.out.println(user);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("serverPort", "9999");
            jsonObject.put("serverHost", "192.168.1.177");
            JSONObject userInfo = new JSONObject();
            userInfo.put("userId", "10002");
            userInfo.put("name", "lisi");
            userInfo.put("nickName", "李四");
            userInfo.put("password", "123456");
            jsonObject.put("resource", userInfo);
            return jsonObject.toJSONString();
        }
        return null;
    }

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

    @Mapping(url = "/upload", method = "POST")
    public Response hello6(File file1) {
        System.out.println(file1.getName());
        try {
            File file = new File(ApplicationContext.uploadPath + file1.getName());
            if (file.exists()) {
                file.delete();
            }
            FileUtils.copyFile(file1, file);
            System.out.println("666");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Response response = new Response();
        response.setRedirectHtml("/index.html");
        return response;
    }

}
