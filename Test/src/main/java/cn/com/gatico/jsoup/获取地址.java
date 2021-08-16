package cn.com.gatico.jsoup;

import java.net.URLEncoder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.HashMap;
import java.util.Map;

public class 获取地址 {
    public static void main(String[] args) throws Exception {
        String url = "http://map.sogou.com/EngineV6/search/json";
        Document document = Jsoup.connect(url)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36 Edg/92.0.902.67")
                .headers(str2Header())
                .data(str2Data("上海长寿路401号亚新生活广场"))
                .post();
        String str =document.toString();
        System.out.println(new String(str.getBytes(),"ISO8859-1"));
    }


    public static HashMap<String, String> str2Header(){
        String str ="Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9\n" +
                "Accept-Encoding: gzip, deflate\n" +
                "Accept-Language: zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6\n" +
                "Cache-Control: no-cache\n" +
                "Connection: keep-alive\n" +
                "Content-Length: 480\n" +
                "Content-Type: application/x-www-form-urlencoded\n" +
                "Cookie: IPISP=ISP; SMYUV=1628069498831726; SUV=1628069498830295; UM_distinctid=17b10810fe8528-0fa6a508563486-7e687969-1fa400-17b10810fe99c2; SMAPUVID=1628847433318584; IPLOC=CN1501; ho_co=; activecity=%u6DEE%u5B89%2C13249636%2C3950510%2C11\n" +
                "Host: map.sogou.com\n" +
                "Origin: http://map.sogou.com\n" +
                "Pragma: no-cache\n" +
                "Referer: http://map.sogou.com/\n" +
                "Upgrade-Insecure-Requests: 1\n" +
                "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36 Edg/92.0.902.67";
        String[] split = str.split("\n");
        HashMap<String, String> headrs = new HashMap<String, String>();
        for (int i = 0; i < split.length; i++) {
            String[] split1 = split[i].split(": ");
            headrs.put(split1[0],split1[1]);
        }
        return headrs;

    }

    public static HashMap<String, String> str2Data(String addr){
        String str ="what: keyword:"+ URLEncoder.encode(addr)+"\n" +
                "range: bound:5792000,624000,18096000,7680000\n" +
                "othercityflag: 1\n" +
                "appid: 1361\n" +
                "userdata: 3\n" +
                "encrypt: 1\n" +
                "pageinfo: 1,10\n" +
                "locationsort: 0\n" +
                "version: 7.0\n" +
                "ad: 0\n" +
                "level: 15\n" +
                "exact: 1\n" +
                "submittime: 0\n" +
                "resultTypes: poi,busline\n" +
                //"reqid: 1628847458137447\n" +
                "cb: parent.IFMS.search";
        String[] split = str.split("\n");
        HashMap<String, String> headrs = new HashMap<String, String>();
        for (int i = 0; i < split.length; i++) {
            String[] split1 = split[i].split(": ");
            headrs.put(split1[0],split1[1]);
        }
        return headrs;

    }
}
