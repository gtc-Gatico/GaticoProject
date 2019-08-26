package cn.com.gatico;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CMain {
    static String systemHead =
            "//\n" +
                    "// Created by lance on 10/24/18.\n" +
                    "//\n\n" +
                    "#include <cstdio>\n" +
                    "#include <cstring>\n" +
                    "#include <string>\n" +
                    "#include <sstream>\n" +
                    "#include <iostream>\n" +
                    "#include <cstdlib>\n" +
                    "#include <unistd.h>\n\n";
    static String jsonHead = "#include <json/json.h>\n";
    static String uuidHead = "#include <uuid/uuid.h>\n";
    static String userHead =
            "#include \"../include/forge.h\"\n" +
                    "#include \"../include/exec_7xcli.h\"\n" +
                    "#include \"../include/string_utils.h\"\n" +
                    "#include \"../include/pylon_interface.h\"\n\n";
    static String namespace = "using namespace std;\n\n";
    static String excliPath = "static const char *base_exec_path = \"/usr/local/svxnetworks/\";";

    public static void main(String[] args) throws Exception {
        int fileCount = 0;
        String rootPath = "/home/tianci.gao/test/exec_7xcli.cpp";
        String childPath = "/home/tianci.gao/test/exec_7xcli/";
        File file = new File(rootPath);
        StringBuffer sb = new StringBuffer();
        InputStream is = new FileInputStream(file);
        String line; // 用来保存每行读取的内容
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        line = reader.readLine(); // 读取第一行
        while (line != null) { // 如果 line 为空说明读完了
            sb.append(line); // 将读到的内容添加到 buffer 中
            sb.append("\n"); // 添加换行符
            line = reader.readLine(); // 读取下一行
        }
        reader.close();
        is.close();
        String startStr = "int ";
        String removeStartStr = "//int ";
        String endStr = "return exitCode;\n}";
        String removeEndStr = "//    return exitCode;\n//}";
        String fileName;
        String[] fileContexts = sb.toString().split(endStr);
        int startPoint;
        int namePoint;
        List<String> editFile = new ArrayList<>();
        for (int i = 0; i < fileContexts.length - 1; i++) {
            //处理注释的方法
            String fileContext = fileContexts[i] + endStr;
            int romeveEndPoint = fileContext.indexOf(removeEndStr);
            if (romeveEndPoint != -1) {
                String removes[] = fileContext.split(removeEndStr);
                for (int j = 0; j < removes.length; j++) {
                    String removeContext = removes[j];
                    if (j != removes.length - 1) {
                        removeContext = removeContext + removeEndStr;
                    }
                    String tempStartStr = removeStartStr;
                    if (j == removes.length - 1) {
                        tempStartStr = startStr;
                    }
                    startPoint = removeContext.indexOf(tempStartStr);
                    namePoint = removeContext.indexOf("(");
                    fileName = removeContext.substring(startPoint + tempStartStr.length(), namePoint);

                    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(childPath + fileName + ".cpp")));
                    bufferedWriter.write(addHeadStr(removeContext));//添加headstr
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    System.out.println("注释部分：" + fileName + "完成。");
                    editFile.add(fileName);
                    fileCount++;
                }
            } else {
                startPoint = fileContext.indexOf(startStr);
                namePoint = fileContext.indexOf("(");
                fileName = fileContext.substring(startPoint + startStr.length(), namePoint);
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(childPath + fileName + ".cpp")));
                bufferedWriter.write(i == 0 ? fileContext : addHeadStr(fileContext));
                bufferedWriter.flush();
                bufferedWriter.close();
                System.out.println(fileName + "完成。");
                fileCount++;
            }
        }
        System.out.println("注释掉部分的文件：");
        for (int i = 0; i < editFile.size(); i++) {
            System.err.println(editFile.get(i));
        }
        System.out.println("共处理了" + fileCount + "个文件。");
        System.out.println("正常部分" + (fileCount - editFile.size()) + "个文件。");
        System.out.println("注释掉部分" + editFile.size() + "个文件。");
    }

    public static String addHeadStr(String context) {
        StringBuffer sb = new StringBuffer();
        sb.append(systemHead);
        if (context.indexOf("Json::") != -1) {
            sb.append(jsonHead);
        }
        if (context.indexOf("uuid") != -1) {
            sb.append(uuidHead);
        }
        sb.append(userHead);
        sb.append(namespace);
        sb.append(excliPath);
        return sb.toString() + context;
    }
}
