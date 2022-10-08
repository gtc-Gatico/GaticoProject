package cn.com.gatico.utils;

import cn.com.gatico.Script.Js;

import javax.script.SimpleBindings;

public class ThreadUtils {
    public void start(String f, String r) {
        new Thread(() -> {
            System.out.println(System.currentTimeMillis());
            for (int i = 0; i < 1000; i++) {
                System.out.println(i);
            }
            System.out.println(System.currentTimeMillis());
            System.out.println("线程中");
            SimpleBindings bindings = new SimpleBindings();
            bindings.put("data", "" + f + "");
            Js.eval("var bf=" + r, bindings);
            Js.eval("bf(data)", bindings);
        }).start();
    }
}
