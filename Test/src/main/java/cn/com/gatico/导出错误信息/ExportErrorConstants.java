package cn.com.gatico.导出错误信息;

import cn.com.gatico.控制台颜色.ColorFontPrint;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Gatico
 * @version 1.0
 * @date 2020/1/16 16:57
 */
public class ExportErrorConstants {
    public static void main(String[] args) throws Exception {
        System.out.println(ColorFontPrint.convert("彩虹颜色的字"));
        ColorFontPrint.println("666", ColorFontPrint.GREEN, ColorFontPrint.BACKGROUND_BLUE);


        String path = "F:\\errorCodes.json";
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject;
        CustomerErrorConstants[] errorConstants = CustomerErrorConstants.values();
        Map<String, String> stringMap = new HashMap<>();
        ColorFontPrint.println("共" + errorConstants.length + "条错误信息。");
        ColorFontPrint.println("====检查冲突====");
        for (int i = 0; i < errorConstants.length; i++) {
            jsonObject = new JSONObject();
            jsonObject.put("code", errorConstants[i].getCode());
            jsonObject.put("message", errorConstants[i].getMessage());
            if (!stringMap.containsKey(errorConstants[i].getCode())) {
                stringMap.put(errorConstants[i].getCode(), errorConstants[i].getMessage());
            } else {
                ColorFontPrint.print("[code: " + errorConstants[i].getCode() + ", message: " + stringMap.get(errorConstants[i].getCode()) + "]\t");
                ColorFontPrint.print("[code: " + errorConstants[i].getCode() + ", message: " + errorConstants[i].getMessage() + "]\n");
            }
            jsonArray.add(jsonObject);
        }
        ColorFontPrint.println("====检查完毕====");
        int errorCount = (jsonArray.size() - stringMap.size());
        ColorFontPrint.print("==== ");
        ColorFontPrint.print(errorCount);
        ColorFontPrint.println(" 条冲突====");
        if (errorCount <= 0) {
            String[] sbs = jsonArray.toJSONString().split("},");
            FileWriter fw = new FileWriter(new File(path));
            for (int i = 0; i < sbs.length; i++) {
                if (i == sbs.length - 1) {
                    fw.write(sbs[i]);
                } else {
                    fw.write(sbs[i] + "},\n");
                }
                fw.flush();
            }
            ColorFontPrint.println("====生成文件路径: " + path + " ====");
        }

    }
}
