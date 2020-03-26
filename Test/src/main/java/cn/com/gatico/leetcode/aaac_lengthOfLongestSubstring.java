package cn.com.gatico.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Gatico
 * @version 1.0
 * @date 2019/10/26 15:03
 */
public class aaac_lengthOfLongestSubstring {
    public static void main(String[] args) {
        String str = "vbxpvwkkteaiob";
        System.out.println(str.split("").length);
        System.out.println(lengthOfLongestSubstring(str));


    }

    public static int lengthOfLongestSubstring(String s) {
        if ("".equals(s)) {
            return 0;
        }
        String tmpStr = s;
        List<Integer> cc = new ArrayList();
        while (tmpStr.length() > 0) {
            cc.add(strSub(tmpStr));
            tmpStr = tmpStr.substring(1);
        }

        Collections.sort(cc);
        return cc.get(cc.size() - 1);
    }

    public static int strSub(String s) {
        StringBuffer targetStr = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            if (targetStr.indexOf(s.charAt(i) + "") < 0) {
                targetStr.append(s.charAt(i));
            } else {
                return targetStr.toString().length();
            }
        }
        return targetStr.toString().length();
    }
}
