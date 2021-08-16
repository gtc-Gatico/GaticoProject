package cn.com.gatico.json;

import com.google.gson.*;

public class Test {
    public static Gson gson = new Gson();

    public static void main(String[] args) {

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
