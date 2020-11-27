package cn.com.gatico.TestThread;

public class TestTimer extends Thread {
    public static void main(String[] args) {
//        System.out.println(UUID.randomUUID().toString());
//
//        int a = 0;
//        int b = 1;
//        int c = a++ + ++b;
//        System.out.format("%d,%d,%d",a,b,c);
        new TestTimer().start();
    }

    @Override
    public void run() {
        System.out.println(1);
    }

    public static int b(int a) {
        if (a > 1) {
            return a * b(--a);
        }
        return 1;
    }


}
