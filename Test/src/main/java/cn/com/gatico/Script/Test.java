package cn.com.gatico.Script;

import cn.com.gatico.utils.FileUtil;

import java.util.List;

public class Test {

    public static void main(String[] args) {
        List<String> strings = FileUtil.readLines("D:\\Project\\Java\\GaticoProject\\Test\\src\\main\\java\\cn\\com\\gatico\\Script\\test.js");
        StringBuffer stringBuffer = new StringBuffer();
        strings.forEach(s -> {
            stringBuffer.append(s+"\r\n");
        });
        Js.init();
        Js.eval(stringBuffer.toString());
    }

}
