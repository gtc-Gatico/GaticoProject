package cn.com.gatico.io.framework;

public class Test {
    public static void main(String[] args) {
        AsynchronousServiceContext asynchronousServiceContext = new AsynchronousServiceContext();
        asynchronousServiceContext.setPort(8000);
        asynchronousServiceContext.setAsynchronousHandler(new Handler());
        asynchronousServiceContext.init();
    }
}
