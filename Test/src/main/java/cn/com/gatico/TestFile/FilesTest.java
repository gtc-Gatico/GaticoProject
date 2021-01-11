package cn.com.gatico.TestFile;

import cn.com.gatico.entity.ABCDEFEntity;

import java.io.*;
import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;

/**
 * @author Gatico
 * @version 1.0
 * @date 2020/1/7 13:37
 */
public class FilesTest {
    public static void main(String[] args) throws Exception {
        //4,714,162,176字节 4.38GB
        File file = new File(new String("F:\\Win10_1803_Chinese(Simplified)_x64.iso"));
        File file2 = new File(new String("F:\\Win10_1803_Chinese(Simplified)_x64.iso1"));
        try (
                InputStream in = new FileInputStream(file);
                OutputStream out = new FileOutputStream(file2)
        ) {
            long b = System.currentTimeMillis();
            byte[] arr = new byte[Math.min(1 << 23, in.available())];//10240    8388608
            int index = 0;
            while ((index = in.read(arr)) > 0) {
                out.write(arr, 0, index);
                out.flush();
            }
            System.out.println(System.currentTimeMillis() - b); //77409   28566
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (1 == 1) {
            return;
        }
        //管道copy文件
        try (
                FileInputStream fis = new FileInputStream(file);
                FileOutputStream fos = new FileOutputStream(file2);
                FileChannel in = fis.getChannel();
                FileChannel out = fos.getChannel();
        ) {
            long b = System.currentTimeMillis();
            out.transferFrom(in, 0, in.size());
            System.out.println(System.currentTimeMillis() - b); //26184  23608
        } catch (Exception e) {
            e.printStackTrace();
        }


        if (1 == 1) {
            return;
        }
        file.delete();
        if (file.createNewFile()) {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(file));
            ABCDEFEntity abcdefEntity = new ABCDEFEntity();
            abcdefEntity.setBoxId(1L);
            abcdefEntity.setAction("1");
            abcdefEntity.setDstCidr("1");
            abcdefEntity.setPriority(1L);
            abcdefEntity.setProtocol(1L);
            abcdefEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));
            os.write(1);
            os.flush();
            os.close();
            return;
        }

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
