package cn.com.gatico.反射.TestClass;

import cn.com.gatico.反射.annotattions.API;
import cn.com.gatico.反射.annotattions.Mapping;

@API(url = "/api")
public class APITest {

    @Mapping(url = "/hello", method = "post")
    public String hello(String str) {
        System.out.println(str);
        return "hello:" + str;
    }
}
