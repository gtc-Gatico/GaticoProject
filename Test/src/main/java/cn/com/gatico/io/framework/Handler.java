package cn.com.gatico.io.framework;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class Handler extends TestAsynchronousHandler {

    @Override
    public void doRead(int result, ByteBuffer byteBuffer) {
        System.out.println("Handler[doRead]:"+new String(byteBuffer.array(),0,result));
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ByteBuffer allocate = ByteBuffer.allocate(1024);
        allocate.put("ok".getBytes(StandardCharsets.UTF_8));
        allocate.flip();
        doWrite(allocate);

        new Thread(() -> {
            int i = 0;
            while(true){
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                buffer.put(("ok"+ (++i)).getBytes(StandardCharsets.UTF_8));
                buffer.flip();
                doWrite(buffer);
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void doWrite(ByteBuffer byteBuffer) {
        System.out.println("Handler[doWrite]:"+new String(byteBuffer.array(),0,byteBuffer.limit()));
        super.doWrite(byteBuffer);
    }
}
