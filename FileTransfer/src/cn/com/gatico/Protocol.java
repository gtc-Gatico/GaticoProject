package cn.com.gatico;

public class Protocol {
    private int protocolTitle;//FT  协议头，版本  32位
    //数据内容
    private byte type;//1单文件，2 多文件 8位
    private int splitByte;//文件分隔符 32位 单文件 没有这个数据
    private byte nameLength;//文件名长度 8位，内容字节数
    private byte[] fileName; //文件名 文件长度  - 255
    private long fileLength; //文件长度 64位
    private byte[] fileContext;//文件内容 不定长

    private ProtocolStatus protocolStatus = ProtocolStatus.protocolTitle;

    private int tempSize;

    public int getProtocolTitle() {
        return protocolTitle;
    }

    public void setProtocolTitle(int protocolTitle) {
        this.protocolTitle = protocolTitle;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public int getSplitByte() {
        return splitByte;
    }

    public void setSplitByte(int splitByte) {
        this.splitByte = splitByte;
    }

    public byte getNameLength() {
        return nameLength;
    }

    public void setNameLength(byte nameLength) {
        this.nameLength = nameLength;
    }

    public byte[] getFileName() {
        return fileName;
    }

    public void setFileName(byte[] fileName) {
        this.fileName = fileName;
    }

    public long getFileLength() {
        return fileLength;
    }

    public void setFileLength(long fileLength) {
        this.fileLength = fileLength;
    }

    public ProtocolStatus getProtocolStatus() {
        return protocolStatus;
    }

    public void setProtocolStatus(ProtocolStatus protocolStatus) {
        this.protocolStatus = protocolStatus;
    }

    public int getTempSize() {
        return tempSize;
    }

    public void setTempSize(int tempSize) {
        this.tempSize = tempSize;
    }
}
