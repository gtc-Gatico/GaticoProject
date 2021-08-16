package cn.com.gatico;

public class JNIDemo {
    //定义一个方法，该方法在C中实现
    public native void testHello();

    public native int add(int a, int b);

    public native int plus(int a, int b);

    static {
        System.loadLibrary("TestJni");
    }

    public static void main(String[] args) {
        //加载C文件
        System.loadLibrary("TestJni");
        JNIDemo jniDemo = new JNIDemo();
        jniDemo.testHello();
    }
}
