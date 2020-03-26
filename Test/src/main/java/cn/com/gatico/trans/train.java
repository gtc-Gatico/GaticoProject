package cn.com.gatico.trans;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;

/**
 * @author Gatico
 * @version 1.0
 * @date 2019/12/23 20:08
 */
public class train {
    public static void main(String[] args) throws Exception {
        String stationJs = "https://kyfw.12306.cn/otn/resources/js/framework/station_name.js?station_version=1.9131";
        HttpClient httpClient = HttpClients.createDefault();
        HttpGet get = new HttpGet();
        get.setURI(new URI(stationJs));
        HttpResponse response = httpClient.execute(get);
        StatusLine statusLine = response.getStatusLine();
        int code = statusLine.getStatusCode();
        StringBuffer stringBuffer = new StringBuffer();
        if (code == 200) {
            HttpEntity entity = response.getEntity();
            InputStream inputStream = entity.getContent();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            while (bufferedReader.read() != -1) {
                stringBuffer.append(bufferedReader.readLine());
            }
        }
        String stationInfo = "'" + stringBuffer.toString().substring(stringBuffer.toString().indexOf("='@") + 3);
        System.out.println(stationInfo);
    }
}
