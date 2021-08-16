package cn.com.gatico.autocode;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonToJava {
    public static void main(String[] args) {
        JSONObject jsonObject = JSON.parseObject("{\n" +
                "  \"right_value\": \"0\",\n" +
                "  \"priority\": 2,\n" +
                "  \"pause\": 1,\n" +
                "  \"op\": \"==\",\n" +
                "  \"note\": \"this is a test exp\",\n" +
                "  \"max_step\": 3,\n" +
                "  \"id\": 5,\n" +
                "  \"func\": \"all(#3)\",\n" +
                "  \"expression\": \"each(metric=agent.alive endpoint=docker-agent)\",\n" +
                "  \"action\": {\n" +
                "    \"url\": \"http://localhost:1234/callback\",\n" +
                "    \"uic\": [\n" +
                "      \"test\",\n" +
                "      \"test2\"\n" +
                "    ],\n" +
                "    \"callback\": 0,\n" +
                "    \"before_callback_sms\": 1,\n" +
                "    \"before_callback_mail\": 0,\n" +
                "    \"after_callback_sms\": 1,\n" +
                "    \"after_callback_mail\": 0\n" +
                "  }\n" +
                "}");

        Map map = new HashMap();
        print(map, jsonObject);
        System.out.println(map);
    }

    public static void print(Map strings, JSONObject jsonObject) {
//        List<String> strings1 = new ArrayList<>();
//        jsonObject.keySet().forEach(s -> {
//            strings1.add(s);
//            JSONObject o = (JSONObject) jsonObject.get(s);
//            if (o != null && o.is) {
//                System.out.println(s);
//                print(strings, o);
//            }
//        });
//
//        strings.put(jsonObject, strings1);
    }
}
