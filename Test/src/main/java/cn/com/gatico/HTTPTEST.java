package cn.com.gatico;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class HTTPTEST {
    public static void main(String[] args) {

        try {
            String url = "http://www.16xx8.com";
            String url1 = "http://www.16xx8.com/photoshop/xinshoujiaocheng/rumen/";
            HttpGet get = new HttpGet(url1);
            HttpClient client = HttpClients.createDefault();
            HttpResponse httpResponse = client.execute(get);
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                InputStream inputStream = httpResponse.getEntity().getContent();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] data = new byte[1024];
                int len = 0;
                String result = "";
                if (inputStream != null) {
                    try {
                        while ((len = inputStream.read(data)) != -1) {
                            outputStream.write(data, 0, len);
                        }
                        result = new String(outputStream.toByteArray(), "gbk");

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(result);

                Document html = Jsoup.parse(result);
                Element div_box = html.getElementById("box");
                Elements a_all = div_box.getElementsByTag("a");
                System.out.println(a_all);
                List<String[]> paths = new ArrayList<>();
                a_all.forEach(element -> {
                    String path = element.attr("href");
                    String title = element.attr("title");
                    if (path.indexOf("http") == -1) {
                        path = url + path;
                    }
                    String[] arr = new String[]{path, title};
                    paths.add(arr);
                });
                PrintWriter log = null;
                for (int i = 0; i < paths.size(); i++) {
                    System.out.print(paths.get(i)[0] + "\t");
                    System.out.println(paths.get(i)[1]);
                    get = new HttpGet(paths.get(i)[0]);
                    httpResponse = client.execute(get);
                    System.out.println(httpResponse.getStatusLine().getStatusCode());
                    if (httpResponse.getStatusLine().getStatusCode() == 200) {
                        InputStream inputStreamx = httpResponse.getEntity().getContent();
                        ByteArrayOutputStream outputStreamx = new ByteArrayOutputStream();
                        data = new byte[1024];
                        len = 0;
                        result = "";
                        if (inputStreamx != null) {
                            try {
                                while ((len = inputStreamx.read(data)) != -1) {
                                    outputStreamx.write(data, 0, len);
                                }
                                result = new String(outputStreamx.toByteArray(), "gbk");
                                log = new PrintWriter(new BufferedOutputStream(new FileOutputStream("/home/tianci.gao/test/ps/" + paths.get(i)[1] + ".html", false)));
                                log.write(result);
                                log.close();
                                log = null;
                                System.out.println(i);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}