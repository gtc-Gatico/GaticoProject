package cn.com.gatico.io.framework;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.channels.InterruptedByTimeoutException;
import java.util.concurrent.TimeUnit;

public class AsynchronousServiceContext {
    private AsynchronousServerSocketChannel serverChannel;
    private AsynchronousHandler asynchronousHandler;
    private TimeUnit readTimeUnit = TimeUnit.SECONDS;
    private TimeUnit writeTimeUnit = TimeUnit.SECONDS;
    private long readTimeOut = 30;
    private long writeTimeOut = 30;
    private int port;

    public void init() {
        try {
            // 初始化 AsyncronousServersocketChannel
            serverChannel = AsynchronousServerSocketChannel.open();
            // 监听端口
            serverChannel.bind(new InetSocketAddress(getPort()));
            // 监听客户端连接,但在AIO，每次accept只能接收一个client，所以需要
            // 在处理逻辑种再次调用accept用于开启下一次的监听
            // 类似于链式调用
            serverChannel.accept(this, new CompletionHandler<AsynchronousSocketChannel, AsynchronousServiceContext>() {
                public void completed(AsynchronousSocketChannel result, AsynchronousServiceContext attachment) {
                    asynchronousHandler.setAsynchronousServiceContext(attachment,result);
                    // 处理下一次的client连接。类似链式调用
                    attachment.getServerChannel().accept(attachment, this);
                    // 执行业务逻辑
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    result.read(byteBuffer, getReadTimeOut(), getReadTimeUnit(), null, new CompletionHandler<Integer, AsynchronousServiceContext>() {
                        @Override
                        public void completed(Integer length, AsynchronousServiceContext attachment) {
                            asynchronousHandler.doRead(length, byteBuffer);
                        }

                        @Override
                        public void failed(Throwable exc,AsynchronousServiceContext attachment) {
                            if (exc instanceof InterruptedByTimeoutException) {
                                asynchronousHandler.readTimeOut(result);
                            } else {
                                asynchronousHandler.doFailed(exc);
                            }
                        }
                    });
                }

                public void failed(Throwable exc, AsynchronousServiceContext attachment) {
                    asynchronousHandler.doFailed(exc);
                }
            });

            try {
                // 阻塞程序，防止被GC回收
                TimeUnit.SECONDS.sleep(Long.MAX_VALUE);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public AsynchronousServerSocketChannel getServerChannel() {
        return serverChannel;
    }

    public void setServerChannel(AsynchronousServerSocketChannel serverChannel) {
        this.serverChannel = serverChannel;
    }

    public AsynchronousHandler getAsynchronousHandler() {
        return asynchronousHandler;
    }

    public void setAsynchronousHandler(AsynchronousHandler asynchronousHandler) {
        this.asynchronousHandler = asynchronousHandler;
    }

    public TimeUnit getReadTimeUnit() {
        return readTimeUnit;
    }

    public void setReadTimeUnit(TimeUnit readTimeUnit) {
        this.readTimeUnit = readTimeUnit;
    }

    public TimeUnit getWriteTimeUnit() {
        return writeTimeUnit;
    }

    public void setWriteTimeUnit(TimeUnit writeTimeUnit) {
        this.writeTimeUnit = writeTimeUnit;
    }

    public long getReadTimeOut() {
        return readTimeOut;
    }

    public void setReadTimeOut(long readTimeOut) {
        this.readTimeOut = readTimeOut;
    }

    public long getWriteTimeOut() {
        return writeTimeOut;
    }

    public void setWriteTimeOut(long writeTimeOut) {
        this.writeTimeOut = writeTimeOut;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    /*class AsynchronousServiceHandler implements CompletionHandler<AsynchronousSocketChannel, AsynchronousServiceContext> {
        @Override
        public void completed(AsynchronousSocketChannel result, AsynchronousServiceContext attachment) {
            // 处理下一次的client连接。类似链式调用
            attachment.getServerChannel().accept(attachment, this);
            // 执行业务逻辑
            asynchronousHandler.doRead(result);
            asynchronousHandler.doWrite(result);
        }

        *//**
     * 读取client发送的消息打印到控制台
     * <p>
     * AIO中OS已经帮助我们完成了read的IO操作，所以我们直接拿到了读取的结果
     *
     * @param clientChannel 服务端于客户端通信的 channel
     *//*
        private void doRead(AsynchronousSocketChannel clientChannel) {
            ByteBuffer buffer = ByteBuffer.allocate(1024);

            // 从client读取数据,在我们调用clientChannel.read()之前OS，已经帮我们完成了IO操作
            // 我们只需要用一个缓冲区来存放读取的内容即可
            clientChannel.read(
                    buffer,   // 用于数据中转缓冲区
                    buffer,   // 用于存储client发送的数据的缓冲区
                    new CompletionHandler<Integer, ByteBuffer>() {
                        @Override
                        public void completed(Integer result, ByteBuffer attachment) {
                            System.out.println("receive client data length：" + attachment.capacity() + " byte");
                            attachment.flip(); // 移动 limit位置
                            // 读取client发送的数据
                            System.out.println("from client : " + new String(attachment.array(), 0, result, StandardCharsets.UTF_8));

                            // 向client写入数据
                            doWrite(clientChannel);
                        }

                        @Override
                        public void failed(Throwable exc, ByteBuffer attachment) {
                        }
                    }
            );
        }

        private void doWrite(AsynchronousSocketChannel clientChannel) {

            // 向client发送数据，clientChannel.write()是一个异步调用，该方法执行后会通知
            // OS执行写的IO操作，会立即返回
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            Scanner s = new Scanner(System.in);
            String line = s.nextLine();
            buffer.put(line.getBytes(StandardCharsets.UTF_8));
            buffer.flip();
            clientChannel.write(buffer);
            // clientChannel.write(buffer).get(); // 会进行阻塞，直到OS写操作完成
        }

        *//**
     * 异常处理逻辑
     *
     * @param exc
     * @param attachment
     *//*
        @Override
        public void failed(Throwable exc, AsynchronousServiceContext attachment) {
            exc.printStackTrace();
        }
    }*/
}
