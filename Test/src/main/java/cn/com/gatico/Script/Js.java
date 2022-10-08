package cn.com.gatico.Script;

import cn.com.gatico.utils.FileUtil;
import cn.com.gatico.utils.HttpUtils;
import cn.com.gatico.utils.ThreadUtils;

import javax.script.*;

public class Js {
    static ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
    static ScriptEngine javascript = scriptEngineManager.getEngineByName("JavaScript");


    public static void init() {
        javascript.put("file", new FileUtil());
        javascript.put("http", new HttpUtils());
        javascript.put("thread", new ThreadUtils());

    }


    public static Object eval(String script) {
        try {
            return javascript.eval(script);
        } catch (ScriptException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object eval(String script, SimpleBindings bindings) {
        try {
            return javascript.eval(script, bindings);
        } catch (ScriptException e) {
            e.printStackTrace();
        }
        return null;
    }
}
