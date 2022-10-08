package cn.com.gatico.TestByte;

import cn.com.gatico.Ipv42Int;

public class TestIp {
    public static void main(String[] args) {
        String ip1 = "192.168.8.200";
        String ip2 = "192.168.9.199";
        String netMask = "255.255.252.0";
        String netMask2 = "192.168.1.0/30";
        boolean flag = checkIp(ip1, ip2, netMask);
        System.out.println(flag);

        int netMaskl = Ipv42Int.ipv4ToInt("192.168.1.26");
        int ip1l = createIP(24);
        int x = netMaskl & ip1l;
        System.out.println(Ipv42Int.intToIpv4(x));
        System.out.println(Integer.toBinaryString(x));
    }

    public static boolean checkIp(String ip1, String ip2, String netMask) {
        int netMaskl = Ipv42Int.ipv4ToInt(netMask);
        int ip1l = Ipv42Int.ipv4ToInt(ip1);
        int ip2l = Ipv42Int.ipv4ToInt(ip2);
        System.out.println(Integer.toBinaryString(Integer.parseInt(String.valueOf(netMaskl & ip1l))));
        System.out.println(Integer.toBinaryString(Integer.parseInt(String.valueOf(netMaskl & ip2l))));
        return (netMaskl & ip1l) == (netMaskl & ip2l);
    }

    public static int createIP(int c) {
        int p = 0xffffffff;
        p = p << 32 - c;
        System.out.println(Integer.toBinaryString(p));
        System.out.println(Integer.toUnsignedString(p));
        System.out.println(p);
        return p;
    }
}
