package cn.com.gatico.io.framework;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.channels.InterruptedByTimeoutException;

public class TestAsynchronousHandler implements AsynchronousHandler {

    AsynchronousServiceContext asynchronousServiceContext;
    AsynchronousSocketChannel clientChannel;

    public void setAsynchronousServiceContext(AsynchronousServiceContext asynchronousServiceContext, AsynchronousSocketChannel clientChannel) {
        this.asynchronousServiceContext = asynchronousServiceContext;
        this.clientChannel = clientChannel;
    }

    @Override
    public void doRead(int result, ByteBuffer byteBuffer) {
        System.out.println(new String(byteBuffer.array(), 0, result));
    }

    @Override
    public void readTimeOut(AsynchronousSocketChannel clientChannel) {

    }

    @Override
    public void doWrite(ByteBuffer byteBuffer) {
        clientChannel.write(byteBuffer,
                asynchronousServiceContext.getWriteTimeOut(),
                asynchronousServiceContext.getWriteTimeUnit(),
                asynchronousServiceContext,
                new CompletionHandler<Integer, AsynchronousServiceContext>() {
                    @Override
                    public void completed(Integer result,AsynchronousServiceContext attachment) {
                        System.out.println("已发送" + result + "字节。");
                    }

                    @Override
                    public void failed(Throwable exc, AsynchronousServiceContext attachment) {
                        if (exc instanceof InterruptedByTimeoutException) {
                            writeTimeOut(clientChannel);
                        } else {
                            doFailed(exc);
                        }
                    }
                });
    }

    @Override
    public void writeTimeOut(AsynchronousSocketChannel clientChannel) {
        System.out.println("TestAsynchronousHandler[writeTimeOut]");
    }

    @Override
    public void doFailed(Throwable exc) {
        exc.printStackTrace();
    }
}
