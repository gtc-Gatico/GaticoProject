package cn.com.gatico.menu;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class menu {
    public static void main(String[] args) throws Exception {
        StringBuffer json = new StringBuffer();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("F://menu.txt")));
        String tmp;
        while ((tmp = bufferedReader.readLine()) != null) {
            json.append(tmp);
        }
        Map<String, List<String>> listMap = new HashMap<>();

        JSONArray root = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("path", 0);
        jsonObject.put("name", "目录");
        jsonObject.put("child", new JSONArray());
        root.add(jsonObject);
        JSONArray jsonArray = JSONArray.parseArray(json.toString());
        jsonArray.forEach(o -> {
            JSONObject object = JSONObject.parseObject(o.toString());
            if (listMap.containsKey(object.getString("value"))) {
                List<String> stringList = (List) object.get("value");
                stringList.add(object.getString("key"));
                listMap.put(object.getString("value"), stringList);
            } else {
                List<String> stringList = new LinkedList<>();
                stringList.add(object.getString("key"));
                listMap.put(object.getString("value"), stringList);
            }
        });
        System.out.println(JSONObject.parse(listMap.toString()));
    }
}
