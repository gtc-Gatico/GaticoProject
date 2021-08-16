package cn.com.gatico.server.controller;

import cn.com.gatico.server.ApplicationContext;
import cn.com.gatico.server.Response;
import cn.com.gatico.server.annotattions.API;
import cn.com.gatico.server.annotattions.Mapping;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonArray;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

@API(url = "/api")
public class APIController {
 UserItem zs = new UserItem("https://gatico.com.cn/image/icon.jpg",getDate(),getTime(),10001L,"zhangsan","张三","1","nssb");
 UserItem ls = new UserItem("https://gatico.com.cn/image/icon2.gif",getDate(),getTime(),10002L,"lisi","李四","1","nssb");

    @Mapping(url = "/getUser")
    public String getUser(String user) throws UnsupportedEncodingException {
        if (user.equals("zhangsan")) {
            System.out.println(user);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("serverPort", "9999");
            jsonObject.put("serverHost", "192.168.1.211");
            JSONObject userInfo = new JSONObject();
            userInfo.put("userId", zs.getUserId());
            userInfo.put("userName", zs.getUserName());
            userInfo.put("nickName", zs.getTitle());
            userInfo.put("img", zs.getImg());
            userInfo.put("userDescription", "这个人很懒，什么也没留下");
            userInfo.put("password", "123456");
            JSONArray objects = new JSONArray();
            objects.add(ls);
            userInfo.put("userItemList", objects);
            jsonObject.put("resource", userInfo);
            return jsonObject.toJSONString();
        } else if (user.equals("lisi")) {
            System.out.println(user);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("serverPort", "9999");
            jsonObject.put("serverHost", "192.168.1.211");
            JSONObject userInfo = new JSONObject();
            userInfo.put("userId", ls.getUserId());
            userInfo.put("userName", ls.getUserName());
            userInfo.put("nickName", ls.getTitle());
            userInfo.put("img", ls.getImg());
            userInfo.put("userDescription","这个人很懒，什么也没留下".getBytes());
            userInfo.put("password", "123456");
            JSONArray objects = new JSONArray();
            objects.add(zs);
            userInfo.put("userItemList", objects);
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

    public String getDate(){
        return new SimpleDateFormat("yyyy-mm-dd").format(new Date());
    }
    public String getTime(){
        return new SimpleDateFormat("HH:mm:ss").format(new Date());
    }
}
