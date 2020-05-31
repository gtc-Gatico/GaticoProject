package cn.com.gatico.导出错误信息;

public enum CustomerErrorConstants {

    SUCCESSFUL("000000", "请求成功"),
    INTERNAL_COMMUNICATION_ERROR("000001", "内部通信异常"),
    UNKNOWN_ERROR("000002", "未知错误"),
    SECURITY_USER_NOT_EXIST("100001", "用户不存在"),
    SECURITY_USER_PASSWORD_ERROR("100002", "密码错误"),
    SECURITY_USER_USER_NAME_REPEAT("100003", "该用户名已存在"),
    SECURITY_USER_CREATE_FAIL("100004", "用户创建失败"),
    SECURITY_USER_UPDATE_FAIL("100005", "用户修改失败"),
    SECURITY_USER_DELETE_FAIL("100006", "用户删除失败"),
    SECURITY_USER_LOGIN_FAIL("100007", "用户登录失败"),
    SECURITY_USER_LOGOUT_FAIL("100008", "用户注销失败"),

    SNAT_CIDR_LIST_FAIL("100011", "SNAT获取失败"),
    SNAT_CIDR_REMOVE_FAIL("100012", "SNAT删除失败"),
    SNAT_CIDR_MODIFY_FAIL("100013", "SNAT设置失败"),


    ARP_BINDING_CREATE_FAIL("100021", "创建ARP绑定失败"),
    ARP_BINDING_REMOVE_FAIL("100021", "删除ARP绑定失败"),
    ARP_BINDING_SET_AUTO_BINDING_FAIL("100021", "ARP自动绑定设置失败"),
    //==============================================================================================================


    UPLOAD_FILE_NULL("100021", "上传的文件为空"),
    BEGIN_TIME_ILLEGAL("200002", "开始时间非法"),
    INTERVAL_ILLEGAL("200003", "间隔参数非法"),
    ORIGIN_PASSWORD_EMPTY("200004", "原始密码为空"),
    TARGET_PASSWORD_EMPTY("200005", "新密码为空"),
    SNAT_PROTOCOL_ILLEGAL("200011", "协议类型非法"),
    SNAT_ORIGINAL_SRC_CIDR_EMPTY("200012", "原始源地址段为空"),
    SNAT_ORIGINAL_SRC_CIDR_ILLEGAL("200013", "原始源地址段非法"),
    SNAT_DST_CIDR_EMPTY("200014", "匹配目的地址段为空"),
    SNAT_DST_CIDR_ILLEGAL("200015", "匹配目的地址段非法"),
    SNAT_TARGET_SRC_IP_EMPTY("200016", "原始目的地址段为空"),
    SNAT_TARGET_SRC_IP_ILLEGAL("200017", "原始目的地址段非法"),
    SNAT_PROTOCOL_EMPTY("200018", "协议类型为空"),
    SNAT_DST_PORT_EMPTY("200019", "目的端口为空"),
    SNAT_DST_PORT_ILLEGAL("200020", "目的端口非法"),
    SNAT_IN_IF_EMPTY("200021", "入网卡为空"),
    SNAT_OUT_IF_EMPTY("200022", "出网卡为空"),

    DNAT_PROTOCOL_EMPTY("200031", "协议类型为空"),
    DNAT_PROTOCOL_ILLEGAL("200032", "协议类型非法"),
    DNAT_EXTERNAL_IP_ILLEGAL("200033", "外部地址非法"),
    DNAT_EXTERNAL_IP_EMPTY("200034", "外部地址为空"),
    DNAT_EXTERNAL_PORTS_ILLEGAL("200035", "外部端口非法"),
    DNAT_EXTERNAL_PORTS_EMPTY("200036", "外部端口为空"),
    DNAT_INTERNAL_IP_ILLEGAL("200037", "内部地址非法"),
    DNAT_INTERNAL_IP_EMPTY("200038", "内部地址为空"),
    DNAT_INTERNAL_PORT_ILLEGAL("200039", "内部端口非法"),
    DNAT_INTERNAL_PORT_EMPTY("200040", "内部端口为空"),
    DNAT_INTERNAL_PORT_IS_EMPTY("200041", "端口映射外部端口为范围端口时,内部端口不能填写"),
    DNAT_IN_IF_EMPTY("200042", "入网卡为空"),

    ARP_BINDING_IP_EMPTY("200051", "ARP绑定IP为空"),
    ARP_BINDING_IP_ILLEGAL("200052", "ARP绑定IP非法"),
    ARP_BINDING_MAC_EMPTY("200053", "ARP绑定MAC为空"),
    ARP_BINDING_MAC_ILLEGAL("200054", "ARP绑定MAC非法"),
    ARP_BINDING_ENABLE_EMPTY("200055", "是否ARP自动绑定为空"),


    ;
    private String code;

    private String message;

    CustomerErrorConstants(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public String toString() {
        return "[" + code + "] " + message;
    }

}
