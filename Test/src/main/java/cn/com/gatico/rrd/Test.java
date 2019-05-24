package cn.com.gatico.rrd;

import org.rrd4j.core.*;
import org.rrd4j.graph.RrdGraph;
import org.rrd4j.graph.RrdGraphDef;

import java.awt.*;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import static org.rrd4j.ConsolFun.*;
import static org.rrd4j.DsType.GAUGE;

public class Test {
    static final long SEED = 1909752002L;
    static final Random RANDOM = new Random(SEED);
    //文件名称
    static final String FILE = "demo";

    static final long START = Util.getTimestamp(2010, 4, 1);
    static final long END = Util.getTimestamp(2010, 6, 1);
    static final int MAX_STEP = 300;

    //生成图片的宽度高度
    static final int IMG_WIDTH = 500;
    static final int IMG_HEIGHT = 300;

    /**
     * <p>To start the demo, use the following command:</p>
     * <pre>
     * java -cp rrd4j-{version}.jar org.rrd4j.demo.Demo
     * </pre>
     *
     * @param args the name of the backend factory to use (optional)
     * @throws IOException Thrown
     */
    public static void main(String[] args) throws IOException {
        println("== Starting demo");
        long startMillis = System.currentTimeMillis();
        if (args.length > 0) {
            println("Setting default backend factory to " + args[0]);
            RrdDb.setDefaultFactory(args[0]);
        }
        long start = START;
        long end = END;
        //文件路径
        String rrdPath = Util.getRrd4jDemoPath(FILE + ".rrd");
        String xmlPath = Util.getRrd4jDemoPath(FILE + ".xml");
        String rrdRestoredPath = Util.getRrd4jDemoPath(FILE + "_restored.rrd");
        String imgPath = Util.getRrd4jDemoPath(FILE + ".png");
        String logPath = Util.getRrd4jDemoPath(FILE + ".log");
        PrintWriter log = new PrintWriter(new BufferedOutputStream(new FileOutputStream(logPath, false)));

        // first, define the RRD 新建一个RRD定义对象,给定路径,开始时间以及时间间隔
        RrdDef rrdDef = new RrdDef(rrdPath, start - 1, 300);
        rrdDef.setVersion(2);

        //添加数据源到RRD，通过指定它的数据源名称、源类型、心跳、最小和最大的值。
        //DsType参数  ABSOLUTE COUNTER DERIVE GAUGE
        rrdDef.addDatasource("sun", GAUGE, 600, 0, Double.NaN);
        rrdDef.addDatasource("shade", GAUGE, 600, 0, Double.NaN);

        rrdDef.addArchive(AVERAGE, 0.5, 1, 600);// 1 step, 600 rows
        rrdDef.addArchive(AVERAGE, 0.5, 6, 700);// 6 steps, 700 rows
        rrdDef.addArchive(AVERAGE, 0.5, 24, 775);
        rrdDef.addArchive(AVERAGE, 0.5, 288, 797);
        rrdDef.addArchive(TOTAL, 0.5, 1, 600);
        rrdDef.addArchive(TOTAL, 0.5, 6, 700);
        rrdDef.addArchive(TOTAL, 0.5, 24, 775);
        rrdDef.addArchive(TOTAL, 0.5, 288, 797);
        rrdDef.addArchive(MAX, 0.5, 1, 600);
        rrdDef.addArchive(MAX, 0.5, 6, 700);
        rrdDef.addArchive(MAX, 0.5, 24, 775);
        rrdDef.addArchive(MAX, 0.5, 288, 797);
        println(rrdDef.dump());
        log.println(rrdDef.dump());
        println("Estimated file size: " + rrdDef.getEstimatedSize());

        RrdDb rrdDb = new RrdDb(rrdDef);
        println("== RRD file created.");
        if (rrdDb.getRrdDef().equals(rrdDef)) {
            println("Checking RRD file structure... OK");
        } else {
            println("Invalid RRD file created. This is a serious bug, bailing out");
            return;
        }
        rrdDb.close();
        println("== RRD file closed.");

        // update database
        GaugeSource sunSource = new GaugeSource(1200, 20);
        GaugeSource shadeSource = new GaugeSource(300, 10);
        println("== Simulating one month of RRD file updates with step not larger than " +
                MAX_STEP + " seconds (* denotes 1000 updates)");
        long t = start;
        int n = 0;

        // then, create a RrdDb from the definition and start adding data
        rrdDb = new RrdDb(rrdPath);
        Sample sample = rrdDb.createSample();
        while (t <= end + 172800L) {
            sample.setTime(t);
            sample.setValue("sun", sunSource.getValue());
            sample.setValue("shade", shadeSource.getValue());
            log.println(sample.dump());
            sample.update();
            t += RANDOM.nextDouble() * MAX_STEP + 1;
            if (((++n) % 1000) == 0) {
                System.out.print("*");
            }
        }
        System.out.println(n);
        rrdDb.close();

        println("");
        println("== Finished. RRD file updated " + n + " times");
        //rrdDb.close();

        // test read-only access!
        rrdDb = new RrdDb(rrdPath, true);
        println("File reopen in read-only mode");
        println("== Last update time was: " + rrdDb.getLastUpdateTime());
        println("== Last info was: " + rrdDb.getInfo());

        // fetch data
        println("== Fetching data for the whole month");
        FetchRequest request = rrdDb.createFetchRequest(AVERAGE, start, end);
        println(request.dump());
        log.println(request.dump());
        FetchData fetchData = request.fetchData();
        println("== Data fetched. " + fetchData.getRowCount() + " points obtained");
        println(fetchData.toString());
        println("== Dumping fetched data to XML format");
        println(fetchData.exportXml());
        println("== Fetch completed");

        // dump to XML file
        println("== Dumping RRD file to XML file " + xmlPath + " (can be restored with RRDTool)");
        rrdDb.exportXml(xmlPath);
        println("== Creating RRD file " + rrdRestoredPath + " from XML file " + xmlPath);
        RrdDb rrdRestoredDb = new RrdDb(rrdRestoredPath, xmlPath);

        // close files
        println("== Closing both RRD files");
        rrdDb.close();
        println("== First file closed");
        rrdRestoredDb.close();
        println("== Second file closed");

        // create graph
        println("Creating graph " + Util.getLapTime());
        println("== Creating graph from the second file");
        RrdGraphDef gDef = new RrdGraphDef();
        gDef.setWidth(IMG_WIDTH);
        gDef.setHeight(IMG_HEIGHT);
        gDef.setFilename(imgPath);
        gDef.setStartTime(start);
        gDef.setEndTime(end);
        gDef.setTitle("Temperatures in May-June 2010");
        gDef.setVerticalLabel("temperature");
        gDef.datasource("sun", rrdRestoredPath, "sun", AVERAGE);
        gDef.datasource("shade", rrdRestoredPath, "shade", AVERAGE);
        //创建一个新的虚拟数据源通过评估一个数学表达式.   name:数据源名称  rpnExpression：RPN表达式
        //平均值
        gDef.datasource("median", "sun,shade,+,2,/");
        //差值
        gDef.datasource("diff", "sun,shade,-,ABS,-1,*");
        gDef.datasource("sine", "TIME," + start + ",-," + (end - start) + ",/,2,PI,*,*,SIN,1000,*");
        System.out.println("TIME," + start + ",-," + (end - start) + ",/,2,PI,*,*,SIN,1000,*");
        //name:数据源名称    Color: 颜色   legend：注释文本
        //line：直线   area：区域
        gDef.line("sun", Color.GREEN, "sun temp");
        gDef.line("shade", Color.BLUE, "shade temp");
        gDef.line("median", Color.MAGENTA, "median value");
        gDef.area("diff", Color.YELLOW, "difference");
        gDef.line("diff", Color.RED, null);
        gDef.line("sine", Color.CYAN, "sine fun");
        gDef.hrule(2568, Color.GREEN, "hrule");
        gDef.vrule((start + 2 * end) / 3, Color.MAGENTA, "vrule\\c");

        gDef.comment("\\r");

        gDef.gprint("sun", MAX, "maxSun = %.3f%s");
        gDef.gprint("sun", AVERAGE, "avgSun = %.3f%S\\c");
        gDef.gprint("shade", MAX, "maxShade = %.3f%S");
        gDef.gprint("shade", AVERAGE, "avgShade = %.3f%S\\c");

        gDef.setImageInfo("<img src='%s' width='%d' height = '%d'>");
        gDef.setPoolUsed(false);
        gDef.setImageFormat("png");
        println("Rendering graph " + Util.getLapTime());
        // create graph finally
        RrdGraph graph = new RrdGraph(gDef);
        println(graph.getRrdGraphInfo().dump());
        println("== Graph created " + Util.getLapTime());
        // locks info
        println("== Locks info ==");
        println(RrdSafeFileBackend.getLockInfo());
        // demo ends
        log.close();
        println("== Demo completed in " +
                ((System.currentTimeMillis() - startMillis) / 1000.0) + " sec");
    }

    static void println(String msg) {
        System.out.println(msg);
    }

    static void print(String msg) {
        System.out.print(msg);
    }

    static class GaugeSource {
        private double value;
        private double step;

        GaugeSource(double value, double step) {
            this.value = value;
            this.step = step;
        }

        long getValue() {
            double oldValue = value;
            double increment = RANDOM.nextDouble() * step;
            if (RANDOM.nextDouble() > 0.5) {
                increment *= -1;
            }
            value += increment;
            if (value <= 0) {
                value = 0;
            }
            return Math.round(oldValue);
        }
    }
}
