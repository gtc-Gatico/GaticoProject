package cn.com.gatico.rrd;


import org.rrd4j.core.RrdDb;

public class TestAliDNS {
    public static void main(String[] args) throws Exception {
        String path = "/home/tianci.gao/rrd4j-demo/AliDNS.rrd";

        RrdDb rrdDb = new RrdDb(path);
        String[] ds = rrdDb.getDsNames();
        System.out.println(ds);
        //FetchRequest request = rrdDb.createFetchRequest(AVERAGE, 0, 10);
    }
}
