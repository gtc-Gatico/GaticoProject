package cn.com.gatico.rrd;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.rrd4j.ConsolFun;
import org.rrd4j.DsType;
import org.rrd4j.core.*;
import org.rrd4j.graph.RrdGraph;
import org.rrd4j.graph.RrdGraphDef;
import sun.security.timestamp.TSRequest;

import java.awt.*;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.*;
import java.util.List;

import static org.rrd4j.ConsolFun.AVERAGE;

public class TestRRD {
    //全局常量
    private static String file_path;
    private DataEntity dataEntity;

    public String createRrd(DataEntity dataEntity) throws Exception {
        this.dataEntity = dataEntity;
        //设置创建时间（unix时间戳）
        long start = dataEntity.getTime();

        //创建RrdDef，每300s接受一个新数据(unix时间戳)，如果不传入创建时间，默认为当前时间的前10秒
        RrdDef rrdDef = new RrdDef(file_path, start - 1, 300);
        rrdDef.setVersion(2);
        //添加数据源,"score"相当于数据源名称，第二个参数为数据源类型，第三个参数为心跳，一般设置为step的两倍
        for (String source : dataEntity.getDataSources()) {
            rrdDef.addDatasource(source, DsType.GAUGE, 600, 0, Double.NaN);
        }
        //一个数据库可以添加多个数据源
//        rrdDef.addDatasource("class", DsType.GAUGE, 600, 0, Double.NaN);

        //创建RRA档，AVERAGE是取平均值，xff为0.5（不用管），steps表示几个PDP合成一个CDP，rows表示数据库一共有多少列数据，RRA档在绘图的时候会用到
        rrdDef.addArchive(ConsolFun.AVERAGE, 0.5, 1, 1 * 24 * 12);//环形数据库一共存储一个月的数据
        //可以创建多个RRA，比如我要记录5个人成绩的平均分和总分，可以如下：
        /*rrdDef.addArchive(ConsolFun.AVERAGE, 0.5, 5, 1 * 24 * 12);
        rrdDef.addArchive(ConsolFun.AVERAGE, 0.5, 10, 30);
        rrdDef.addArchive(ConsolFun.TOTAL, 0.5, 5, 30 * 24 * 12);*/

        //根据数据库的定义创建rrd数据库
        RrdDb db = new RrdDb(rrdDef);
        //数据库需要关闭
        db.close();
        return null;
    }

    public String addOrUpdateRrdResource(DataEntity dataEntity) throws Exception {

        RrdDb db = new RrdDb(file_path);
        //创建样本
        Sample sample = db.createSample();
        sample.setTime(dataEntity.getTime());  //设置样本的创建时间
        for (int i = 0; i < this.dataEntity.getDataSources().length; i++) {
            sample.setValue(this.dataEntity.getDataSources()[i], dataEntity.getValues()[i]); //添加值
        }
        //保存至数据库中
        sample.update();
        //数据库必须关闭，否则样本无法存入到数据库中
        db.close();
        return null;
    }

    public Map<String, Object> selectRrdResource(long startTime, long endTime) throws Exception {

        RrdDb db = new RrdDb(file_path);

        //设置查询条件，这个AVERAGE必须和创建RRA档的类型对上，不然会出错
        FetchRequest request = db.createFetchRequest(ConsolFun.AVERAGE, startTime, endTime);
        FetchData fetchData = request.fetchData();

        Map<String, Object> map = new HashMap<>();
        for (int i = 0; i < dataEntity.getDataSources().length; i++) {
            double[] data = fetchData.getValues(dataEntity.getDataSources()[i]); //获取分数
            map.put(dataEntity.getDataSources()[i], data);
        }
        /*double[] scoreArr = fetchData.getValues("score"); //获取分数
        double[] classArr = fetchData.getValues("class"); //获取班级编号*/
        return map;
    }

    public String showRrdGraph(String imgPath, long startTime, long endTime) throws Exception {

        RrdGraphDef gDef = new RrdGraphDef();
        gDef.setWidth(600);
        gDef.setHeight(400);
        gDef.setFilename(imgPath);  //设置图片的保存路径
        gDef.setStartTime(startTime);
        gDef.setEndTime(endTime);
        String name = dataEntity.getDataSources()[0];
        for (int i = 1; i < dataEntity.getDataSources().length; i++) {
            name += "_" + dataEntity.getDataSources()[i];
        }
        gDef.setTitle(name);
        gDef.setImageFormat("png"); //设置图片格式，可以设置为png，pdf等
        RrdDb rrdDb = new RrdDb(file_path, true);
        String xmlPath = file_path.replace(".rrd", (startTime + "_" + endTime) + ".xml");
        rrdDb.exportXml(xmlPath);
        String rrdRestoredPath = file_path.replace(".rrd", (startTime + "_" + endTime) + "_restored.rrd");
        RrdDb rrdRestoredDb = new RrdDb(rrdRestoredPath, xmlPath);
        rrdDb.close();
        rrdRestoredDb.close();
        //从数据库中获取数据：Score表示你取的数据的别名，file_path：数据库路径，score：数据源，AVERAGE：类型
        for (String source : dataEntity.getDataSources()) {
            gDef.datasource(source, rrdRestoredPath, source, ConsolFun.AVERAGE);
        }
        /*gDef.datasource("Score", file_path, "score", ConsolFun.AVERAGE);
        gDef.datasource("Class", file_path, "class", ConsolFun.AVERAGE);*/

        //定义线状图:Score代表上一步你取的别名，Green是颜色，score表示在图片上的显示的这条线的名称

        for (String source : dataEntity.getDataSources()) {
            gDef.line(source, Color.getHSBColor((float) (Math.random() * 255), (float) (Math.random() * 255), (float) (Math.random() * 255)), source);
        }
       /* gDef.line("Score", Color.GREEN, "score");
        gDef.line("Class", Color.BLACK, "class");*/
        //rrd4j提供了定义面积图形，和定义线状图差不多
        //gDef.area(...);

        //创建图表
        new RrdGraph(gDef);
        return null;
    }

