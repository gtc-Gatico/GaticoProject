package cn.com.gatico.orm.config;

import cn.com.gatico.orm.annotation.JDBC;
import cn.com.gatico.orm.jdbc.Jdbc;

@JDBC(name = "file")
public class FileJDBC extends Jdbc {

    private String url = "jdbc:mysql://47.106.38.103:3306/file?characterEncoding=UTF-8&useSSL=false";
    private String user = "file";
    private String driver = "com.mysql.jdbc.Driver";
    private String password = "gtc123456";

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public String getUser() {
        return user;
    }

    @Override
    public String getDriver() {
        return driver;
    }

    @Override
    public String getPassword() {
        return password;
    }

}