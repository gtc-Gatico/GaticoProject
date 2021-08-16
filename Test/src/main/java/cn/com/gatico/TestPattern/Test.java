package cn.com.gatico.TestPattern;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
    public static void main(String[] args) {
        System.out.println(isPhone("17315386061"));

        String str1 = findStr("each(endpoint=ewd145d5sa6das metric=22 tag=30)", "endpoint=([\\w.]*)");
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
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);
        if (m.find()) {
            return m.group(1);
        }
        return "";
    }

    public static boolean isPhone(String phone) {
        Pattern pattern = Pattern.compile("^(\\+?0?86\\-?)?((13\\d|14[57]|15[^4,\\D]|17[3678]|18\\d)\\d{8}|170[059]\\d{7})$");
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }
}
