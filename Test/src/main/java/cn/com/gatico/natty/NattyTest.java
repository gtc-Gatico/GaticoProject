package cn.com.gatico.natty;

import org.farng.mp3.MP3File;
import org.farng.mp3.TagException;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;

/**
 * @author Gatico
 * @version 1.0
 * @date 2020/1/9 17:40
 */
public class NattyTest {
    public static void main(String[] args) {
        try {
            /*
            char Header[3];    //必须为“ID3”否则认为标签不存在
            char Ver;         //版本号ID3V2.3 就记录3
            char Revision;     //副版本号此版本记录为0
            char Flag;        //标志字节，只使用高三位，其它位为0
            char Size[4];      //标签大小
             */
            FileChannel fileChannel = FileChannel.open(Paths.get("F:\\库\\音乐\\G.E.M.邓紫棋 - 泡沫.mp3"));
            ByteBuffer byteBuffer = ByteBuffer.allocate((int) fileChannel.size());
            byteBuffer.clear();
            fileChannel.read(byteBuffer);
            fileChannel.close();

            byteBuffer.flip();
            byte[] header = new byte[3];
            for (int i = 0; i < header.length; i++) {
                header[i] = byteBuffer.get();
            }
            System.out.println(new String(header));
            File file = new File("F:\\库\\音乐\\G.E.M.邓紫棋 - 泡沫.mp3");
            MP3File mp3File = new MP3File(file);
            System.out.println(mp3File.getID3v1Tag().toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TagException e) {
            e.printStackTrace();
        }
    }
}
