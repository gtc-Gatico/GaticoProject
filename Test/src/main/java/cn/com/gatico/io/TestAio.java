package cn.com.gatico.io;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.Future;

public class TestAio {
    public static void main(String[] args) throws Exception {
        readToFile();
    }

    public static void readToFile() throws Exception {
        Path file = Paths.get("C:\\Users\\7x-networks\\Downloads\\7xnetworks 门店.rules");
        AsynchronousFileChannel channel = AsynchronousFileChannel.open(file);

        ByteBuffer buffer = ByteBuffer.allocate(100_000);
        Future<Integer> result = channel.read(buffer, 0);

        while (!result.isDone()) {
            byte[] bytes = buffer.array();
            System.out.println(new String(bytes,0,result.get()));
        }
        Integer bytesRead = result.get();
        System.out.println("Bytes read [" + bytesRead + "]");
    }


    public static void WriteToFile() throws Exception {
        AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(
                Paths.get("/asynchronous.txt"), StandardOpenOption.READ,
                StandardOpenOption.WRITE, StandardOpenOption.CREATE);
        CompletionHandler<Integer, Object> handler = new CompletionHandler<Integer, Object>() {

            @Override
            public void completed(Integer result, Object attachment) {
                System.out.println("Attachment: " + attachment + " " + result
                        + " bytes written");
                System.out.println("CompletionHandler Thread ID: "
                        + Thread.currentThread().getId());
            }

            @Override
            public void failed(Throwable e, Object attachment) {
                System.err.println("Attachment: " + attachment + " failed with:");
                e.printStackTrace();
            }
        };

        System.out.println("Main Thread ID: " + Thread.currentThread().getId());
        fileChannel.write(ByteBuffer.wrap("Sample".getBytes()), 0, "First Write",
                handler);
        fileChannel.write(ByteBuffer.wrap("Box".getBytes()), 0, "Second Write",
                handler);


    }
}
