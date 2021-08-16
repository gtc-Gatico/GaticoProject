package cn.com.gatico.泛型;


public class 测试泛型 {

    public static <T> Test1<T> add(T str) {
        return new Test1(str);
    }

    public static void main(String[] args) {
        System.out.println(测试泛型.add("1234").s);
    }
}
