package cn.com.gatico;

import cn.com.gatico.SSL.My509TrustManager;
import cn.com.gatico.utils.HmacSHA256Util;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.security.SecureRandom;
import java.sql.Timestamp;
import java.util.UUID;

public class RedTeaTest {
    //测试
//    public static String ACCESS_KEY = "33b0682c92224cb6b9ef34567ec56075";
//    public static String ACCESS_SECRET = "b63e83149e7b493a8dcf8cf135e39d2d";
//    public static String CALLBACK_KEY = "b200dfddb6ab40e8b24a50b385d95562";
//    public static String CALLBACK_SECRET = "72e6e1d58a8745c9854e956b5584e417";
//    public static String redTeaUrl = "https://rcp.staging.redtea.dev";
//89086657727465610100000000001820

    //生产
    public static String ACCESS_KEY = "f0d519625a47451fa5f37dda6d60975b";
    public static String ACCESS_SECRET = "7fc562a49cc64665af24f3e1e0b1b9df";
    public static String CALLBACK_KEY = "51f03257c8444917bd8a202b53fe63d1";
    public static String CALLBACK_SECRET = "ee4314d6f88744ce9d464d2b28835481";
    public static String redTeaUrl = "https://rcp.redtea.io";
//89023022000010000000000036877298
    //862607053541719
    public static void main(String[] args) throws Exception {

        TrustManager[] tm = {new My509TrustManager()};//1.生成trustmanager数组
        SSLContext ssl = SSLContext.getInstance("TLS"); //2.得到sslcontext实例。SSL TSL 是一种https使用的安全传输协议
        ssl.init(null, tm, new SecureRandom());//初始化sslcontext
        SSLSocketFactory sslSocketFactory = ssl.getSocketFactory();//得到sslSocketFactory实例

        String str = redTeaUrl + "/api/v1/query/card_usage";
        str = redTeaUrl + "/api/v1/query/device_infos";
        URL realUrl = new URL(str);
        HttpsURLConnection conn = (HttpsURLConnection) realUrl.openConnection();
        conn.setRequestMethod("POST");
//        conn.setSSLSocketFactory(sslSocketFactory);//设置sllsocketfactory
        conn.setRequestProperty("accept", "*/*");
        conn.setRequestProperty("Accept-Charset", "utf-8");
        conn.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.90 Safari/537.36");
//        conn.setHostnameVerifier((arg0, arg1) -> true);
        JSONObject param = new JSONObject();
        //card_usage
//        JSONArray cardIds = new JSONArray();
//        cardIds.add("89023022000010000000000036877298");
//        param.put("card_ids", cardIds);
//        JSONArray months = new JSONArray();
//        months.add("202104");
//        months.add("202103");
//        param.put("months", months);

        //device_info
        JSONArray imeis = new JSONArray();
        imeis.add("867184037723776");
        param.put("imeis", imeis);

        String requestId = UUID.randomUUID().toString();
        Long timestamp = new Timestamp(System.currentTimeMillis()).getTime();

        String signature = HmacSHA256Util.sha256_HMAC(ACCESS_KEY + timestamp.toString() + requestId + param.toString(), ACCESS_SECRET).toLowerCase();
        System.out.println("requestId\t" + requestId);
        System.out.println("signature\t" + signature);
        System.out.println("timestamp\t" + timestamp.toString());
        System.out.println(param.toJSONString());
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Access-Key", ACCESS_KEY);
        conn.setRequestProperty("Request-ID", requestId);
        conn.setRequestProperty("Sign-Method", "HMAC-SHA256");
        conn.setRequestProperty("Signature", signature);
        conn.setRequestProperty("Timestamp", timestamp.toString());
        conn.setRequestProperty("Version", "1.0");
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.connect();
        PrintWriter out = new PrintWriter(conn.getOutputStream());
        out.print(param);
        out.flush();
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
        StringBuffer result = new StringBuffer();
        String line;
        while ((line = in.readLine()) != null) {
            result.append(line);
        }
        System.out.println(result.toString());
    }
}
