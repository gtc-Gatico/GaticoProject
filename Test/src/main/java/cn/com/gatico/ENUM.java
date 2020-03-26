package cn.com.gatico;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Gatico
 * @version 1.0
 * @date 2020/1/16 16:57
 */
public class ENUM {
    public static void main(String[] args) throws Exception {
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject;
        CustomerErrorConstants[] errorConstants = CustomerErrorConstants.values();
        Map<String, String> stringMap = new HashMap<>();
        System.out.println(errorConstants.length);
        System.out.println("====");
        for (int i = 0; i < errorConstants.length; i++) {
            jsonObject = new JSONObject();
            jsonObject.put("code", errorConstants[i].getCode());
            jsonObject.put("message", errorConstants[i].getMessage());
            if (stringMap.get(errorConstants[i].getCode()) == null) {
                stringMap.put(errorConstants[i].getCode(), errorConstants[i].getCode());
            } else {
                System.out.println(errorConstants[i].getCode());
            }
            jsonArray.add(jsonObject);
        }
        System.out.println("====");
        System.out.println(stringMap.size());
        System.out.println(jsonArray.size());
        String[] sbs = jsonArray.toJSONString().split("},");
        FileWriter fw = new FileWriter(new File("F:\\errorCodes.json"));
        for (int i = 0; i < sbs.length; i++) {
            fw.write(sbs[i] + "},\n");
            fw.flush();
        }

    }
}
