package cn.com.gatico.enum1;

public enum TestEnums {
    GREEN("GREEN");

    String code;
    TestEnums(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
