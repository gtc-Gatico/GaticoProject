package cn.com.gatico.rrd;

import org.rrd4j.ConsolFun;
import org.rrd4j.DsType;
import org.rrd4j.core.*;
import org.rrd4j.graph.RrdGraph;
import org.rrd4j.graph.RrdGraphDef;

import java.awt.*;
import java.io.IOException;

import static org.rrd4j.ConsolFun.*;
import static org.rrd4j.ConsolFun.MAX;

public class Test2 {
    public static void main(String[] args) throws Exception {
        String path = Util.getRrd4jDemoPath("gatico" + ".rrd");
        String imgpath = Util.getRrd4jDemoPath("gatico" + ".png");
        String rrdRestoredPath = Util.getRrd4jDemoPath("gatico" + "_restored.rrd");
        String xmlPath = Util.getRrd4jDemoPath("gatico" + ".xml");
        long begin = Util.getTimestamp(2010, 4, 1);
        long end = Util.getTimestamp(2010, 6, 1);
        System.out.println(end-begin);
        RrdDef rrdDef = new RrdDef(path, begin - 1, 300);
        rrdDef.setVersion(2);
        rrdDef.addDatasource("input", DsType.GAUGE, 600, 0, Double.NaN);
        rrdDef.addDatasource("output", DsType.GAUGE, 600, 0, Double.NaN);
        rrdDef.addArchive(ConsolFun.AVERAGE, 0.5, 1, 30 * 24 * 12);//环形数据库一共存储一个月的数据
        //可以创建多个RRA，比如我要记录5个人成绩的平均分和总分，可以如下：
        rrdDef.addArchive(ConsolFun.AVERAGE, 0.5, 5, 30 * 24 * 12);
        rrdDef.addArchive(ConsolFun.TOTAL, 0.5, 5, 30 * 24 * 12);

        RrdDb rrdDb = new RrdDb(rrdDef);
        rrdDb.close();

        rrdDb = new RrdDb(path);
        Sample sample = rrdDb.createSample();
        for (long i=begin; i < end ; i++) {
            sample.setTime(i);
            sample.setValue("input", Math.random() * 100);
            sample.setValue("output", Math.random() * 100);
            sample.update();
        }
        rrdDb.close();
        System.out.println(rrdDb.getInfo());
        rrdDb = new RrdDb(path, true);
        long endtimer = rrdDb.getLastUpdateTime();
        System.out.println(endtimer);
        FetchRequest fetchRequest = rrdDb.createFetchRequest(ConsolFun.AVERAGE, begin, end);
        FetchData fetchData = fetchRequest.fetchData();
        System.out.println(("== Data fetched. " + fetchData.getRowCount() + " points obtained"));
        System.out.println(fetchData.toString());
        rrdDb.exportXml(xmlPath);
        RrdDb rrdRestoredDb = new RrdDb(rrdRestoredPath, xmlPath);
        rrdDb.close();
        rrdRestoredDb.close();

        System.out.println("0--------");
        RrdGraphDef gDef = new RrdGraphDef();
        gDef.setWidth(500);
        gDef.setHeight(500);
        gDef.setFilename(imgpath);
        gDef.setStartTime(begin);
        gDef.setEndTime(end);
        gDef.setTitle("hello");
        gDef.setVerticalLabel("hello");
        gDef.datasource("input", rrdRestoredPath, "input", AVERAGE);
        gDef.datasource("output", rrdRestoredPath, "output", AVERAGE);

        gDef.line("input", Color.GREEN, "input temp");
        gDef.line("output", Color.BLUE, "output temp");

        gDef.setImageInfo("<img src='%s' width='%d' height = '%d'>");
        gDef.setPoolUsed(false);
        gDef.setImageFormat("png");
        RrdGraph graph = new RrdGraph(gDef);
        System.out.println(graph.getRrdGraphInfo().dump());
    }

    public void CreateRRD(long start) {
        String rrdPath = "/Users/yangengzhe/Desktop/a.rrd"; // 文件路径
        String imgPath = "/Users/yangengzhe/Desktop/a.png";
        String factory = "FILE"; // 设置文件的保存方式 1.FILE 文件形式 2.SAFE 线程安全 3.NIO 缓冲区 4.MEMORY 内存
        RrdDb.setDefaultFactory(factory);
        //开始创建数据库
        RrdDef rrdDef = new RrdDef(rrdPath, start - 1, 60); // 新建一个RRD定义对象,给定路径,开始时间以及时间间隔
        rrdDef.setVersion(2); // 文件版本
        rrdDef.addDatasource("sun", DsType.GAUGE, 600, 0, Double.NaN); //单一数据源添加到RRD定义通过指定它的数据源名称、源类型DsType包含4个参数 ABSOLUTE COUNTER计数器 DERIVE GAUGE直径、心跳、最小和最大的值。
        //ConsolFun 被存储存储数据的平均 TOTAL 总的数据点存储 MAX最大的数据点存储
        rrdDef.addArchive(ConsolFun.AVERAGE, 0.5, 1, 80);//consolFun 合并函数 xff: x档案的元素.有效的值在0和1之间。 steps: 数量的存步伐 rows数量的存档行
        try {
            RrdDb rrdDb = new RrdDb(rrdDef); // 建立好模型
            rrdDb.close();
        } catch (Exception ex) {
        }
    }


}
