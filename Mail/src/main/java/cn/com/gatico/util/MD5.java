package cn.com.gatico.util;

import java.util.Map;
import java.util.TreeMap;

public class MD5 {
    private Map<Integer, Character> tabjie = new TreeMap<Integer, Character>();
    private Map<Character, Integer> tabjia = new TreeMap<Character, Integer>();
    static MD5 md5 = new MD5();

    public MD5() {
        int j = 0;
        for (int i = 48; i <= 122; i++) {
            if (i >= 48 && i <= 57) {
                tabjia.put((char) (i), j);
                tabjie.put(j, (char) (i));
                j++;
            } else if (i >= 65 && i <= 90) {
                tabjia.put((char) (i), j);
                tabjie.put(j, (char) (i));
                j++;
            } else if (i >= 97) {
                tabjia.put((char) (i), j);
                tabjie.put(j, (char) (i));
                j++;
            }
        }
        tabjia.put('+', 62);
        tabjia.put('/', 63);
        tabjie.put(62, '+');
        tabjie.put(63, '/');
    }

    private String getMd5String(String str) {
        char[] arr = str.toCharArray();
        String md5 = new String();
        for (char c : arr) {
            md5 += (char) ((int) tabjia.get(c));
        }
        return md5;
    }

    private String getSimpleString(String str) {
        String[] arr = new String[]{};
        int j = 0;
        for (int i = 1; i < str.length(); i += 2) {
            arr[j] = str.substring(i, i + 1);
        }
        String Simple = new String();
        int i;
        for (String c : arr) {
            i = c.charAt(0);
            char cstr = (char) (i - 10);
            Simple += cstr;
        }
        return Simple;
    }

    public static String encode(String str) {
        return md5.getMd5String(str);
    }

    public static String dencode(String str) {
        return md5.getSimpleString(str);
    }
}

