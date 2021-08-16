package cn.com.gatico.enum2;

public enum TestEnums {
    GREEN("green");

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
