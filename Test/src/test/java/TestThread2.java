public class TestThread2 extends Thread {

    @Override
    public synchronized void start() {
        System.out.println("B");
    }

    @Override
    public void run() {
        System.out.println("C");
    }
}
