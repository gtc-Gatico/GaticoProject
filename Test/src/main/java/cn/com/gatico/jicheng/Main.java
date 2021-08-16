package cn.com.gatico.jicheng;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Test test = new Test();
        test.setName("123");
        test = JSONObject.parseObject("{'name':'123','num':a}", Test.class);
        System.out.println("test = " + test.name);
        System.out.println("test.num = " + test.num);
        List<Test> arr = new ArrayList<>();
        //arr.add(test);
        List<Test2> arr2 = new ArrayList<>();
        arr.forEach(o -> {
            Test2 t = new Test2();
            t.setName(o.getName());
            arr2.add(t);
        });
//        arr2 = arr.stream().map(test1 -> {
//            Test2 t = new Test2();
//            t.setName(test1.getName());
//            return t;
//        }).collect(Collectors.toList());
        System.out.println(JSONArray.toJSON(arr2).toString());
        arr2.forEach(test2 -> System.out.println("test2= " + test2.getName()));
    }

}
