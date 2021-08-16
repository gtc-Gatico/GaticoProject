package cn.com.gatico.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;

public class 获取Ip {
    public static void main(String[] args) throws IOException, InterruptedException {
        PrintWriter log = new PrintWriter(new BufferedOutputStream(new FileOutputStream(new File("D:\\ip.txt"))));
        int index = 174;
        String path = "https://www.89ip.cn/index_${index}.html";
        String url = path.replace("${index}", "" + index);
        Document document = Jsoup.connect(url).get();

        for (int i = 1; i <= index; i++) {
            System.out.println(i);
            Elements tr = document.getElementsByClass("layui-table").select("tr");
            for (int j = 1; j < tr.size(); j++) {
                Element element = tr.get(j);
                Elements td = element.children();
                td.forEach(element1 -> {
                    log.write(element1.html() + "\t");
                });
                log.write("\n");
            }
            log.flush();
            url = path.replace("${index}", "" + i);
            document = Jsoup.connect(url).get();
            Thread.sleep(3000L);
        }
        log.flush();
        log.close();
    }
}
