package cn.com.gatico;

import java.sql.Timestamp;

/**
 * @author Gatico
 * @version 1.0
 * @date 2020/2/26 16:21
 */
public class Ipv42Int {
    public static void main(String[] args) {
        System.out.println(new Timestamp(System.currentTimeMillis()).getTime() / 1000);
        Integer integer = 1568;
        System.out.println(integer.byteValue());
        System.out.println(new String(new byte[]{integer.byteValue()}));
        for (byte b : "[]".getBytes()) {
            System.out.println(b);
        }
        long ipv4int = ipv4ToInt("172.17.1.187 ");
        System.out.println("--" + ipv4int);
        long i = 3030589592L;
        System.out.println(intToIpv4((int) i));
        System.out.println(ipv4ToInt("172.17.1.1"));
        System.out.println(compareVersion("4.4.0", "4.4.0"));
        System.out.println(new String(new byte[]{91, 93}));

        Timestamp timestamp = new Timestamp(System.currentTimeMillis() * 300000 / 300000);
        System.out.println(timestamp.getTime());
        Timestamp timestamp2 = new Timestamp(System.currentTimeMillis() / 300000 * 300000);
        System.out.println(timestamp2.getTime());

    }

    public static long ipv4ToInt(String ipv4) {
        long result = 0;
        String[] ipv4Arr = ipv4.split("\\.");

        for (int i = 3; i >= 0; --i) {
            long seg = Long.parseLong(ipv4Arr[3 - i]);
            result = (result | seg << i * 8);
        }

        return result;
    }

    public static String intToIpv4(int val) {
        int min = 0x1;
        int max = 0xffffffff;
//        if (val < min || val > max) {
//            throw new IllegalArgumentException(val + " not be valid ipv4 value.");
//        }

        StringBuilder ipv4 = new StringBuilder();
        for (int i = 0; i <= 3; i++) {
            ipv4.append(val >> ((3 - i) * 8) & 0xff);
            if (i < 3) {
                ipv4.append('.');
            }
        }

        return ipv4.toString();
    }

    public static boolean compareVersion(String currentVersion, String targetVersion) {
        String[] array1 = currentVersion.split("\\.");
        String[] array2 = targetVersion.split("\\.");
        return Long.valueOf(array1[2]) + Long.valueOf(array1[1]) * 1000L + Long.valueOf(array1[0]) * 1000000L >= Long.valueOf(array2[2]) + Long.valueOf(array2[1]) * 1000L + Long.valueOf(array2[0]) * 1000000L;
    }

    public void Test(String str) {
        System.out.println(str);
    }
}