    public static void main(String[] args) throws Exception {

        long START = Util.getTimestamp(2019, 5, 30);
        long END = Util.getTimestamp(2019, 6, 1);
        file_path = Util.getRrd4jDemoPath("RRDTEST" + (START + "" + END) + ".rrd");
        TestRRD testRRD = new TestRRD();
        DataEntity dataEntity = new DataEntity();
        dataEntity.setTime(START);
        dataEntity.setDataSources(new String[]{"test", "Test2", "666"});
        testRRD.dataEntity = dataEntity;
        testRRD.createRrd(dataEntity);
        long t = START;
        int count = 0;
        while (t < END) {
            DataEntity dataEntity1 = new DataEntity();
            dataEntity1.setTime(t);
            dataEntity1.setValues(new double[]{Math.random() * 100, Math.random() * 100, Math.random() * 100});
            testRRD.addOrUpdateRrdResource(dataEntity1);
            t += 300;
            count++;
        }
        System.out.println(count);
        testRRD.showRrdGraph(file_path.replace(".rrd", (START + "_" + END) + ".png"), START, END);

        /*System.out.println("开始读取数据");
        RrdDb rrdDb = new RrdDb(file_path);
        FetchRequest request = rrdDb.createFetchRequest(AVERAGE, END - 10 * 300, END);
        Map<String, Object> map = new HashMap<>();
        FetchData fetchData = request.fetchData();
        System.out.println(fetchData.getColumnCount());
        for (String key : dataEntity.getDataSources()) {
            Map<Long, Object> item = new HashMap<>();
            long[] timestamps = fetchData.getTimestamps();
            for (int i = 0; i < fetchData.getColumnCount(); i++) {
                for (int j = 0; j < fetchData.getValues(i).length; j++) {
                    item.put(timestamps[i] + j * 300, fetchData.getValues(i)[j]);
                }
            }
            map.put(key, item);
        }
        String json = JSONObject.toJSONString(map);
        System.out.println(json);
        PrintWriter log = new PrintWriter(new BufferedOutputStream(new FileOutputStream(file_path.replace(".rrd", (START + "_" + END) + "第一次的数据.log"), false)));
        log.write(json);
        log.close();
        rrdDb.close();*/

       /* //写进去修改的数据
        TestRRD testRRD1 = new TestRRD();
        DataEntity dataEntity1 = new DataEntity();
        dataEntity1.setTime((END - 10 * 300));
        dataEntity1.setDataSources(new String[]{"test", "Test2", "666"});
        file_path = Util.getRrd4jDemoPath("RRDTEST__" + (END - 10 * 300 + "" + END) + ".rrd");
        testRRD1.dataEntity = dataEntity1;
        testRRD1.createRrd(dataEntity1);
        long t1 = END - 10 * 300;
        int count1 = 0;

        while (t1 < END) {
            DataEntity dataEntity2 = new DataEntity();
            dataEntity2.setTime(t1);
            List<Double> arr = new ArrayList<>();
            for (String key : dataEntity1.getDataSources()) {
                HashMap map2=(HashMap)map.get(key);
                double value = 0;
                if (null != map2.get(t1)) {
                    value = (double) map2.get(t1);
                }
                arr.add(value + 10);
            }
            double[] arr2 = new double[arr.size()];
            for (int i = 0; i < arr.size(); i++) {
                arr2[i] = arr.get(i);
            }
            dataEntity2.setValues(arr2);
            testRRD.addOrUpdateRrdResource(dataEntity2);
            t1 += 300;
            count1++;
        }
        System.out.println(count1);


        rrdDb = new RrdDb(file_path);
        request = rrdDb.createFetchRequest(AVERAGE, END - 10 * 300, END);
        map = new HashMap<>();
        fetchData = request.fetchData();
        System.out.println(fetchData.getColumnCount());
        for (String key : dataEntity.getDataSources()) {
            Map<Long, Object> item = new HashMap<>();
            long[] timestamps = fetchData.getTimestamps();
            for (int i = 0; i < fetchData.getColumnCount(); i++) {
                for (int j = 0; j < fetchData.getValues(i).length; j++) {
                    item.put(timestamps[i] + j * 300, fetchData.getValues(i)[j]);
                }
            }
            map.put(key, item);
        }
        json = JSONObject.toJSONString(map);
        System.out.println(json);
        PrintWriter log2 = new PrintWriter(new BufferedOutputStream(new FileOutputStream(file_path.replace(".rrd", "第二次写的数据.log"), false)));
        log2.write(json);
        log2.close();

        System.out.println("结束");


        rrdDb = new RrdDb(file_path);
        END = Util.getTimestamp(2019, 7, 1);
        t = rrdDb.getLastUpdateTime() + 1;
        while (t < END) {
            DataEntity dataEntity2 = new DataEntity();
            dataEntity2.setTime(t);
            dataEntity2.setValues(new double[]{Math.random() * 100, Math.random() * 100, Math.random() * 100});
            testRRD.addOrUpdateRrdResource(dataEntity2);
            t += 300;
        }
        testRRD.showRrdGraph(file_path.replace(".rrd", (START + "_" + END) + "______222.png"), START, END);
        System.out.println(fetchData.getColumnCount());*/
    }
}
