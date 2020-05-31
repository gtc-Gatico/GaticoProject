package cn.com.gatico.utils;

public class IPv4Utils {

    public static String intToStr(int intIPv4) {
        return ((intIPv4 >> 24) & 0xFF) + "."
                + ((intIPv4 >> 16) & 0xFF) + "."
                + ((intIPv4 >> 8) & 0xFF) + "."
                + (intIPv4 & 0xFF);
    }

    public static int strToint(String strIPv4) {
        int result = 0;

        String[] ipInArray = strIPv4.split("\\.");

        for (int i = 3; i >= 0; i--) {

            int ip = Integer.parseInt(ipInArray[3 - i]);
            result |= ip << (i * 8);
        }
        return result;
    }

    public static int prefixToint(String prefix) {
        int result = -1;
        result = result << (32 - Integer.parseInt(prefix));
        return result;
    }

    public static String transferCIDR(String strIPv4, String prefix) {
        return intToStr(strToint(strIPv4) & prefixToint(prefix)) + "/" + prefix;
    }

    public static boolean isInNet(String strIPv4, String strCIDR) {
        String[] cidr = strCIDR.split("/");
        String netIPv4 = cidr[0];
        String strPrefix = cidr[1];
        return transferCIDR(strIPv4, strPrefix).equals(strCIDR);
    }

    public static long strTolong(String strIPv4) {
        long result = 0;

        String[] ipInArray = strIPv4.split("\\.");

        for (int i = 3; i >= 0; i--) {

            long ip = Long.parseLong(ipInArray[3 - i]);
            result |= ip << (i * 8);
        }
        return result;
    }
}
