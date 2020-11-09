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
            System.out.println(file.length());
            try {
                sendProtocol(protocol);
                FileInputStream fileInputStream = new FileInputStream(file);//8388608
                int length = 0;
                byte temp[] = new byte[file.length() > 8388608 ? 8388608 : (int) file.length()];
                while ((length = fileInputStream.read(temp, 0, temp.length)) > 0) {
                    os.write(temp, 0, length);
                    os.flush();
                }
                fileInputStream.close();
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
                    FileInputStream fileInputStream = new FileInputStream(tmp);
                    byte[] temp = new byte[tmp.length() > 8388608 ? 8388608 : (int) tmp.length()];
                    while (fileInputStream.read(temp) != -1) {
                        os.write(temp);
                        os.flush();
                    }
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
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public void sendProtocol(Protocol protocol) {
        try {
            os.write(Util.intToByte(protocol.getProtocolTitle()));
            os.write(protocol.getType());
            if (protocol.getType() == 2) {
                os.write(Util.intToByte(protocol.getSplitByte()));
            } else {
                os.write(protocol.getNameLength());
                os.write(protocol.getFileName());
                os.write(Util.longToByte(protocol.getFileLength()));
            }
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public byte[] getFileByte(byte[] filearr, String filename) {
        byte[] namearr = filename.getBytes();
        System.out.println("客户端:获取的文件名长度:" + namearr.length);
        ByteArrayOutputStream bos = new ByteArrayOutputStream(namearr.length + filearr.length + 4);
        try {
            bos.write(Util.intToByte(filearr.length));
            bos.write(filearr);
            bos.write(namearr);
            bos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bos.toByteArray();
    }

    /**
     * 上传字节数组
     *
     * @param arr
     * @return
     */
    public boolean uploadByte(byte[] arr) {
        OutputStream os = null;
        try {
            this.init();
            os = socket.getOutputStream();
            os.write(arr);
            os.flush();
            socket.close();
            socket = null;
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
