package cn.com.gatico.utils;

import cn.com.gatico.Script.Js;

import javax.script.SimpleBindings;

public class ThreadUtils {
    public void start(String f, String r) {
        new Thread(() -> {
            SimpleBindings data = new SimpleBindings();
            data.put("data", f);

            Js.eval(r, data);
        }).start();
    }
}
