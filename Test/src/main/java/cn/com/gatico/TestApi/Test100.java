package cn.com.gatico.TestApi;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;

public class Test100 {

    public static void main(String[] args) throws Exception {
        String postId = "YT3126094475419";
        HttpGet get = new HttpGet();
        get.setURI(new URI("https://www.kuaidi100.com/autonumber/autoComNum?resultv2=1&text=" + postId));
        get.setHeader("Cache-Control", "no-cache");
        get.setHeader("Host", " www.kuaidi100.com");
        get.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.89 Safari/537.36");
        HttpClient httpClient = new DefaultHttpClient();
        HttpResponse execute = httpClient.execute(get);
        StatusLine statusLine = execute.getStatusLine();
        System.out.println(statusLine.getStatusCode());
        HttpEntity entity = execute.getEntity();
        InputStream inputStream = entity.getContent();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuffer stringBuffer = new StringBuffer();
        String temp = "";
        while ((temp = bufferedReader.readLine()) != null) {
            stringBuffer.append(temp);
        }
        JSONObject jsonObject = JSONObject.parseObject(stringBuffer.toString());
        System.out.println(jsonObject);
        JSONArray auto = jsonObject.getJSONArray("auto");
        String type = JSONObject.parseObject(auto.get(0).toString()).getString("comCode");
        get.setURI(new URI("https://www.kuaidi100.com/query?type=" + type + "&postid=" + postId + "&temp=0.4533802170138579"));
        execute = httpClient.execute(get);
        statusLine = execute.getStatusLine();
        System.out.println(statusLine.getStatusCode());
        entity = execute.getEntity();
        inputStream = entity.getContent();
        bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        stringBuffer = new StringBuffer();
        while ((temp = bufferedReader.readLine()) != null) {
            stringBuffer.append(temp);
        }
        System.out.println(stringBuffer.toString());
    }
}
