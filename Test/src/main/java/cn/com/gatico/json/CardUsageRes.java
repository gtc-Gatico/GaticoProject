package cn.com.gatico.json;

import java.util.List;

public class CardUsageRes {
    private String status;
    private String return_msg;
    private String card_id;
    private List<UsageRes> usage;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReturn_msg() {
        return return_msg;
    }

    public void setReturn_msg(String return_msg) {
        this.return_msg = return_msg;
    }

    public String getCard_id() {
        return card_id;
    }

    public void setCard_id(String card_id) {
        this.card_id = card_id;
    }

    public List<UsageRes> getUsage() {
        return usage;
    }

    public void setUsage(List<UsageRes> usage) {
        this.usage = usage;
    }
}
