package cn.com.gatico;

import java.io.*;
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
            while (!serviceSocket.isClosed()) {

                switch (protocol.getProtocolStatus()) {
                    case protocolTitle:
                        temp = new byte[2];
                        is.read(temp);
                        String title = new String(temp);
                        if (title.equals("FT")) {
                            temp = new byte[2];
                            is.read(temp);
                            protocol.setProtocolTitle(byteToInt(new byte[]{70, 84, temp[0], temp[1]}));
                            protocol.setProtocolStatus(ProtocolStatus.type);
                        } else {
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
                        break;
                    case splitByte:
                        temp = new byte[4];
                        is.read(temp);
                        int tmp = byteToInt(temp);
                        if (protocol.getSplitByte() == 0) {
                            protocol.setSplitByte(tmp);
                        }
                        if (tmp == protocol.getSplitByte()) {
                            protocol.setSplitByte(byteToInt(temp));
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
                        break;
                    case fileLength:
                        temp = new byte[8];
                        is.read(temp);
                        protocol.setFileLength(Util.byteToLong(temp));
                        protocol.setProtocolStatus(ProtocolStatus.fileContext);//8388608
                        protocol.setTempSize(protocol.getFileLength() > 8388608 ? 8388608 : (int) protocol.getFileLength());
                        break;
                    case fileContext:
                        File file = new File(FileServerStart.path + File.separator + new String(protocol.getFileName(), Charset.forName("UTF8")));
                        FileOutputStream fileOutputStream = new FileOutputStream(file);
                        temp = new byte[protocol.getTempSize()];

                        int size = 0;
                        int length = 0;
                        while ((length = is.read(temp, 0, temp.length)) > 0 && size <= protocol.getFileLength()) {
                            fileOutputStream.write(temp, 0, length);
                            size += length;
                            fileOutputStream.flush();
                        }
                        fileOutputStream.close();
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

    /*
     * 获取数据2进制数据
     */
    public byte[] getData() {
        byte[] arr;
        InputStream is = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            is = serviceSocket.getInputStream();
            int i = -1;
            while ((i = is.read()) > -1) {
                bos.write(i);
            }
            bos.flush();
        } catch (Exception e) {
            Log.e(tga, e);
        }
        arr = bos.toByteArray();
        int fileindex = Util.byteToInt(arr);
        Log.i(tga, "----------开始----------");
        Log.i(tga, "获取的文件长度:" + fileindex);
        byte[] fnarr = new byte[fileindex];
        for (int i = 0; i < fileindex; i++) {
            fnarr[i] = arr[i + 4];
        }
        byte[] namearr = new byte[arr.length - fileindex - 4];
        for (int i = 0; i < namearr.length; i++) {
            namearr[i] = arr[i + 4 + fileindex];
        }
        String filename = new String(namearr);
        String filePath = FileServerStart.path + "\\" + filename;
        Log.i(tga, filePath);
        try {
            byteToFile(fnarr, filePath);
            is.close();
            serviceSocket.close();
        } catch (Exception e) {
            Log.e(tga, e);
        }

        Log.i(tga, "收到的文件大小：" + arr.length);
        Log.i(tga, "----------结束----------");
        return arr;
    }

    /**
     * 获取数据转字符串
     *
     * @return
     */
    public String getDataToString() {
        return new String(this.getData());
    }

    /**
     * 字节数组转成int 4位
     *
     * @param b
     * @return
     */
    public static int byteToInt(byte[] b) {
        int value = 0;
        for (int i = 0; i < 4; i++) {
            int shift = (4 - 1 - i) * 8;
            value += (b[i] & 0x000000FF) << shift;
        }
        return value;
    }

    /**
     * 字节数组转成int 4位
     *
     * @param b
     * @return
     */
    public static byte[] intToByte(int b) {
        return new byte[]{(byte) (b & 0xff), (byte) (b >> 8 & 0xff)};
    }


    // 读取文件名
    @SuppressWarnings("unused")
    private String getFileName(InputStream is) throws IOException {
        int name_len = readInteger(is);
        byte[] result = new byte[name_len];
        is.read(result);
        return new String(result);
    }

    // 读取一个数字
    private int readInteger(InputStream is) throws IOException {
        byte[] bytes = new byte[4];
        is.read(bytes);
        return byteToInt(bytes);
    }

    // byte数组到图片
    public void byteToFile(byte[] data, String path) {
        if (data.length < 3 || path.equals(""))
            return;
        try {
            FileOutputStream Output = new FileOutputStream(new File(path));
            Output.write(data, 0, data.length);
            Output.close();
            Log.i(tga, "上传成功");
        } catch (Exception ex) {
            Log.e("写入失败。 ", ex);
        }
    }
}
