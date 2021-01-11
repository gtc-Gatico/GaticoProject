import java.util.Random;
import java.util.concurrent.Semaphore;

public class TestThread {
    //停车场同时容纳的车辆10
    private static Semaphore semaphore = new Semaphore(10);

    public static void main1(String[] args) {

        //模拟100辆车进入停车场
        for (int i = 0; i < 100; i++) {

            Thread thread = new Thread(new Runnable() {
                public void run() {
                    try {
                        System.out.println("====" + Thread.currentThread().getName() + "来到停车场");
                        if (semaphore.availablePermits() == 0) {
                            System.out.println("车位不足，请耐心等待");
                        }
                        semaphore.acquire();//获取令牌尝试进入停车场
                        System.out.println(Thread.currentThread().getName() + "成功进入停车场");
                        Thread.sleep(new Random().nextInt(10000));//模拟车辆在停车场停留的时间
                        System.out.println(Thread.currentThread().getName() + "驶出停车场");
                        semaphore.release();//释放令牌，腾出停车场车位
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, (i + 1) + "号车");

            thread.start();

        }

    }

    public static void main(String[] args) throws InterruptedException {
        StringBuffer stringBuffer = new StringBuffer();
        Semaphore sp1 = new Semaphore(2);
        Semaphore sp2 = new Semaphore(2);

        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                try {
                    sp1.acquire(1);
                    stringBuffer.append(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                sp2.release(1);
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 50; i++) {
                try {
                    sp2.acquire(2);
                    stringBuffer.append(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                sp1.release(2);
            }
        }).start();
        while(stringBuffer.length() < 150){
            System.out.println(stringBuffer.length());
            Thread.sleep(100);
        }
        String str = stringBuffer.toString();
        for (int i = 0; i < str.length(); i++) {
            if(i%3==0){
                System.out.println(str);
            }
        }
    }

}
