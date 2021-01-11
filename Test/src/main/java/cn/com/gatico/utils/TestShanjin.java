package cn.com.gatico.utils;

import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class TestShanjin {
    public static Gson gson = new Gson();

    public static void main(String[] args) throws Exception {
        DeviceFlowReq deviceFlowReq = new DeviceFlowReq();
        List<String> deviceIds = new LinkedList<>();
        String []device= new String[] {"6301002028000802","6301002028000752","6301002028000944","6301002028000879","6301002028000842","6301002028000794","6301002028000736","6301002028000855","6301002028000991","6301002028000741","6301002028000816","6301002028000769","6301002028000925","6301002028000766","6301002028000788","6301002028000910","6301002028001001","6301002028000844","6301002028000967","6301002028000868"};
        for (int i = 0; i < device.length; i++) {
            deviceIds.add(device[i]);
        }
        deviceFlowReq.setDevices(deviceIds);
        deviceFlowReq.setId(UUID.randomUUID().toString());
        deviceFlowReq.setTs(System.currentTimeMillis());
        String key = "a0a87bcc68c411ea9fb100163e049e4eedaaed3c48a84029be0edfcbfd2f93de";
        String uid = "a0a84eae-68c4-11ea-9fb1-00163e049e4e";


        HttpClient client = new DefaultHttpClient();
        HttpPost request = new HttpPost(new URI("http://47.100.191.45:9790/mapi_v1/get_device_flow"));
        request.addHeader("sign", HmacSHA512Util.getSign(gson.toJson(deviceFlowReq), key));
        request.addHeader("uid", uid);
        request.addHeader("content-type", "application/json");
        HttpEntity requestEntity = new StringEntity(gson.toJson(deviceFlowReq), ContentType.APPLICATION_JSON);
        request.setEntity(requestEntity);
        HttpResponse response = client.execute(request);
        System.out.println(response);
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            String responseBody = EntityUtils.toString(entity,"UTF-8");
            System.out.println(responseBody);
        }
    }
}
