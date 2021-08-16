
import java.util.Arrays;

public class TestCode {
    public static void main(String[] args) {
        String tag = "if_index=wan,ifName=1,if_type=wan";
        String ifName = tag.substring(tag.indexOf("ifName=") + "ifName=".length(), tag.indexOf(",", tag.indexOf("ifName=")));
        System.out.println(ifName);
        // rhombus(5);
    }

    public static String getChar(int i, int c) {
        char[] arr = new char[c];
        Arrays.fill(arr, ' ');

        int start = (c - i) / 2;
        int end = start + i;
        while (start < end) {
            arr[start] = '*';
            start++;
        }
        return new String(arr);

    }

    public static void rhombus(int n) {
        int count = 0;
        int index = 0;
        String[] arr = new String[n];
        for (int i = 1; i < n; i += 2) {
            if (count > n) {
                break;
            }
            count += i;
            arr[index] = getChar(i, index);
            index++;
        }


        System.out.println("剩余*：" + (n - count));
    }
}
