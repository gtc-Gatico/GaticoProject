package cn.com.gatico.time;

import cn.com.gatico.orm.Test2Entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

public class Test {
    public static void main(String[] args) {

        System.out.println(new SimpleDateFormat("HH:mm").format(new Date(1644487390000L)));
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        new Thread(() -> {
            while(true){
                System.out.println(map.size());
                try {
                    Thread.sleep(500L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() ->  {
            map.put("123", "123");
            System.out.println("add");
            try {
                Thread.sleep(5000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("end");
        }).start();

        /*int portNumber = 8000;
        try {
            DatagramChannel datagramChannel = DatagramChannel.open();

            datagramChannel.configureBlocking(false);
            ByteBuffer allocate = ByteBuffer.allocate(1024);
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNext()){
                String str = scanner.next();
                allocate.put((new Date()+"\n"+str).getBytes());
                allocate.flip();
                datagramChannel.send(allocate,new InetSocketAddress("127.0.0.1",portNumber));
                allocate.clear();
            }
            datagramChannel.close();


        } catch (IOException e) {
            e.printStackTrace();
        }*/

        Test2Entity test2 = new Test2Entity();
        test2.setId(0L);
        test2.setKey("");
        System.out.println(test2);
    }
}
