package cn.com.gatico.smailRest;

import cn.com.gatico.utils.ByteUtils;
import cn.com.gatico.utils.MD5Utils;

@Restful(url = "/utils")
public class TestRestful extends AbsRestful {

    public Object md5(String str) {
        String encrypt = MD5Utils.encrypt(str);
        return encrypt;
    }

    public Object longToByte(int num) {
        return ByteUtils.longToByte(num);
    }

    public Object bytesToLong(byte[] buffer) {
        return ByteUtils.bytesToLong(buffer);
    }
}
