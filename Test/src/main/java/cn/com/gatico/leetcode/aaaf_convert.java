package cn.com.gatico.leetcode;

/**
 * @author Gatico
 * @version 1.0
 * @date 2019/10/28 12:05
 */
public class aaaf_convert {
    ///////////////////////////////////////////////////////////////////////////
    // 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
    //
    //比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
    //
    //L   C   I   R
    //E T O E S I I G
    //E   D   H   N
    //之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。
    //
    //请你实现这个将字符串进行指定行数变换的函数：
    //
    //string convert(string s, int numRows);
    //示例 1:
    //
    //输入: s = "LEETCODEISHIRING", numRows = 3
    //输出: "LCIRETOESIIGEDHN"
    //解释:
    //L   C   I   R
    //E T O E S I I G
    //E   D   H   N
    //示例 2:
    //
    //输入: s = "LEETCODEISHIRING", numRows = 4
    //输出: "LDREOEIIECIHNTSG"
    //解释:
    //
    //L     D     R
    //E   O E   I I
    //E C   I H   N
    //T     S     G
    //
    //输入: s = "LEETCODEISHIRING", numRows = 5
    //输出: "LDREOEIIECIHNTSG"
    //解释:
    //
    //L       I       G
    //E     E S     N
    //E   D   H   R
    //T O     I I
    //C       R
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/zigzag-conversion
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    ///////////////////////////////////////////////////////////////////////////
    public static void main(String[] args) {
        System.out.println(convert("", 3));
    }

    public static String convert(String s, int numRows) {
        char[] chars = s.toCharArray();
        String[] arr = new String[numRows];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = "";
        }
        for (int i = 0; i < chars.length; ) {
            for (int j = 0; j < numRows; j++) {
                if (i == 0) {
                    arr[j] = "";
                }
                if (i >= chars.length) {
                    break;
                }
                arr[j] += chars[i];
                i++;
            }
            for (int j = numRows - 2; j > 0; j--) {
                if (i >= chars.length) {
                    break;
                }
                arr[j] += chars[i];
                i++;
            }
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < arr.length; i++) {
            stringBuffer.append(arr[i]);
        }
        return stringBuffer.toString();
    }
}
