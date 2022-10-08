package cn.com.gatico.network;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class JavaNetworkInterface {
    public static void main(String[] args) throws SocketException {
        Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
        while (networkInterfaces.hasMoreElements()) {
            NetworkInterface networkInterface = networkInterfaces.nextElement();
            if (networkInterface.getMTU() > 0) {
                System.out.println(networkInterface);
                networkInterface.getInterfaceAddresses().forEach(interfaceAddress -> {
                    System.out.println(interfaceAddress.getAddress().getHostAddress());
                    if (interfaceAddress.getBroadcast() != null) {
                        System.out.println(interfaceAddress.getBroadcast().getHostAddress());
                    }
                    System.out.println(interfaceAddress.getNetworkPrefixLength());
                });

                System.out.println(networkInterface.isLoopback());
                if(networkInterface.getParent()!=null){

                    System.out.println(networkInterface.getParent().getName());
                }
            }
        }

    }
}
