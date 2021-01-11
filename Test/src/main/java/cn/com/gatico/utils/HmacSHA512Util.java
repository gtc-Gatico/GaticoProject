package cn.com.gatico.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class HmacSHA512Util {

    private static Log logger = LogFactory.getLog(HmacSHA512Util.class);

    public static String getSign(String body, String key) {
        String sign = "";
        try {
            byte[] byteKey = key.getBytes();
            final String HMAC_SHA512 = "HmacSHA512";
            Mac sha512_HMAC = Mac.getInstance(HMAC_SHA512);
            SecretKeySpec keySpec = new SecretKeySpec(byteKey, HMAC_SHA512);
            sha512_HMAC.init(keySpec);
            byte[] mac_data = sha512_HMAC.doFinal(body.getBytes());

            sign = bytesToHex(mac_data);
        } catch (Exception e) {
            logger.error("闪进签名生成异常", e);
        }

        return sign;
    }

    private static String bytesToHex(byte[] bytes) {
        final char[] hexArray = "0123456789ABCDEF".toCharArray();
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

}
