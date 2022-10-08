package cn.com.gatico;

public class TestInit {
    private final static TestInit INSTANCE = new TestInit();
    public static int i = 0;

    private TestInit() {
        i++;
        System.out.println("TestInit=" + i);
    }

    public static TestInit getInstance() {
        i++;
        System.out.println("getInstance=" + i);
        return INSTANCE;
    }

    public static void main(String[] args) {
        getInstance();
    }
}
