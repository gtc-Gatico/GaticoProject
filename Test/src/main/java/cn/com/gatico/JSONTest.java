package cn.com.gatico;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author Gatico
 * @version 1.0
 * @date "2019/12/27 16:49
 */
public class JSONTest {

    public static void main(String args[]) {
        String uuid = UUID.randomUUID().toString();
        System.out.println(uuid);
        System.out.println(UUID.fromString(uuid));
        System.out.println(UUID.fromString(uuid));
        Gson gson = new Gson();
        Map<String, String> stringStringMap = new HashMap<>();
        stringStringMap.put("first", "测试故障");
        stringStringMap.put("performance", "停机了");
        stringStringMap.put("time", new SimpleDateFormat("yyyy-MM-dd HH:mm:sss").format(new Date()));
        stringStringMap.put("remark", "这是备注");

        JsonObject jsonObject = gson.fromJson("{\"performance\":\"停机了\",\"remark\":\"这是备注\",\"time\":\"2020-03-13 11:12:042\",\"first\":\"测试故障\"}", JsonObject.class);

        jsonObject.entrySet().forEach(jsonElementEntry -> {
            System.out.println(jsonElementEntry.getKey() + "\t" + jsonElementEntry.getValue().toString());
        });
    }
}
