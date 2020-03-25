package cn.com.gatico.dingTalk;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class TestRobot {
    public static void main(String[] args) {
        try {
            String path = "https://oapi.dingtalk.com/robot/send?access_token=64c4ad1e5279668e1de6bdc5b67891f451c2680d869bdcf052c770cf5a68fd74";
            URL uri = new URL("https://oapi.dingtalk.com/robot/send?access_token=64c4ad1e5279668e1de6bdc5b67891f451c2680d869bdcf052c770cf5a68fd74");

            // 创建默认的httpClient实例.
            CloseableHttpClient httpclient = HttpClients.createDefault();
            // 创建httppost
            HttpPost httppost = new HttpPost(path);
            // 创建参数队列
            List formparams = new ArrayList();
            formparams.add(new BasicNameValuePair("type", "house"));
            UrlEncodedFormEntity uefEntity;
            try {
                uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
                httppost.setEntity(uefEntity);
                System.out.println("executing request " + httppost.getURI());
                CloseableHttpResponse response = httpclient.execute(httppost);
                try {
                    HttpEntity entity = response.getEntity();
                    if (entity != null) {
                        System.out.println("--------------------------------------");
                        System.out.println("Response content: " + EntityUtils.toString(entity, "UTF-8"));
                        System.out.println("--------------------------------------");
                    }
                } finally {
                    response.close();
                }
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                // 关闭连接,释放资源
                try {
                    httpclient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
