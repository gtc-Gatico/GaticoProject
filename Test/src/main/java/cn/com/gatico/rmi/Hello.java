package cn.com.gatico.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Hello extends Remote {
    String sayHello(User user) throws RemoteException;
}
