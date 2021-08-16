package cn.com.gatico.JNI;

public class Test {
    //定义一个方法，该方法在C中实现
    public native void testHello();
    static{
        System.loadLibrary("TestJni");
    }

    public static void main(String[] args) {
        //加载C文件
        Test jniDemo = new Test();
        jniDemo.testHello();
    }

}
