package cn.com.gatico.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class 获取易经 {
    static String startUrl = "https://www.xingzuo123.com/yijingquanwen";
    static int index = 1;
    static int size = 5;

    public static void main(String[] args) throws IOException {
        List<String> urlList = new ArrayList<>();

        for (int i = index; i <= size; i++) {
            Document document = Jsoup.connect(startUrl + "/list766_${index}.html".replace("${index}", i + "")).get();

            Element viewBox = document.body().getElementsByClass("viewbox").get(0);
            Elements elements = viewBox.getElementsByClass("preview");
            elements.forEach(element -> {
                urlList.add(element.attr("href"));
            });
        }

        System.out.println(urlList);
        AtomicInteger i = new AtomicInteger();
        urlList.forEach(s -> {
            try {
                Document document = Jsoup.connect(startUrl + s).get();
                Element viewBox = document.body().getElementsByClass("viewbox").get(0);
                Elements title = viewBox.getElementsByClass("title");
                Element content = viewBox.getElementsByClass("read-content").get(0);
                System.out.println(title.text());
                Elements p = content.getElementsByTag("p");
                p.forEach(element -> {
                    System.out.println(element.text());
                });
//                double jd = ((double)(i.incrementAndGet()) / (double)urlList.size()) * 100;
//                System.out.print("\r下载进度："+ jd +"%");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
