package cn.com.gatico.rrd;


import org.rrd4j.core.RrdDb;

public class TestAliDNS {
    public static void main(String[] args) throws Exception {
        String path = "D:\\Project\\Java\\GaticoProject\\Test\\src\\main\\resources\\fe0038b4c89de07856b14b059d1eb4d9_GAUGE_60.rrd";

        RrdDb rrdDb = new RrdDb(path);
        String[] ds = rrdDb.getDsNames();
        System.out.println(ds);
        //FetchRequest request = rrdDb.createFetchRequest(AVERAGE, 0, 10);
    }
}
