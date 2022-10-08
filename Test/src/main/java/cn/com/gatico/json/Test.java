package cn.com.gatico.json;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.*;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static Gson gson = new Gson();
    public static  List<Long> arr1 = new ArrayList<>();
    public static long arr2[]=new long[3];
    public static void main(String[] args) {
        String str1 = "\"{\"\"ismain\"\":1\",level:67,ts:17,ver:93}";
        str1 = str1.replace("\"", "");

        arr1.add(12L);
        arr1.add(12L);
        arr1.add(11L);
        System.out.println(gson.toJson(arr1));

        arr2[0] = 1;
        arr2[1] = 1;
        arr2[2] = 1;
        System.out.println(gson.toJson(arr2));
        System.exit(1);
        System.out.println(str1);

        JSONObject jsonObject = JSONObject.parseObject(str1);
        System.out.println(jsonObject);


        String []arr = new String[]{"1","2"};
        String res = gson.toJson(arr);
        System.out.println(res);
        String str = "{" +
                "results: [" +
                "{" +
                "status: 0," +
                "return_msg: success," +
                "card_id: 89086657727465610100000000001820," +
                "usage: [" +
                "{" +
                "month: 202103," +
                "total_data_amount: 6291456.0," +
                "data_usage: 99.0" +
                "}" +
                "]" +
                "}" +
                "]" +
                "}";

        JsonParser parser = new JsonParser();
        JsonElement jsonElement = parser.parse(str);
        JsonObject results = jsonElement.getAsJsonObject();
        JsonArray asJsonArray = results.getAsJsonArray("results");
        CardUsageRes cardUsageRes = gson.fromJson((asJsonArray.get(0).toString()), CardUsageRes.class);
        System.out.println(cardUsageRes);
    }
}
