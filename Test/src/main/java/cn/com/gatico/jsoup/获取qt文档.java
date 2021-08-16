package cn.com.gatico.jsoup;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.atomic.AtomicReference;

public class 获取qt文档 {
    @Test
    public void mtTest() throws Exception{
        String href = "https://m.meituan.com/";
        Document document = Jsoup.connect(href).get();
        System.out.println(document.outerHtml());
    }

    @Test
    public void qtTest() throws Exception {
        String href = "https://qtguide.ustclug.org/";
        String json = "[{\"name\":\"序言\",\"href\":\"ch00-00.htm\",\"index\":0},{\"name\":\"致谢\",\"href\":\"ch00-01.htm\",\"index\":1},{\"name\":\"关于学习和实践\",\"href\":\"ch00-02.htm\",\"index\":2},{\"name\":\"第1章 Qt 开发环境\",\"href\":\"ch01-00.htm\",\"index\":3},{\"name\":\"1.1 Qt 介绍\",\"href\":\"ch01-01.htm\",\"index\":4},{\"name\":\"1.2 Qt 下载\",\"href\":\"ch01-02.htm\",\"index\":5},{\"name\":\"1.3 Qt 在 Windows 下安装\",\"href\":\"ch01-03.htm\",\"index\":6},{\"name\":\"1.4 Qt 在 Linux 下安装\",\"href\":\"ch01-04.htm\",\"index\":7},{\"name\":\"1.5 认识开发工具\",\"href\":\"ch01-05.htm\",\"index\":8},{\"name\":\"1.6 常见的名词术语\",\"href\":\"ch01-06.htm\",\"index\":9},{\"name\":\"第2章 从Hello World开始\",\"href\":\"ch02-00.htm\",\"index\":10},{\"name\":\"2.1 Hello World\",\"href\":\"ch02-01.htm\",\"index\":11},{\"name\":\"2.2 Hello Qt\",\"href\":\"ch02-02.htm\",\"index\":12},{\"name\":\"2.3 Hello Designer\",\"href\":\"ch02-03.htm\",\"index\":13},{\"name\":\"2.4 Hello Creator\",\"href\":\"ch02-04.htm\",\"index\":14},{\"name\":\"2.5 Qt 程序调试\",\"href\":\"ch02-05.htm\",\"index\":15},{\"name\":\"2.6 Qt 帮助文档\",\"href\":\"ch02-06.htm\",\"index\":16},{\"name\":\"第3章 字符串和字符编码\",\"href\":\"ch03-00.htm\",\"index\":17},{\"name\":\"3.1 字符编码方式\",\"href\":\"ch03-01.htm\",\"index\":18},{\"name\":\"3.2 Qt 程序字符编码\",\"href\":\"ch03-02.htm\",\"index\":19},{\"name\":\"3.3 使用 QString\",\"href\":\"ch03-03.htm\",\"index\":20},{\"name\":\"3.4 使用 QByteArray\",\"href\":\"ch03-04.htm\",\"index\":21},{\"name\":\"第4章 信号和槽函数\",\"href\":\"ch04-00.htm\",\"index\":22},{\"name\":\"4.1 元对象系统\",\"href\":\"ch04-01.htm\",\"index\":23},{\"name\":\"4.2 使用原有的信号和槽\",\"href\":\"ch04-02.htm\",\"index\":24},{\"name\":\"4.3 自定义信号和槽\",\"href\":\"ch04-03.htm\",\"index\":25},{\"name\":\"4.4 属性系统\",\"href\":\"ch04-04.htm\",\"index\":26},{\"name\":\"4.5 扩展阅读：ui_*.h代码\",\"href\":\"ch04-05.htm\",\"index\":27},{\"name\":\"4.6 扩展阅读：moc_*.cpp代码\",\"href\":\"ch04-06.htm\",\"index\":28},{\"name\":\"第5章 简单控件的使用\",\"href\":\"ch05-00.htm\",\"index\":29},{\"name\":\"5.1 按钮类的控件\",\"href\":\"ch05-01.htm\",\"index\":30},{\"name\":\"5.2 单行编辑控件\",\"href\":\"ch05-02.htm\",\"index\":31},{\"name\":\"5.3 丰富文本编辑控件\",\"href\":\"ch05-03.htm\",\"index\":32},{\"name\":\"5.4 其他输入控件\",\"href\":\"ch05-04.htm\",\"index\":33},{\"name\":\"5.5 显示类的控件\",\"href\":\"ch05-05.htm\",\"index\":34},{\"name\":\"5.6 Qt 资源文件\",\"href\":\"ch05-06.htm\",\"index\":35},{\"name\":\"第6章 控件布局\",\"href\":\"ch06-00.htm\",\"index\":36},{\"name\":\"6.1 传统窗口调整技术\",\"href\":\"ch06-01.htm\",\"index\":37},{\"name\":\"6.2 水平和垂直布局器\",\"href\":\"ch06-02.htm\",\"index\":38},{\"name\":\"6.3 网格布局器\",\"href\":\"ch06-03.htm\",\"index\":39},{\"name\":\"6.4 表单布局器\",\"href\":\"ch06-04.htm\",\"index\":40},{\"name\":\"6.5 控件尺寸调整策略\",\"href\":\"ch06-05.htm\",\"index\":41},{\"name\":\"6.6 分裂器\",\"href\":\"ch06-06.htm\",\"index\":42},{\"name\":\"第7章 文件和数据流\",\"href\":\"ch07-00.htm\",\"index\":43},{\"name\":\"7.1 文件系统概览\",\"href\":\"ch07-01.htm\",\"index\":44},{\"name\":\"7.2 基本文件读写QFile\",\"href\":\"ch07-02.htm\",\"index\":45},{\"name\":\"7.3 文本流QTextStream\",\"href\":\"ch07-03.htm\",\"index\":46},{\"name\":\"7.4 串行化数据流QDataStream\",\"href\":\"ch07-04.htm\",\"index\":47},{\"name\":\"7.5 其他文件操作类\",\"href\":\"ch07-05.htm\",\"index\":48},{\"name\":\"第8章 基于条目的控件\",\"href\":\"ch08-00.htm\",\"index\":49},{\"name\":\"8.1 列表控件\",\"href\":\"ch08-01.htm\",\"index\":50},{\"name\":\"8.2 表格控件\",\"href\":\"ch08-02.htm\",\"index\":51},{\"name\":\"8.3 树形控件\",\"href\":\"ch08-03.htm\",\"index\":52},{\"name\":\"8.4 基于条目控件的自定义特性\",\"href\":\"ch08-04.htm\",\"index\":53},{\"name\":\"第9章 数据容器\",\"href\":\"ch09-00.htm\",\"index\":54},{\"name\":\"9.1 顺序容器：QList、QQueue和QLinkedList\",\"href\":\"ch09-01.htm\",\"index\":55},{\"name\":\"9.2 顺序容器：QVector、QStack\",\"href\":\"ch09-02.htm\",\"index\":56},{\"name\":\"9.3 关联容器：QMap、QMultiMap\",\"href\":\"ch09-03.htm\",\"index\":57},{\"name\":\"9.4 关联容器：QHash、QMultiHash和QSet\",\"href\":\"ch09-04.htm\",\"index\":58},{\"name\":\"9.5 数据容器的迭代器\",\"href\":\"ch09-05.htm\",\"index\":59},{\"name\":\"第10章 控件容器\",\"href\":\"ch10-00.htm\",\"index\":60},{\"name\":\"第11章 对话框和多 UI 使用\",\"href\":\"ch11-00.htm\",\"index\":61},{\"name\":\"第11章 主窗口程序和菜单工具栏\",\"href\":\"ch11-00.htm\",\"index\":62},{\"name\":\"第12章 丰富一下主窗口程序\",\"href\":\"ch12-00.htm\",\"index\":63},{\"name\":\"第13章 多文档主窗口程序\",\"href\":\"ch13-00.htm\",\"index\":64},{\"name\":\"第14章 模型和视图\",\"href\":\"ch14-00.htm\",\"index\":65},{\"name\":\"第15章 定制控件和窗口\",\"href\":\"ch15-00.htm\",\"index\":66},{\"name\":\"第16章 2D 绘图\",\"href\":\"ch15-00.htm\",\"index\":67},{\"name\":\"第17章 QWT 图表绘制\",\"href\":\"ch16-00.htm\",\"index\":68},{\"name\":\"第18章 OpenGL 3D 绘图\",\"href\":\"ch17-00.htm\",\"index\":69},{\"name\":\"第19章 图像处理和 OpenCV\",\"href\":\"ch18-00.htm\",\"index\":70},{\"name\":\"第19章 多线程\",\"href\":\"ch19-00.htm\",\"index\":71},{\"name\":\"第20章 多进程\",\"href\":\"ch20-00.htm\",\"index\":72},{\"name\":\"第21章 同步机制\",\"href\":\"ch21-00.htm\",\"index\":73},{\"name\":\"第22章 TCP 通信\",\"href\":\"ch22-00.htm\",\"index\":74},{\"name\":\"第23章 UDP 通信\",\"href\":\"ch23-00.htm\",\"index\":75},{\"name\":\"第24章 组播通信\",\"href\":\"ch24-00.htm\",\"index\":76},{\"name\":\"第25章 多线程 TCP 通信\",\"href\":\"ch25-00.htm\",\"index\":77},{\"name\":\"第26章 FTP 客户端\",\"href\":\"ch26-00.htm\",\"index\":78},{\"name\":\"第27章 简易 Web 浏览器\",\"href\":\"ch27-00.htm\",\"index\":79},{\"name\":\"第28章 简易 Web 服务器\",\"href\":\"ch28-00.htm\",\"index\":80},{\"name\":\"第29章 数据库编程初探\",\"href\":\"ch29-00.htm\",\"index\":81},{\"name\":\"第30章 多种数据库使用示例\",\"href\":\"ch30-00.htm\",\"index\":82},{\"name\":\"第31章 XML 和数据库应用案例\",\"href\":\"ch31-00.htm\",\"index\":83},{\"name\":\"第32章 音频播放和录音\",\"href\":\"ch32-00.htm\",\"index\":84},{\"name\":\"第33章 视频播放和采集\",\"href\":\"ch33-00.htm\",\"index\":85},{\"name\":\"第34章 音视频网络聊天\",\"href\":\"ch34-00.htm\",\"index\":86},{\"name\":\"第35章 代码直接集成方式\",\"href\":\"ch35-00.htm\",\"index\":87},{\"name\":\"第36章 链接库方式\",\"href\":\"ch36-00.htm\",\"index\":88},{\"name\":\"第37章 动态插件方式\",\"href\":\"ch37-00.htm\",\"index\":89},{\"name\":\"第38章 国际化\",\"href\":\"ch38-00.htm\",\"index\":90},{\"name\":\"第39章 利用 Qt 动态库发布\",\"href\":\"ch39-00.htm\",\"index\":91},{\"name\":\"第40章 利用 Qt 静态库发布\",\"href\":\"ch40-00.htm\",\"index\":92},{\"name\":\"第41章 制作自己的安装包\",\"href\":\"ch41-00.htm\",\"index\":93},{\"name\":\"Qt Creator 编译环境配置\",\"href\":\"configs.htm\",\"index\":94},{\"name\":\"Qt 5.4.0 模块概览\",\"href\":\"modules.htm\",\"index\":95},{\"name\":\"Qt 设计师控件概览\",\"href\":\"widgets.htm\",\"index\":96},{\"name\":\"GNU 自由文档许可证\",\"href\":\"LICENSE.htm\",\"index\":97}]";

        JsonParser jsonParser = new JsonParser();
        JsonElement parse = jsonParser.parse(json);
        JsonArray asJsonArray = parse.getAsJsonArray();
        AtomicReference<FileWriter> fileWriter = new AtomicReference<>();
        asJsonArray.forEach(jsonElement -> {
            System.out.println(jsonElement.toString());
            String path = jsonElement.getAsJsonObject().get("href").getAsString();
//            String res = HttpUtils.get(href.concat(path));
            Document document;
            try {
                document = Jsoup.connect(href.concat(path)).get();
                Elements elementsByTag = document.getElementsByTag("img");
                if (!elementsByTag.isEmpty()) {
                    elementsByTag.forEach(element -> {
                        String src = element.attr("src");

                        try {
                            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(href + src).openConnection();
                            InputStream inputStream = httpURLConnection.getInputStream();
                            File file = new File("F:\\Qt\\doc\\" + src);
                            if (!file.getParentFile().exists()) {
                                file.getParentFile().mkdirs();
                            }
                            FileOutputStream fileOutputStream = new FileOutputStream(new File("F:\\Qt\\doc\\" + src));
                            byte[] arr = new byte[inputStream.available()];
                            inputStream.read(arr);
                            fileOutputStream.write(arr);
                            fileOutputStream.flush();
                            fileOutputStream.close();
                            inputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
