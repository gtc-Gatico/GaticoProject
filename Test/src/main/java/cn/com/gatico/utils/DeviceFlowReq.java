package cn.com.gatico.utils;

import java.util.List;

public class DeviceFlowReq {

    private String id;//唯一Id,标识不同请求

    private Long ts;//时间戳

    private List<String> devices;//设备id

    private DeviceOrderInfoReq order_info = new DeviceOrderInfoReq();

    private Integer page = 1;

    private Integer perpage = 20;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getTs() {
        return ts;
    }

    public void setTs(Long ts) {
        this.ts = ts;
    }

    public List<String> getDevices() {
        return devices;
    }

    public void setDevices(List<String> devices) {
        this.devices = devices;
    }

    public DeviceOrderInfoReq getOrder_info() {
        return order_info;
    }

    public void setOrder_info(DeviceOrderInfoReq order_info) {
        this.order_info = order_info;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPerpage() {
        return perpage;
    }

    public void setPerpage(Integer perpage) {
        this.perpage = perpage;
    }
}
