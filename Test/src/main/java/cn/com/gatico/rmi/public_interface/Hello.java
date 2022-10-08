package cn.com.gatico.rmi.public_interface;

import cn.com.gatico.rmi.User;
import cn.com.gatico.rmi.anotation.RemoteClient;

import java.rmi.Remote;
import java.rmi.RemoteException;

@RemoteClient(path = "Hello")
public interface Hello extends Remote {
    String sayHello(User user) throws RemoteException;
}
