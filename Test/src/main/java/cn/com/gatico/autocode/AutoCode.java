package cn.com.gatico.autocode;

import com.alibaba.fastjson.JSONObject;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserManager;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.Select;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AutoCode extends JFrame {
    public AutoCode() {
        this.setTitle("自动生成代码工具");
        this.setSize(500, 520 + 27);
        this.setLayout(null);
        this.setLocationRelativeTo(null);// 设置居中显示
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
    }

    public static void main(String[] args) {
        AutoCode autoCode = new AutoCode();
        JLabel sqlLabel = new JLabel("Sql脚本：");
        sqlLabel.setBounds(10, 20, 70, 20);
        autoCode.add(sqlLabel);
        JScrollPane jScrollPane = new JScrollPane();
        JTextArea sqlTextArea = new JTextArea();
//        sqlTextArea.setBounds(70, 20, 400, 300);
        jScrollPane.setBounds(70, 20, 400, 300);
        jScrollPane.setViewportView(sqlTextArea);
        autoCode.add(jScrollPane);

        JLabel packageNameLabel = new JLabel("包名：");
        packageNameLabel.setBounds(10, 340, 70, 20);
        autoCode.add(packageNameLabel);
        JTextField packageNameField = new JTextField();
        packageNameField.setBounds(70, 340, 400, 30);
        packageNameField.setText("com.sevenXnetworks.colossus");
        autoCode.add(packageNameField);

        JLabel filePathLabel = new JLabel("文件目录：");
        filePathLabel.setBounds(10, 390, 70, 20);
        autoCode.add(filePathLabel);

        JTextField filePathField = new JTextField();
        filePathField.setBounds(70, 390, 400, 30);
        filePathField.setText("F:\\Workspaces\\IDEA\\colossus\\src\\main\\java\\");
        filePathField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFileChooser jFileChooser = new JFileChooser();
                jFileChooser.setCurrentDirectory(new File("d://"));// 文件选择器的初始目录定为d盘
                FileSystemView fsv = FileSystemView.getFileSystemView();  //注意了，这里重要的一句
                jFileChooser.setCurrentDirectory(fsv.getHomeDirectory());
                jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);//只能选择目录
                jFileChooser.setDialogTitle("请选择要生成文件的目录");
                jFileChooser.setApproveButtonText("确定");
//                jFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                int result = jFileChooser.showOpenDialog(autoCode);
                if (JFileChooser.APPROVE_OPTION == result) {
                    String path = jFileChooser.getSelectedFile().getPath() + "\\";
                    System.out.println("path: " + path);
                    filePathField.setText(path);
                }
            }
        });
        autoCode.add(filePathField);

        JLabel fileTypeLabel = new JLabel("文件类型：");
        fileTypeLabel.setBounds(10, 440, 70, 20);
        autoCode.add(fileTypeLabel);

        List<JCheckBox> jCheckBoxes = new ArrayList<>();
        JCheckBox entiyTypeBox = new JCheckBox();
        entiyTypeBox.setText("entity");
        entiyTypeBox.setBounds(70, 440, 100, 20);
        autoCode.add(entiyTypeBox);
        jCheckBoxes.add(entiyTypeBox);

        JCheckBox beanTypeBox = new JCheckBox();
        beanTypeBox.setText("bean");
        beanTypeBox.setBounds(170, 440, 100, 20);
        autoCode.add(beanTypeBox);
        jCheckBoxes.add(beanTypeBox);

        JCheckBox voTypeBox = new JCheckBox();
        voTypeBox.setText("vo");
        voTypeBox.setBounds(270, 440, 80, 20);
        autoCode.add(voTypeBox);
        jCheckBoxes.add(voTypeBox);

        JCheckBox daoTypeBox = new JCheckBox();
        daoTypeBox.setText("dao");
        daoTypeBox.setBounds(70, 460, 100, 20);
        autoCode.add(daoTypeBox);
        jCheckBoxes.add(daoTypeBox);

        JCheckBox daoImplTypeBox = new JCheckBox();
        daoImplTypeBox.setText("daoImpl");
        daoImplTypeBox.setBounds(170, 460, 100, 20);
        autoCode.add(daoImplTypeBox);
        jCheckBoxes.add(daoImplTypeBox);

        JCheckBox serviceTypeBox = new JCheckBox();
        serviceTypeBox.setText("service");
        serviceTypeBox.setBounds(270, 460, 100, 20);
        autoCode.add(serviceTypeBox);
        jCheckBoxes.add(serviceTypeBox);

        JCheckBox serviceImplTypeBox = new JCheckBox();
        serviceImplTypeBox.setText("serviceImpl");
        serviceImplTypeBox.setBounds(350, 440, 100, 20);
        autoCode.add(serviceImplTypeBox);
        jCheckBoxes.add(serviceImplTypeBox);

        JButton createButton = new JButton("生成");
        createButton.setBounds(370, 470, 100, 30);
        createButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (sqlTextArea.getText().length() <= 0) {
                    JOptionPane.showMessageDialog(autoCode, "请输入SQL");
                    return;
                }
                String sql = sqlTextArea.getText();
                List<Map<String, Object>> obj = AnalysisSql(sql);
                if (packageNameField.getText().length() <= 0) {
                    JOptionPane.showMessageDialog(autoCode, "请输入包名");
                    return;
                }
                String packName = packageNameField.getText();
                final String filePath = filePathField.getText().length() > 0 ? filePathField.getText() : "/";
                jCheckBoxes.stream().filter(AbstractButton::isSelected).forEach(jCheckBox -> {
                    System.out.println(jCheckBox.getText());
                    CreateFile(filePath, jCheckBox.getText(), packName, obj, "java");
                });
            }
        });
        autoCode.add(createButton);
        autoCode.show();

        /*String sql = "CREATE TABLE `collection_snmp_condition` (\n" +
                "\t`id` BIGINT ( 20 ) NOT NULL AUTO_INCREMENT COMMENT '主键',\n" +
                "\t`key` VARCHAR ( 32 ) DEFAULT NULL COMMENT 'index或者oid',\n" +
                "\t`value` VARCHAR ( 32 ) DEFAULT NULL COMMENT '结果名称或值',\n" +
                "\t`op` VARCHAR ( 32 ) DEFAULT NULL COMMENT '按条件时的运算符',\n" +
                "PRIMARY KEY ( `id` ) \n" +
                ") ENGINE = INNODB DEFAULT CHARSET = utf8mb4 COMMENT = 'snmp 筛选条件'";
        List<Map<String, Object>> obj = AnalysisSql(sql);
        String filePath = "F:\\Workspaces\\IDEA\\colossus\\src\\main\\java\\";
        filePath = "F:\\Workspaces\\IDEA\\GaticoProject\\Test\\src\\main\\java\\";
        String packName = "com.sevenXnetworks.colossus";
        packName = "cn.com.gatico";
        CreateFile(filePath, "entity", packName, obj, "java");
        CreateFile(filePath, "dao", packName, obj, "java");
        CreateFile(filePath, "daoImpl", packName, obj, "java");
        CreateFile(filePath, "bean", packName, obj, "java");
        CreateFile(filePath, "vo", packName, obj, "java");
        CreateFile(filePath, "service", packName, obj, "java");
        CreateFile(filePath, "serviceImpl", packName , obj, "java");*/
    }

    //解析sql
    public static List<Map<String, Object>> AnalysisSql(String sql){
        if (sql == null || sql.length() <= 0) {
            return null;
        }
//        CCJSqlParserManager parser = new CCJSqlParserManager();
//        Statement stmt = null;
//        try {
//            stmt = parser.parse(new StringReader(sql));
//        } catch (JSQLParserException e) {
//            e.printStackTrace();
//        }
//        if (stmt instanceof Select) {
//            List withItemsList = ((Select) stmt).getWithItemsList();
//            withItemsList.forEach(o -> {
//                System.out.println(o.toString());
//            });
//        }
//        System.exit(1);
        //return list;


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
    public static void CreateFile(String filePAth, String type, String packageName, List<Map<String, Object>> param, String suffix) {
        String path = filePAth;
        File root = new File(path + packageName.replace(".", "/"));
        if (!root.exists()) {
            root.mkdirs();
        }
        String className = ToName((String) param.get(0).get("tableName"), true);
        String fileText = "";
        File file = null;
        if ("entity".equals(type.toLowerCase())) {
            file = new File(root + "/entity/" + className + "Entity." + suffix);
            fileText = getEntityTemp(packageName, param);
        } else if ("bean".equals(type.toLowerCase())) {
            file = new File(root + "/bean/" + className + "Bean." + suffix);
            fileText = getBeanTemp(packageName, param);
        } else if ("vo".equals(type.toLowerCase())) {
            file = new File(root + "/vo/" + className + "Vo." + suffix);
            fileText = getVoTemp(packageName, param);
        } else if ("dao".equals(type.toLowerCase())) {
            file = new File(root + "/dao/" + className + "Dao." + suffix);
            fileText = getDaoTemp(packageName, param);
        } else if ("daoimpl".equals(type.toLowerCase())) {
            file = new File(root + "/dao/impl/" + className + "DaoImpl." + suffix);
            fileText = getDaoImplTemp(packageName, param);
        } else if ("service".equals(type.toLowerCase())) {
            file = new File(root + "/service/" + className + "Service." + suffix);
            fileText = getServiceTemp(packageName, param);
        } else if ("serviceimpl".equals(type.toLowerCase())) {
            file = new File(root + "/service/impl/" + className + "ServiceImpl." + suffix);
            fileText = getServiceImplTemp(packageName, param);
        }


        try {
            FileWriter fw = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fw);
            bufferedWriter.write(new String(fileText.getBytes(), "UTF-8"));
            bufferedWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getEntityTemp(String packageName, List<Map<String, Object>> param) {
        String packageText = "package " + packageName + ".entity" + ";\r\n\r\n";
        StringBuffer importText = new StringBuffer();
        importText.append("import javax.persistence.*;\r\n");
        StringBuffer classText = new StringBuffer();
        classText.append("\r\n@Entity\r\n" +
                "@Table(name = \"" + param.get(0).get("tableName") + "\")\r\n");
        String className = ToName((String) param.get(0).get("tableName"), true);
        classText.append("public class " + className + "Entity {\r\n\r\n");

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
                        importText.append("import java.sql.Timestamp;\r\n");
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
                classText.append("    private " + type + " " + column + ";\r\n\r\n");
                if (map.get("column").toString().equals("id")) {
                    classGetSetText.append("    @Id\r\n");
                }
                if (map.get("autoIncrement") != null && map.get("autoIncrement").toString().equals("true")) {
                    classGetSetText.append("    @GeneratedValue(strategy = GenerationType.IDENTITY)\r\n");
                } else {
                    classGetSetText.append("    @Basic\r\n");
                }
                if (map.get("column").toString().equals("id")) {
                    classGetSetText.append("    @Column(name = \"" + map.get("column").toString() + "\", nullable = false)\r\n");
                } else {
                    classGetSetText.append("    @Column(name = \"" + map.get("column").toString() + "\")\r\n");
                }
                classGetSetText.append("    public " + type + " get" + ToName(column, true) + "() {\r\n");
                classGetSetText.append("        return " + column + ";\r\n");
                classGetSetText.append("    }\r\n\r\n");
                classGetSetText.append("    public void set" + ToName(column, true) + "(" + type + " " + column + ") {\r\n");
                classGetSetText.append("        this." + column + " = " + column + ";\r\n");
                classGetSetText.append("    }\r\n\r\n");
            }
        }
        classText.append(classGetSetText);
        classText.append("}");
        importText.append(classText);
        return packageText + importText.toString();
    }

    public static String getBeanTemp(String packageName, List<Map<String, Object>> param) {
        String packageText = "package " + packageName + ".bean" + ";\r\n\r\n";
        StringBuffer importText = new StringBuffer();
        StringBuffer classText = new StringBuffer();
        String className = ToName((String) param.get(0).get("tableName"), true);
        classText.append("public class " + className + "Bean {\r\n\r\n");

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
                        importText.append("import java.sql.Timestamp;\r\n\r\n");
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
                classText.append("    private " + type + " " + column + ";\r\n\r\n");
                classGetSetText.append("    public " + type + " get" + ToName(column, true) + "() {\r\n");
                classGetSetText.append("        return " + column + ";\r\n");
                classGetSetText.append("    }\r\n\r\n");
                classGetSetText.append("    public void set" + ToName(column, true) + "(" + type + " " + column + ") {\r\n");
                classGetSetText.append("        this." + column + " = " + column + ";\r\n");
                classGetSetText.append("    }\r\n\r\n");
            }
        }
        classText.append(classGetSetText);
        classText.append("}");
        importText.append(classText);
        return packageText + importText.toString();
    }

    public static String getVoTemp(String packageName, List<Map<String, Object>> param) {
        String packageText = "package " + packageName + ".vo" + ";\r\n\r\n";
        StringBuffer importText = new StringBuffer();
        StringBuffer classText = new StringBuffer();
        String className = ToName((String) param.get(0).get("tableName"), true);
        classText.append("public class " + className + "Vo {\r\n\r\n");

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
                        importText.append("import java.sql.Timestamp;\r\n\r\n");
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
                classText.append("    private " + type + " " + column + ";\r\n\r\n");
                classGetSetText.append("    public " + type + " get" + ToName(column, true) + "() {\r\n");
                classGetSetText.append("        return " + column + ";\r\n");
                classGetSetText.append("    }\r\n\r\n");
                classGetSetText.append("    public void set" + ToName(column, true) + "(" + type + " " + column + ") {\r\n");
                classGetSetText.append("        this." + column + " = " + column + ";\r\n");
                classGetSetText.append("    }\r\n\r\n");
            }
        }
        classText.append(classGetSetText);
        classText.append("}");
        importText.append(classText);
        return packageText + importText.toString();
    }

    private static String getDaoTemp(String packageName, List<Map<String, Object>> param) {
        String ClassName = ToName((String) param.get(0).get("tableName"), true);
        String className = ToName((String) param.get(0).get("tableName"), false);
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("packageName", packageName);
        paramMap.put("ClassName", ClassName);
        paramMap.put("className", className);

        try {
            byte[] bytes = Files.readAllBytes(Paths.get("F:\\Workspaces\\IDEA\\GaticoProject\\Test\\src\\main\\java\\cn\\com\\gatico\\autocode\\DaoTemp.txt"));
            String res = formatTemp(new String(bytes), paramMap);
            return res;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String getDaoImplTemp(String packageName, List<Map<String, Object>> param) {
        String ClassName = ToName((String) param.get(0).get("tableName"), true);
        String className = ToName((String) param.get(0).get("tableName"), false);
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("packageName", packageName);
        paramMap.put("ClassName", ClassName);
        paramMap.put("className", className);

        try {
            byte[] bytes = Files.readAllBytes(Paths.get("F:\\Workspaces\\IDEA\\GaticoProject\\Test\\src\\main\\java\\cn\\com\\gatico\\autocode\\DaoImplTemp.txt"));
            String res = formatTemp(new String(bytes), paramMap);
            return res;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String getServiceTemp(String packageName, List<Map<String, Object>> param) {
        String ClassName = ToName((String) param.get(0).get("tableName"), true);
        String className = ToName((String) param.get(0).get("tableName"), false);
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("packageName", packageName);
        paramMap.put("ClassName", ClassName);
        paramMap.put("className", className);

        try {
            byte[] bytes = Files.readAllBytes(Paths.get("F:\\Workspaces\\IDEA\\GaticoProject\\Test\\src\\main\\java\\cn\\com\\gatico\\autocode\\ServiceTemp.txt"));
            String res = formatTemp(new String(bytes), paramMap);
            return res;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String getServiceImplTemp(String packageName, List<Map<String, Object>> param) {
        String ClassName = ToName((String) param.get(0).get("tableName"), true);
        String className = ToName((String) param.get(0).get("tableName"), false);
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("packageName", packageName);
        paramMap.put("ClassName", ClassName);
        paramMap.put("className", className);

        try {
            byte[] bytes = Files.readAllBytes(Paths.get("F:\\Workspaces\\IDEA\\GaticoProject\\Test\\src\\main\\java\\cn\\com\\gatico\\autocode\\ServiceImplTemp.txt"));
            String res = formatTemp(new String(bytes), paramMap);
            return res;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String ToName(String name, boolean firstTrans) {
        while (name.indexOf("_") != -1) {
            String fgh = name.substring(name.indexOf("_") + 1, name.indexOf("_") + 2);
            name = name.replace("_" + fgh, fgh.toUpperCase());
        }
        while (name.indexOf("-") != -1) {
            String fgh = name.substring(name.indexOf("-") + 1, name.indexOf("-") + 2);
            name = name.replace("-" + fgh, fgh.toUpperCase());
        }
        if (firstTrans) {
            name = (name.charAt(0) + "").toUpperCase() + name.substring(1);
        }
        return name;
    }

    public static String formatTemp(String string, Map<String, Object> args) {
        for (String key : args.keySet()) {
            string = string.replace("${" + key + "}", args.get(key).toString());
        }
        return string;
    }
}
