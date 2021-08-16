package cn.com.gatico.json;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Test2 {
    public static void main(String[] args) {
        CardUsageRes cardUsageRes = new CardUsageRes();
        cardUsageRes.setCard_id("1111");
        JSONObject o = (JSONObject)JSONObject.toJSON(cardUsageRes);
        Map map = new HashMap();
        map.putAll(o);
        System.out.println(map.get("card_id"));
    }
}
