package cn.com.gatico.TEST;

public class DataEntity {

    private long time;

    private String[] dataSources;

    private double[] values;

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String[] getDataSources() {
        return dataSources;
    }

    public void setDataSources(String[] dataSources) {
        this.dataSources = dataSources;
    }

    public double[] getValues() {
        return values;
    }

    public void setValues(double[] values) {
        this.values = values;
    }
}
