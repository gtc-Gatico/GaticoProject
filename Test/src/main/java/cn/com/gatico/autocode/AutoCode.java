package cn.com.gatico.autocode;

import com.alibaba.fastjson.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AutoCode {
    public static void main(String[] args) {
        String sql = "CREATE TABLE `box_dns_redirect` (\n" +
                "\t`id` BIGINT ( 20 ) NOT NULL AUTO_INCREMENT COMMENT '主键',\n" +
                "\t`box_id` BIGINT ( 20 ) DEFAULT NULL COMMENT '云盒id',\n" +
                "\t`src_cidr` VARCHAR ( 50 ) DEFAULT NULL COMMENT '源地址段',\n" +
                "\t`domain` VARCHAR ( 255 ) DEFAULT NULL COMMENT '域名（非IP）',\n" +
                "\t`dns_server_addr` VARCHAR ( 32 ) DEFAULT NULL COMMENT 'dns服务器地址',\n" +
                "\t`dns_server_port` VARCHAR ( 32 ) DEFAULT NULL COMMENT 'dns服务器端口',\n" +
                "PRIMARY KEY ( `id` ) \n" +
                ") ENGINE = INNODB DEFAULT CHARSET = utf8mb4";
        CreateFile("entity", "cn.com.gatico.entity", AnalysisSql(sql), "java");
    }

    //解析sql
    public static List<Map<String, Object>> AnalysisSql(String sql) {
        if (sql == null || sql.length() <= 0) {
            return null;
        }
        sql = sql.replace("`", "");
        sql = sql.replace("\n", " ");
        sql = sql.replace("\t", " ");
        while (sql.indexOf("  ") != -1) {
            sql = sql.replace("  ", " ");
        }
        String[] sqls = sql.split(",");
        List<Map<String, Object>> param = new ArrayList<>();
        for (int i = 0; i < sqls.length; i++) {
            String str = sqls[i].toUpperCase().trim();
            if (i == 0 && str.contains("TABLE")) {
                Map map = new HashMap();
                map.put("tableName", str.substring(str.indexOf("TABLE") + 6, str.indexOf("(") - 1).toLowerCase());
                param.add(map);
                String idStr = str.substring(str.indexOf("(") + 2);
                Map map1 = new HashMap();
                map1.put("column", idStr.split(" ")[0].toLowerCase());
                map1.put("type", Long.class);
                if (str.contains("AUTO_INCREMENT")) {
                    map1.put("autoIncrement", true);
                }
                param.add(map1);
            } else if (str.contains("PRIMARY KEY")) {
                if (!str.contains(")")) {
                    str = str + sqls[i + 1].toUpperCase();
                    i++;
                }
                Map map = new HashMap();
                map.put("primaryKey", str.substring(str.indexOf("PRIMARY KEY") + "PRIMARY KEY".length() + 2, str.indexOf(")")).trim().toLowerCase());
                param.add(map);
            } else {
                Map map = new HashMap();
                String[] strs = str.split(" ");
                map.put("column", strs[0].toLowerCase());
                if (strs[1].contains("INT") && Integer.valueOf(strs[3]) == 1) {
                    map.put("type", Boolean.class);
                } else if (strs[1].contains("CHAR")) {
                    map.put("type", String.class);
                } else if (strs[1].contains("INT") && Integer.valueOf(strs[3]) <= 4) {
                    map.put("type", Integer.class);
                } else if (strs[1].contains("INT") && Integer.valueOf(strs[3]) > 4) {
                    map.put("type", Long.class);
                } else if (strs[1].contains("DATE")) {
                    map.put("type", Timestamp.class);
                } else if (strs[1].contains("TIME")) {
                    map.put("type", Timestamp.class);
                } else if (strs[1].contains("BLOB")) {
                    map.put("type", Blob.class);
                } else if (strs[1].contains("CLOB")) {
                    map.put("type", Clob.class);
                } else {
                    continue;
                }
                param.add(map);
            }
        }
        System.out.println(JSONObject.toJSONString(param));
        return param;
    }

    //生成文件

    public static void CreateFile(String type, String packageName, List<Map<String, Object>> param, String suffix) {
        String path = AutoCode.class.getResource("/").getPath();
        path = path.replace("/target/classes", "/src/main/java");
        File root = new File(path + packageName.replace(".", "/"));
        if (!root.exists()) {
            root.mkdirs();
        }
        String className = ToName((String) param.get(0).get("tableName"), true);
        String fileText = "";
        File file = null;
        if ("entity".equals(type.toLowerCase())) {
            file = new File(root + "/" + className + "Entity." + suffix);
            System.out.println(file.getPath());
            fileText = getEntityTemp(packageName, param);
        } else if ("dao".equals(type.toLowerCase())) {
            file = new File(root + "/" + className + "Dao." + suffix);
            fileText = getDaoTemp(packageName, param);
        } else if ("service".equals(type.toLowerCase())) {
            file = new File(root + "/" + className + "Service." + suffix);
            fileText = getEntityTemp(packageName, param);
        }


        try {
            FileWriter fw = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fw);
            bufferedWriter.write(fileText);
            bufferedWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getDaoTemp(String packageName, List<Map<String, Object>> param) {
        String packageText = "package " + packageName + ";\n\n";
        StringBuffer importText = new StringBuffer();
        importText.append("import javax.persistence.*;\n");
        StringBuffer classText = new StringBuffer();
        classText.append("\n@Entity\n" +
                "@Table(name = \"" + param.get(0).get("tableName") + "\")\n");
        String className = ToName((String) param.get(0).get("tableName"), true);
        classText.append("public class " + className + "Entity {\n\n");

        StringBuffer classGetSetText = new StringBuffer();
        boolean apppended = false;
        for (int i = 0; i < param.size(); i++) {
            Map map = param.get(i);
            if (map.get("column") != null) {
                String column = ToName(map.get("column").toString(), false);
                String type = "";
                if (map.get("type").toString().contains("java.sql.Timestamp")) {
                    type = "Timestamp";
                    if (!apppended) {
                        importText.append("import java.sql.Timestamp;\n");
                        apppended = true;
                    }
                } else if (map.get("type").toString().contains("java.lang.Boolean")) {
                    type = "boolean";
                } else if (map.get("type").toString().contains("java.lang.Long")) {
                    type = "Long";
                } else if (map.get("type").toString().contains("java.lang.Integer")) {
                    type = "Integer";
                } else if (map.get("type").toString().contains("java.lang.String")) {
                    type = "String";
                }
                classText.append("\tprivate " + type + " " + column + ";\n\n");
                if (map.get("column").toString().equals("id")) {
                    classGetSetText.append("\t@Id\n");
                }
                if (map.get("autoIncrement") != null && map.get("autoIncrement").toString().equals("true")) {
                    classGetSetText.append("\t@GeneratedValue(strategy = GenerationType.IDENTITY)\n");
                } else {
                    classGetSetText.append("\t@Basic\n");
                }
                classGetSetText.append("\t@Column(name = \"" + map.get("column").toString() + "\")\n");
                classGetSetText.append("\tpublic " + type + " get" + ToName(column, true) + "() {\n");
                classGetSetText.append("\t\treturn " + column + ";\n");
                classGetSetText.append("\t}\n\n");
                classGetSetText.append("\tpublic void set" + ToName(column, true) + "(" + type + " " + column + ") {\n");
                classGetSetText.append("\t\tthis." + column + " = " + column + ";\n");
                classGetSetText.append("\t}\n\n");
            }
        }
        classText.append(classGetSetText);
        classText.append("}");
        importText.append(classText);
        return packageText + importText.toString();
    }

    public static String getEntityTemp(String packageName, List<Map<String, Object>> param) {
        String packageText = "package " + packageName + ";\n\n";
        StringBuffer importText = new StringBuffer();
        importText.append("import javax.persistence.*;\n");
        StringBuffer classText = new StringBuffer();
        classText.append("\n@Entity\n" +
                "@Table(name = \"" + param.get(0).get("tableName") + "\")\n");
        String className = ToName((String) param.get(0).get("tableName"), true);
        classText.append("public class " + className + "Entity {\n\n");

        StringBuffer classGetSetText = new StringBuffer();
        boolean apppended = false;
        for (int i = 0; i < param.size(); i++) {
            Map map = param.get(i);
            if (map.get("column") != null) {
                String column = ToName(map.get("column").toString(), false);
                String type = "";
                if (map.get("type").toString().contains("java.sql.Timestamp")) {
                    type = "Timestamp";
                    if (!apppended) {
                        importText.append("import java.sql.Timestamp;\n");
                        apppended = true;
                    }
                } else if (map.get("type").toString().contains("java.lang.Boolean")) {
                    type = "boolean";
                } else if (map.get("type").toString().contains("java.lang.Long")) {
                    type = "Long";
                } else if (map.get("type").toString().contains("java.lang.Integer")) {
                    type = "Integer";
                } else if (map.get("type").toString().contains("java.lang.String")) {
                    type = "String";
                }
                classText.append("\tprivate " + type + " " + column + ";\n\n");
                if (map.get("column").toString().equals("id")) {
                    classGetSetText.append("\t@Id\n");
                }
                if (map.get("autoIncrement") != null && map.get("autoIncrement").toString().equals("true")) {
                    classGetSetText.append("\t@GeneratedValue(strategy = GenerationType.IDENTITY)\n");
                } else {
                    classGetSetText.append("\t@Basic\n");
                }
                classGetSetText.append("\t@Column(name = \"" + map.get("column").toString() + "\")\n");
                classGetSetText.append("\tpublic " + type + " get" + ToName(column, true) + "() {\n");
                classGetSetText.append("\t\treturn " + column + ";\n");
                classGetSetText.append("\t}\n\n");
                classGetSetText.append("\tpublic void set" + ToName(column, true) + "(" + type + " " + column + ") {\n");
                classGetSetText.append("\t\tthis." + column + " = " + column + ";\n");
                classGetSetText.append("\t}\n\n");
            }
        }
        classText.append(classGetSetText);
        classText.append("}");
        importText.append(classText);
        return packageText + importText.toString();
    }

    public static String ToName(String name, boolean firstTrans) {
        while (name.indexOf("_") != -1) {
            String fgh = name.substring(name.indexOf("_") + 1, name.indexOf("_") + 2);
            name = name.replace("_" + fgh, fgh.toUpperCase());
        }
        if (firstTrans) {
            name = (name.charAt(0) + "").toUpperCase() + name.substring(1);
        }
        return name;
    }
}
