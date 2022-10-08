package cn.com.gatico.TestPattern;


import net.sf.jsqlparser.parser.CCJSqlParser;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.create.table.ColumnDefinition;
import net.sf.jsqlparser.statement.create.table.CreateTable;
import net.sf.jsqlparser.statement.select.AllTableColumns;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
    public static void main(String[] args) {
        String[] array1 = "6.5.0".split("\\.");
        String[] array2 = "6.5.0".split("\\.");
        System.out.println(Long.valueOf(array1[2]) + Long.valueOf(array1[1]) * 1000L + Long.valueOf(array1[0]) * 1000000L >= Long.valueOf(array2[2]) + Long.valueOf(array2[1]) * 1000L + Long.valueOf(array2[0]) * 1000000L);

        test2();
        System.out.println(isPhone("19966546353"));
        System.exit(1);
        String str1 = findStr("each(endpoint=7x0012016070901683ba2 metric=wan.bandwidth.rx.1 a=1)", "endpoint=([\\w.]*)");
        System.out.println(str1);

        System.out.println(Pattern.compile("^[A-Za-z0-9_,]+$").matcher("____,FCWE").matches());
//        Pattern pattern = Pattern.compile("DESCRIPTION \"(.*?)\" ");
//        Pattern pattern = Pattern.compile("SYNTAX (.*?\\) }|.*?) ");
//        Pattern pattern = Pattern.compile("INDEX \\{ (.*?) }");
//        Pattern pattern = Pattern.compile("} (.*?) OBJECT-TYPE");
//        Pattern pattern = Pattern.compile("(\\w+) (OBJECT-TYPE|OBJECT IDENTIFIER)");
//        Pattern pattern = Pattern.compile("LAST-UPDATED \"(.*?)\"");
//        Pattern pattern = Pattern.compile("ORGANIZATION \"(.*?)\"");
//        Pattern pattern = Pattern.compile("CONTACT-INFO \"(.*?)\"");
//        Pattern pattern = Pattern.compile("DESCRIPTION \"(.*?)\"");
//        Pattern pattern = Pattern.compile("(.*?) MODULE-IDENTITY");
        //Pattern pattern = Pattern.compile("(\\{ (.+) }) DEFINITIONS");
        Pattern pattern = Pattern.compile("(.*?)((\\{.*)| )DEFINITIONS");
//        Pattern pattern = Pattern.compile("(\\d+)");
        String str = "PortList ::= TEXTUAL-CONVENTION STATUS current DESCRIPTION  \"Each octet within this value specifies a set of eight ports, with the first octet specifying ports 1 through 8, the second octet specifying ports 9 through 16, etc. Within each octet, the most significant bit represents the lowest numbered port, and the least significant bit represents the highest numbered port. Thus, each port of the bridge is represented by a single bit within the value of this object. If that bit has a value of '1' then that port is included in the set of ports; the port is not included if its bit has a value of '0'.\" SYNTAX OCTET STRING  overview OBJECT IDENTIFIER DESCRIPTION \"123\"::= { swMgmt 1 }";
        //str = "reboot OBJECT-TYPE SYNTAX INTEGER { disable(0), enable(1) } MAX-ACCESS write-only STATUS current DESCRIPTION \"reboot.\" ::= { control 1 }";
        str = "CISCO-MIB { iso org(3) dod(6) internet(1) private(4) enterprises(1) 9 } DEFINITIONS";
        //str = "CISCO-MIB DEFINITIONS";
        Matcher matcher = pattern.matcher(str);
        int i = 1;
        while (matcher.find()) {
            System.out.println(matcher.group(i));
            i++;
        }

    }

    public static String findStr(String str, String pattern) {
        if (str == null || str.length() < 0) {
            return "";
        }
        Pattern r = Pattern.compile(pattern + "=([\\w.-=,]*)");
        Matcher m = r.matcher(str);
        if (m.find()) {
            return m.group(1);
        }
        return "";
    }

    public static boolean isPhone(String phone) {
        Pattern pattern = Pattern.compile("^(\\+?0?86\\-?)?((13\\d|14[57]|15[^4,\\D]|17[678]|199|18\\d)\\d{8}|170[059]\\d{7})$");
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }

    @org.junit.Test
    public void test1() {
//        Pattern pattern0 = Pattern.compile("[a-z0-9._]*=[a-z0-9._]*");
//        Pattern pattern0 = Pattern.compile("([a-z0-9._]*/=/[a-z0-9._]*)");
        String expression = "each(endpoint=7x0012016070901683ba2 metric=wan.bandwidth.rx.1 a=1 b=1 )";
        String endpoint = findStr(expression, "endpoint");
        System.out.println(endpoint);
        List<String> a = new ArrayList<>();
        String metric = findStr(expression, "metric");
        System.out.println(metric);
        expression = expression.replace("endpoint=" + endpoint, "");
        expression = expression.replace("metric=" + metric, "");

        String tag = expression.substring(expression.indexOf("(") + 1, expression.indexOf(")")).trim();
        System.out.println(tag);
//        Matcher matcher0 = pattern0.matcher("each(endpoint=7x0012016070901683ba2 metric=wan.bandwidth.rx.1 a=1)");
//        int i = 0;
//        System.out.println(matcher0.matches());
//        while (matcher0.find()) {
//            System.out.println(matcher0.group(i));
//            i++;
//        }
    }

    public static void test2() {
        String sql = "CREATE TABLE `npm_check_point` (\n" +
                "  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',\n" +
                "  `name` varchar(50) DEFAULT NULL COMMENT '名称',\n" +
                "  `asset_id` bigint(20) DEFAULT NULL COMMENT '资产id',\n" +
                "  `collection_id` bigint(20) DEFAULT NULL COMMENT '采集服务id',\n" +
                "  `password` varchar(32) DEFAULT NULL COMMENT '密码',\n" +
                "  `sn` varchar(50) DEFAULT NULL COMMENT 'sn',\n" +
                "  `status` varchar(20) DEFAULT NULL COMMENT '状态',\n" +
                "  `run_status` varchar(50) DEFAULT NULL COMMENT '服务状态',\n" +
                "  `need_sync` int(32) DEFAULT NULL COMMENT '同步配置',\n" +
                "  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',\n" +
                "  `create_time` datetime DEFAULT NULL COMMENT '创建时间',\n" +
                "  `update_time` datetime DEFAULT NULL COMMENT '更新时间',\n" +
                "  `encrypt_password` varchar(32) DEFAULT NULL COMMENT '二次加密密码',\n" +
                "  PRIMARY KEY (`id`) USING BTREE\n" +
                ") ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COMMENT='检查点';";

        try {

//            StringReader stringReader = new StringReader(sql);
//            CCJSqlParser ccjSqlParser = new CCJSqlParser(stringReader);
//            Statement statement = CCJSqlParserUtil.parseStatement(ccjSqlParser);
//            CreateTable table = ccjSqlParser.CreateTable();
//            System.out.println("----------------------------------");
//            List<ColumnDefinition>  columns =  table.getColumnDefinitions();
//            columns.forEach(columnDefinition -> {
//                System.out.println(columnDefinition.getColumnName());
//                System.out.println(columnDefinition.getColDataType());
//            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
