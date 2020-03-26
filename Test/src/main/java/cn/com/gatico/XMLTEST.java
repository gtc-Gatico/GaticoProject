package cn.com.gatico;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.FileWriter;
import java.util.Iterator;
import java.util.List;

/**
 * @author Gatico
 * @version 1.0
 * @date 2019/12/18 12:28
 */
public class XMLTEST {
    public static void main(String[] args) throws Exception {
        JSONArray jsonArray = new JSONArray();
        SAXReader reader = new SAXReader();
        Document document = reader.read(new File("F:\\apache-tomcat-9.0.12\\conf\\web.xml"));
        Element bookStore = document.getRootElement();
        Iterator it = bookStore.elementIterator();
        // 遍历迭代器，获取根节点中的信息（书籍）
        while (it.hasNext()) {
            System.out.println("=====开始遍历某一本书=====");
            Element book = (Element) it.next();
            // 获取book的属性名以及 属性值
            List<Attribute> bookAttrs = book.attributes();
            JSONObject jsonObject = new JSONObject();
            for (Attribute attr : bookAttrs) {
                System.out.println("属性名：" + attr.getName() + "--属性值："
                        + attr.getValue());
            }
            Iterator itt = book.elementIterator();
            while (itt.hasNext()) {
                Element bookChild = (Element) itt.next();
                System.out.println("节点名：" + bookChild.getName() + "--节点值：" + bookChild.getStringValue());
                if ("extension".equals(bookChild.getName())) {
                    jsonObject.put("extension", bookChild.getStringValue());
                }
                if ("mime-type".equals(bookChild.getName())) {
                    jsonObject.put("mime-type", bookChild.getStringValue());
                }
            }
            jsonArray.add(jsonObject);
            System.out.println(jsonArray.toJSONString());
            System.out.println("=====结束遍历某一本书=====");
            new FileWriter(new File("F:\\mime.txt")).write(jsonArray.toJSONString());
        }
    }

    //unicode转成中文
    private static String unicodeToCn(String unicode) {
        /** 以 \ u 分割，因为java注释也能识别unicode，因此中间加了一个空格*/
        String[] strs = unicode.split("\\\\u");
        String returnStr = "";
        // 由于unicode字符串以 \ u 开头，因此分割出的第一个字符是""。
        for (int i = 1; i < strs.length; i++) {
            returnStr += (char) Integer.valueOf(strs[i], 16).intValue();
        }
        return returnStr;
    }

    //中文转成unicode
    private static String cnToUnicode(String cn) {
        char[] chars = cn.toCharArray();
        String returnStr = "";
        for (int i = 0; i < chars.length; i++) {
            returnStr += "\\u" + Integer.toString(chars[i], 16);
        }
        return returnStr;
    }
}
