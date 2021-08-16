package cn.com.gatico.json;

public class UsageRes {
    private String month;
    private Long total_data_amount;
    private Long data_usage;

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Long getTotal_data_amount() {
        return total_data_amount;
    }

    public void setTotal_data_amount(Long total_data_amount) {
        this.total_data_amount = total_data_amount;
    }

    public Long getData_usage() {
        return data_usage;
    }

    public void setData_usage(Long data_usage) {
        this.data_usage = data_usage;
    }
}
