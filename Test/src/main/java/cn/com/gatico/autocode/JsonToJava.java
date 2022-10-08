package cn.com.gatico.autocode;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.lang.reflect.Type;
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

        Map<String, String> map = new HashMap<>();
        toJavaObject(map, jsonObject);
    }

    public static String toJavaObject(Map<String, String> resMap, JSONObject jsonObject) {
        jsonObject.keySet().forEach(key -> {
            Object o = jsonObject.get(key);
            if (o instanceof Number) {
                System.out.println(o);
                resMap.put(key, "Long");
            } else if (o instanceof String) {
                System.out.println(o);
                resMap.put(key, "String");
            } else if (o instanceof JSONObject) {
                System.out.println(o);
                char[] chars = key.toCharArray();
                chars[0] = String.valueOf(chars[0]).toUpperCase().charAt(0);
                resMap.put(key, new String(chars));
                toJavaObject(resMap, (JSONObject) o);
            } else if (o instanceof JSONArray) {
                System.out.println(o);
                resMap.put(key, "ArrayList<"+key+">");
            }
        });
        return null;
    }
}
