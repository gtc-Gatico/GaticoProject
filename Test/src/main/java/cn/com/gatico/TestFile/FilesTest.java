package cn.com.gatico.TestFile;

import java.io.ByteArrayOutputStream;
import java.io.RandomAccessFile;
import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Gatico
 * @version 1.0
 * @date 2020/1/7 13:37
 */
public class FilesTest {
    public static void main(String[] args) throws Exception {
        try {
            RandomAccessFile aFile = new RandomAccessFile("F:\\var\\log_0.log", "rw");
            FileChannel inChannel = aFile.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(48);
            int flag = inChannel.read(byteBuffer);
            ByteArrayOutputStream byteArrayInputStream = new ByteArrayOutputStream();
            while (flag != -1) {
                //读模式
                byteBuffer.flip();
                if (byteBuffer.hasRemaining()) {
                    byteArrayInputStream.write(byteBuffer.get());
                }
                byteBuffer.clear(); //清空buffer，准备再次写入
                flag = inChannel.read(byteBuffer);
            }
            System.out.println(new String(byteArrayInputStream.toByteArray()));
            URI uri = new URI("F:\\var\\log_0.log");
            Path paths = Paths.get(uri);
            System.out.println(Files.exists(paths));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
