package cn.com.gatico;

import javax.net.SocketFactory;
import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

public class FileClient {
    public Socket socket;
    public String address;
    public int port;
    public OutputStream os = null;
    String tga = Thread.currentThread().getName() + "_" + FileClient.class.getSimpleName();

    public FileClient() {

    }

    public FileClient(String address, int port) {
        try {
            this.address = address;
            this.port = port;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void init() throws IOException {
        this.socket = SocketFactory.getDefault().createSocket();
        this.socket.connect(new InetSocketAddress(address, port));
        os = socket.getOutputStream();
    }

    /**
     * 上传文件
     *
     * @param file
     * @return
     */
    public boolean uploadFile(File file) {
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
            try {
                socket.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            return false;
        }
        long startTime = System.currentTimeMillis();
        Log.i(tga, "----------开始----------");
        Protocol protocol = new Protocol();
        byte[] arr = new byte[]{(byte) 70, (byte) 84, (byte) 0, (byte) 1};
        protocol.setProtocolTitle(Util.byteToInt(arr));
        if (file.isFile()) {
            protocol.setType((byte) 1);
            byte[] fileName = null;
            try {
                fileName = file.getName().getBytes("UTF8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            protocol.setNameLength((byte) fileName.length);
            protocol.setFileName(fileName);
            protocol.setFileLength(file.length());
            try {
                os.write(Util.intToByte(protocol.getProtocolTitle()));
                os.write(protocol.getType());
                os.write(protocol.getNameLength());
                os.write(protocol.getFileName());
                os.write(Util.longToByte(protocol.getFileLength()));
                os.flush();
                Log.i(tga, "文件名：" + new String(protocol.getFileName(), "UTF8"));
                Log.i(tga, "文件大小：" + protocol.getFileLength());
                FileInputStream fileInputStream = new FileInputStream(file);//8388608
                int length = 0;
                byte temp[] = new byte[file.length() > 1 << 23 ? 1 << 23 : (int) file.length()];
                while ((length = fileInputStream.read(temp, 0, temp.length)) > 0) {
                    os.write(temp, 0, length);
                    os.flush();
                }
                fileInputStream.close();
                Log.i(tga, "文件" + file.getName() + "已发送完成");
            } catch (IOException e) {
                e.printStackTrace();
                try {
                    socket.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                return false;
            }
        } else if (file.isDirectory()) {
            protocol.setType((byte) 2);
            Double d = Math.random() * 1000000000;
            File[] files = file.listFiles();
            protocol.setSplitByte(d.intValue());
            System.out.println(protocol.getSplitByte());
            try {
                os.write(Util.intToByte(protocol.getProtocolTitle()));
                os.write(protocol.getType());
            } catch (IOException e) {
                e.printStackTrace();
                try {
                    socket.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                return false;
            }

            for (int i = 0; i < files.length; i++) {
                File tmp = files[i];
                if (tmp.isDirectory()) {
                    continue;
                }
                try {
                    os.write(Util.intToByte(protocol.getSplitByte()));
                    os.write((byte) tmp.getName().getBytes().length);
                    os.write(tmp.getName().getBytes());
                    os.write(Util.longToByte(tmp.length()));
                    os.flush();
                    Log.i(tga, "文件名：" + new String(tmp.getName().getBytes(), "UTF8"));
                    Log.i(tga, "文件大小：" + tmp.length());
                    FileInputStream fileInputStream = new FileInputStream(tmp);
                    byte[] temp = new byte[tmp.length() > 1 << 23 ? 1 << 23 : (int) tmp.length()];
                    while (fileInputStream.read(temp) != -1) {
                        os.write(temp);
                        os.flush();
                    }
                    fileInputStream.close();
                    Log.i(tga, "文件" + tmp.getName() + "已发送完成");
                } catch (IOException e) {
                    e.printStackTrace();
                    try {
                        socket.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    return false;
                }
            }
        }
        Log.i(tga, "发送完成：用时" + (System.currentTimeMillis() - startTime) + "ms");
        try {
            socket.close();
            Log.i(tga, "关闭连接");
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        Log.i(tga, "----------结束----------");
        return true;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
