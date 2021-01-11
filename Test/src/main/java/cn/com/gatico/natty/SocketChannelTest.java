package cn.com.gatico.natty;

import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.*;

public class SocketChannelTest {


    String host;
    int port = 9999;

    public static void main(String[] args) {
        try {
            String name = System.getProperty("os.name").toLowerCase(Locale.KOREAN).trim();
            System.out.println(name);
            new SocketChannelTest().init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    Map<SocketChannel, Protocol> socketChannelProtocolMap = new HashMap<>();

    Map<Long, SocketChannel> userIdSocketChannelMap = new HashMap<>();
    int connectionSize = 0;

    public void init() {
        try {

            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.bind(new InetSocketAddress(port));
            Selector selector = Selector.open();
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            while (true) {
                SocketChannel socketChannel = null;
                try {
                    int select = selector.select();
                    if (select > 0) {
                        Set selectedKeys = selector.selectedKeys();
                        Iterator keyIterator = selectedKeys.iterator();
                        while (keyIterator.hasNext()) {
                            SelectionKey key = (SelectionKey) keyIterator.next();
                            if (key.isAcceptable()) {
                                // a connection was accepted by a ServerSocketChannel.
                                socketChannel = serverSocketChannel.accept();
                                socketChannel.configureBlocking(false);
                                System.out.println("新的连接:" + socketChannel);
                                Protocol p = new Protocol();
                                socketChannel.register(selector, SelectionKey.OP_READ, p);
                            } else if (key.isConnectable()) {
                                // a connection was established with a remote server.
                            } else if (key.isReadable()) {
                                // a channel is ready for reading
                                socketChannel = (SocketChannel) key.channel();
                                Protocol p = (Protocol) key.attachment();
                                if (p == null) {
                                    p = new Protocol();
                                }
                                if (p.type == 0) {
                                    ByteBuffer byteBuffer = ByteBuffer.allocate(1);
                                    byteBuffer.clear();
                                    int res = socketChannel.read(byteBuffer);
                                    byteBuffer.flip();
                                    if (byteBuffer.hasRemaining()) {
                                        byte type = byteBuffer.get();
                                        p.type = type;
                                        if (res > 1) {
                                            byteBuffer = ByteBuffer.allocate(8);
                                            byteBuffer.clear();
                                            res = socketChannel.read(byteBuffer);
                                            byteBuffer.flip();
                                            p.length = byteBuffer.getLong();
                                        }
                                        if (res > 1) {
                                            byteBuffer = ByteBuffer.allocate(Long.valueOf(p.length).intValue());
                                            byteBuffer.clear();
                                            res = socketChannel.read(byteBuffer);
                                            byteBuffer.flip();
                                            p.data = byteBuffer.array();
                                            onRead(p);
                                            p = new Protocol();
                                            key.attach(p);
                                        }
                                    }
                                } else {
                                    if (p.type == ProtocolType.HEART.getType() || p.type == ProtocolType.DATA.getType()) {
                                        ByteBuffer byteBuffer = ByteBuffer.allocate(8);
                                        byteBuffer.clear();
                                        int res = socketChannel.read(byteBuffer);
                                        byteBuffer.flip();
                                        p.length = byteBuffer.getLong();
                                        if (res > 1) {
                                            byteBuffer = ByteBuffer.allocate(Long.valueOf(p.length).intValue());
                                            byteBuffer.clear();
                                            res = socketChannel.read(byteBuffer);
                                            byteBuffer.flip();
                                            p.data = byteBuffer.array();
                                            if (p.type == ProtocolType.HEART.getType()) {
                                                onHeart(p, socketChannel);
                                            } else if (p.type == ProtocolType.DATA.getType()) {
                                                onRead(p);
                                            }
                                            p = new Protocol();
                                            key.attach(p);
                                        }
                                    } else if (p.data.length < p.length) {
                                        ByteBuffer byteBuffer = ByteBuffer.allocate(Long.valueOf(p.length - p.data.length).intValue());
                                        socketChannel.read(byteBuffer);
                                        byteBuffer.flip();
                                        p.data = byteBuffer.array();
                                        onRead(p);
                                        p = new Protocol();
                                        key.attach(p);
                                    }
                                }
                            } else if (key.isWritable()) {
                                // a channel is ready for writing
                            }
                            keyIterator.remove();
                        }
                    }
                } catch (IOException e) {
                    if (socketChannel != null) {
                        socketChannel.close();
                        socketChannelProtocolMap.remove(socketChannel);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onHeart(Protocol p, SocketChannel socketChannel) {
        System.out.println(p.type);
        System.out.println(p.length);
        if (p.type == ProtocolType.HEART.getType()) {
            ByteBuffer byteBuffer = ByteBuffer.wrap(p.data);
            Long userId = byteBuffer.getLong();
            System.out.println(userId);
            userIdSocketChannelMap.put(userId, socketChannel);
        }
    }

    public void onRead(Protocol p) throws UnsupportedEncodingException {
        System.out.println(p.type);
        System.out.println(p.length);
        if (p.type == ProtocolType.HEART.getType()) {
            ByteBuffer byteBuffer = ByteBuffer.wrap(p.data);
            Long userId = byteBuffer.getLong();
            System.out.println(userId);
        } else if (p.type == ProtocolType.DATA.getType()) {
            String s = new String(p.data);
            JSONObject jsonObject = JSONObject.parseObject(s);
            System.out.println(jsonObject.toJSONString());
            Long toUserId = jsonObject.getLong("toUserId");
            SocketChannel socketChannel = userIdSocketChannelMap.get(toUserId);
            ByteBuffer byteBuffer = ByteBuffer.wrap(jsonObject.toJSONString().getBytes("UTF8"));
            Protocol p2 = new Protocol();
            p2.data = byteBuffer.array();
            try {
                int write = socketChannel.write(p2.toBytes());
                System.out.println(write);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void onWriter(SocketChannel socketChannel) {
        System.out.println("发送");
    }
}

class Protocol {
    byte type;
    long length;
    byte[] data;

    public ByteBuffer toBytes() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(this.data.length + 9);
        byteBuffer.put((byte) 2);
        byteBuffer.putLong(this.data.length);
        byteBuffer.put(this.data);
        byteBuffer.flip();
        return byteBuffer;
    }
}

enum ProtocolType {
    HEART((byte) 1, "心跳"),
    DATA((byte) 2, "数据"),

    ;
    private byte type;
    private String value;

    ProtocolType(byte type, String value) {
        this.type = type;
        this.value = value;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
