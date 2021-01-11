package cn.com.gatico.utils;

public class DeviceOrderInfoReq {
    private String order_type = "month";
    private String asc_desc = "asc";

    public String getOrder_type() {
        return order_type;
    }

    public void setOrder_type(String order_type) {
        this.order_type = order_type;
    }

    public String getAsc_desc() {
        return asc_desc;
    }

    public void setAsc_desc(String asc_desc) {
        this.asc_desc = asc_desc;
    }
}
