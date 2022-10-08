package cn.com.gatico.json;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;

public class 测试调用静态方法传参的导致内存增大问题 {

    public static void main(String[] args) {


        JSONArray json = new JSONArray();
        for (int i = 0; i < 1000; i++) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("samplingTime", "2021-08-19 14:55:00");
            for (int j = 0; j < 100; j++) {
                jsonObject.put(("samplingTime"+i), "2021-08-19 14:55:00");
            }
            json.add(jsonObject);
        }
        //1000000              100000              10000               1000
        //init:257949696       init:257949696      init:257949696      init:257949696
        //max:3668967424       max:3668967424      max:3668967424      max:3668967424
        //used:903540896       used:84923824       used:30063264       used:20819560
        //free:2765426528      free:3584043600     free:3638904160     free:3648147864
        //System.out.println(getStr(getArr(json, json.size()-1), "samplingTime"));
        //1000000             100000               10000               1000
        //init:257949696      init:257949696       init:257949696      init:257949696
        //max:3668967424      max:3668967424       max:3668967424      max:3668967424
        //used:903641144      used:209897336       used:28913040       used:20819536
        //free:2765326280     free:3459070088      free:3640054384     free:3648147888
        System.out.println(json.getJSONObject(json.size()-1).getString("samplingTime"));
        System.out.println();
        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
        MemoryUsage heapMemoryUsage = memoryMXBean.getHeapMemoryUsage();
        System.out.println("init:" + heapMemoryUsage.getInit());
        System.out.println("max:" + heapMemoryUsage.getMax());
        System.out.println("used:" + heapMemoryUsage.getUsed());
        System.out.println("free:" + (heapMemoryUsage.getMax()-heapMemoryUsage.getUsed()));
    }


    public static JSONObject getArr(JSONArray json, int index) {
        return json.getJSONObject(index);
    }

    public static String getStr(JSONObject json, String key) {
        return json.getString(key);
    }
}
