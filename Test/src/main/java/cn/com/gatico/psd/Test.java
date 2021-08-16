package cn.com.gatico.psd;

import cn.com.gatico.utils.ByteUtils;

import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Test {
    private static Psd psd = new Psd();

    static class Psd {
        public byte[] signature = new byte[4];
        public byte[] version = new byte[2];
        public byte[] reserved = new byte[6];
        public byte[] channels = new byte[2];
        public byte[] height = new byte[4];
        public byte[] width = new byte[4];
        public byte[] depth = new byte[2];
        public byte[] mode = new byte[2];
        public byte[] colorLength = new byte[4];
        public byte[] colorData;
        public byte[] imageResourceLength = new byte[4];
        public byte[] imageResourceData ;
    }

    public static void main(String[] args) throws Exception {
        String path = "F:\\niuhi\\小牛要飞\\鼠标1.psd";
        Path path1 = Paths.get(path);
        FileChannel open = FileChannel.open(path1);
        ByteBuffer byteBuffer = ByteBuffer.allocate((int) open.size());
        open.read(byteBuffer);
        byteBuffer.flip();
        byteBuffer.get(psd.signature);
        byteBuffer.get(psd.version);
        byteBuffer.get(psd.reserved);
        byteBuffer.get(psd.channels);
        byteBuffer.get(psd.height);
        byteBuffer.get(psd.width);
        byteBuffer.get(psd.depth);
        byteBuffer.get(psd.mode);
        byteBuffer.get(psd.colorLength);
        psd.colorData = new byte[ByteUtils.byteToInt(psd.colorLength)];
        byteBuffer.get(psd.colorData);
        byteBuffer.get(psd.imageResourceLength);
        psd.imageResourceData = new byte[ByteUtils.byteToInt(psd.imageResourceLength)];
        byteBuffer.get(psd.imageResourceData);
        System.out.println(psd);
    }
}
