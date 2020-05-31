package cn.com.gatico.utils;


import org.apache.log4j.Logger;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by lance on 16-10-20.
 */
public class StringUtils {

    private final Logger logger = Logger.getLogger(getClass());

    public static final String EMPTY = "";


    //字符串判空处理
    public static Boolean isNull(String s) {
        if (s == null || s.trim().length() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isBlank(Object target) {
        if (String.valueOf(target).equals("null")) {
            return true;
        } else {
            Pattern pattern = Pattern.compile("^[\\s]*$");
            Matcher matcher = pattern.matcher(String.valueOf(target));
            return matcher.matches();
        }
    }

    public static boolean isNotBlank(Object target) {
        return !isBlank(target);
    }

    public static boolean isPositiveInteger(String target) {
        if (target == null) {
            return false;
        }

        Pattern pattern = Pattern.compile("^[1-9]+[0-9]*$");
        Matcher matcher = pattern.matcher(target);
        return matcher.matches();
    }

    public static boolean isIpv4(String target) {
        if (target == null) {
            return false;
        }

        Pattern pattern = Pattern.compile("^([1-9]|[1-9]\\d|1\\d{2}|2[0-1]\\d|22[0-3])((\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3})$");
        Matcher matcher = pattern.matcher(target);
        return matcher.matches();
    }

    // 判断是否是ip或ip段 例:1.1.1.1 1.1.1.2/16
    public static boolean isIpv4OrIpv4Segment(String target) {
        if (target == null) {
            return false;
        }

        Pattern pattern = Pattern.compile("^([1-9]|[1-9]\\d|1\\d{2}|2[0-1]\\d|22[0-3])((\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3})((/(\\d|[1-2]\\d|[3][0-2]))?)$");
        Matcher matcher = pattern.matcher(target);
        return matcher.matches();
    }

    public static boolean isMAC(String target) {
        if (target == null) {
            return false;
        }

        Pattern pattern = Pattern.compile("[A-F\\d]{2}:[A-F\\d]{2}:[A-F\\d]{2}:[A-F\\d]{2}:[A-F\\d]{2}:[A-F\\d]{2}");
        Matcher matcher = pattern.matcher(target);
        return matcher.matches();
    }

    public static boolean isPort(int port) {
        return (port > 0 && port < 65536);
    }

    public static boolean isPort(String strPort) {
        if (!isPositiveInteger(strPort)) {
            return false;
        }
        int port = Integer.valueOf(strPort);
        return (port > 0 && port < 65536);
    }

    public static boolean isInteger(String target) {
        if (target == null) {
            return false;
        }

        Pattern pattern = Pattern.compile("^[0-9]+$");
        Matcher matcher = pattern.matcher(target);
        return matcher.matches();
    }

    public static boolean isNetMask(String target) {
        if (target == null) {
            return false;
        }

        Pattern pattern = Pattern.compile("(254|252|248|240|224|192|128|0)\\.0\\.0\\.0|255\\.(254|252|248|240|224|192|128|0)\\.0\\.0|255\\.255\\.(254|252|248|240|224|192|128|0)\\.0|255\\.255\\.255\\.(255|254|252|248|240|224|192|128|0)");
        Matcher matcher = pattern.matcher(target);
        return matcher.matches();
    }

    public static boolean isHostname(String target) {
        if (target == null) {
            return false;
        }

        Pattern pattern = Pattern.compile("^[a-zA-Z0-9]+(([.]?[a-zA-Z0-9]+)*([-]*[a-zA-Z0-9]+)*)*$");
        Matcher matcher = pattern.matcher(target);
        return matcher.matches();
    }

    public static boolean isLetterNumber(String target) {
        if (target == null) {
            return false;
        }

        Pattern pattern = Pattern.compile("^[a-zA-Z0-9]+$");
        Matcher matcher = pattern.matcher(target);
        return matcher.matches();
    }

    public static boolean isEnglish(String target) {
        if (target == null) {
            return false;
        }

        Pattern pattern = Pattern.compile("^[A-Za-z0-9]+$");
        Matcher matcher = pattern.matcher(target);
        return matcher.matches();
    }

    public static boolean isEmail(String target) {
        if (target == null) {
            return false;
        }
        Pattern pattern = Pattern.compile("^[\\w!#$%&'*+/=?'{|}~^-]+(?:\\.[\\w!#$%&'*+/=?'{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$");
        Matcher matcher = pattern.matcher(target);
        return matcher.matches();
    }

    public static String getRandomString(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }


    public static boolean isPhone(String phone) {
        if (StringUtils.isNull(phone)) {
            return false;
        }
        Pattern pattern = Pattern.compile("^(\\+?0?86\\-?)?((13\\d|14[57]|15[^4,\\D]|17[678]|18\\d)\\d{8}|170[059]\\d{7})$");
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }


    public static boolean isTel(String tel) {
        if (isBlank(tel)) return false;
        Pattern pattern = Pattern.compile("^0\\d{2,3}-\\d{7,8}$");
        return pattern.matcher(tel).matches();
    }


    public static boolean equals(String str1, String str2) {
        return str1 == null ? str2 == null : str1.equals(str2);
    }

    /**
     * 清除字符串两端空格
     *
     * @param str 字符串
     * @return
     */
    public static String trim(String str) {
        return str == null || str.equals("") ? "" : str.trim();
    }
}
