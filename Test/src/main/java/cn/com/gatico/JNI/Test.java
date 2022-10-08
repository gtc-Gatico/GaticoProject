package cn.com.gatico.JNI;


public class Test {
    public native void testHello();

    public native int mathAdd(int n1, int n2);

    public native int mathSub(int n1, int n2);

    public native int testMul(int n1, int n2);

    public native double testDiv(double n1, double n2);

    public native Res getRes(Req req);

    static {
//        System.out.println(System.getProperty("java.library.path"));
        System.loadLibrary("Test/src/main/java/TestJni");
    }

    public static void main(String[] args) {
        Test jniDemo = new Test();
        System.out.println(jniDemo.mathAdd(1, 2));
        System.out.println(jniDemo.mathSub(1, 2));
        System.out.println(jniDemo.testMul(1, 2));
        System.out.println(jniDemo.testDiv(1, 2));
        jniDemo.testHello();
        Req req = new Req();
        req.setBody("12345");
        Res res = jniDemo.getRes(req);
        System.out.println(res.getCode());
        System.out.println(res.getMsg());
    }

}
