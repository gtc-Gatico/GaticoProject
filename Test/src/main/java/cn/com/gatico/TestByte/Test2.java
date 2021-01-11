package cn.com.gatico.TestByte;


public class Test2 {
    public static void main(String[] args) {
        String gao = "高天赐";
        for (int i = 0; i < gao.length(); i++) {
            System.out.println(Integer.toBinaryString(gao.charAt(i)));
        }

    }
}
