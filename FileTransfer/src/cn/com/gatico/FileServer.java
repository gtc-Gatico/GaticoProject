package cn.com.gatico;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.nio.charset.Charset;

public class FileServer extends Thread {
    public Socket serviceSocket;
    String tga = Thread.currentThread().getName() + "_" + this.getName();

    public FileServer(Socket socket) {
        this.serviceSocket = socket;
    }

    @Override
    public void run() {
        try {
            Protocol protocol = new Protocol();
            byte[] temp = null;
            InputStream is = serviceSocket.getInputStream();
            int size = 0;
            while (!serviceSocket.isClosed()) {

                switch (protocol.getProtocolStatus()) {
                    case protocolTitle:
                        temp = new byte[2];
                        is.read(temp);
                        String title = new String(temp);
                        if (title.equals("FT")) {
                            Log.i(tga, "----------开始----------");
                            temp = new byte[2];
                            is.read(temp);
                            protocol.setProtocolTitle(Util.byteToInt(new byte[]{70, 84, temp[0], temp[1]}));
                            protocol.setProtocolStatus(ProtocolStatus.type);
                        } else {
                            Log.i(tga, "----------结束----------");
                            serviceSocket.close();
                        }
                        break;
                    case type:
                        temp = new byte[1];
                        is.read(temp);
                        protocol.setType(temp[0]);
                        if (protocol.getType() == 1) {
                            protocol.setProtocolStatus(ProtocolStatus.nameLength);
                        } else if (protocol.getType() == 2) {
                            protocol.setProtocolStatus(ProtocolStatus.splitByte);
                        }
                        Log.i(tga, "传输类型：" + (protocol.getType()));
                        break;
                    case splitByte:
                        temp = new byte[4];
                        size = is.read(temp);
                        if (size < 0) {
                            Log.i(tga, "----------结束----------");
                            serviceSocket.close();
                        }
                        int tmp = Util.byteToInt(temp);
                        if (protocol.getSplitByte() == 0) {
                            protocol.setSplitByte(tmp);
                            Log.i(tga, "随机分割字符串：" + protocol.getSplitByte());
                        }
                        if (tmp == protocol.getSplitByte()) {
                            protocol.setSplitByte(Util.byteToInt(temp));
                            protocol.setProtocolStatus(ProtocolStatus.nameLength);
                        } else if (tmp == protocol.getProtocolTitle()) {
                            protocol = new Protocol();
                            protocol.setProtocolTitle(tmp);
                            protocol.setProtocolStatus(ProtocolStatus.type);
                        }
                        break;
                    case nameLength:
                        temp = new byte[1];
                        is.read(temp);
                        protocol.setNameLength(temp[0]);
                        protocol.setProtocolStatus(ProtocolStatus.fileName);
                        break;
                    case fileName:
                        temp = new byte[protocol.getNameLength()];
                        is.read(temp);
                        protocol.setFileName(temp);
                        protocol.setProtocolStatus(ProtocolStatus.fileLength);
                        Log.i(tga, "获取的文件名称:" + new String(protocol.getFileName(), "UTF8"));
                        break;
                    case fileLength:
                        temp = new byte[8];
                        is.read(temp);
                        protocol.setFileLength(Util.byteToLong(temp));
                        protocol.setProtocolStatus(ProtocolStatus.fileContext);//8388608
                        protocol.setTempSize(protocol.getFileLength() >  1 << 23 ?  1 << 23 : (int) protocol.getFileLength());
                        Log.i(tga, "获取的文件长度:" + protocol.getFileLength());
                        break;
                    case fileContext:
                        File file = new File(FileServerStart.path + File.separator + new String(protocol.getFileName(), "UTF8"));
                        FileOutputStream fileOutputStream = new FileOutputStream(file);
                        temp = new byte[protocol.getTempSize()];

                        size = 0;
                        int length = 0;
                        while ( size < protocol.getFileLength()) {
                            length = is.read(temp);
                            fileOutputStream.write(temp, 0, length);
                            size += length;
                            fileOutputStream.flush();
                        }
                        fileOutputStream.flush();
                        fileOutputStream.close();
                        Log.i(tga, "文件接收已完成：" + file.getAbsolutePath());
                        if (protocol.getType() == 1) {
                            protocol = new Protocol();
                        } else if (protocol.getType() == 2) {
                            protocol.setProtocolStatus(ProtocolStatus.splitByte);
                        }
                        break;
                    default:
                        serviceSocket.close();
                        break;
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
            try {
                serviceSocket.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

}
