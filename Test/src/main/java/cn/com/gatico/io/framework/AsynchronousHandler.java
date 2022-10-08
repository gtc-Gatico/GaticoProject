package cn.com.gatico.io.framework;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;

public interface AsynchronousHandler {
    void setAsynchronousServiceContext(AsynchronousServiceContext asynchronousServiceContext,AsynchronousSocketChannel clientChannel);
    void doRead(int result, ByteBuffer byteBuffer);
    void readTimeOut(AsynchronousSocketChannel clientChannel);
    void doWrite(ByteBuffer byteBuffer);
    void writeTimeOut(AsynchronousSocketChannel clientChannel);
    void doFailed(Throwable exc);
}
