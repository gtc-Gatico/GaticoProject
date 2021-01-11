package cn.com.gatico.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class HelloImpl extends UnicastRemoteObject implements Hello {

    protected HelloImpl() throws RemoteException {
        super();
    }

    @Override
    public String sayHello(User user) throws RemoteException {
        System.out.println(user.getName());
        return "user:"+user.getName();
    }
}
