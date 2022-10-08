package cn.com.gatico.rmi.server;

import cn.com.gatico.rmi.User;
import cn.com.gatico.rmi.anotation.RemoteService;
import cn.com.gatico.rmi.public_interface.Hello;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

@RemoteService(path = "Hello")
public class HelloImpl extends UnicastRemoteObject implements Hello {

    public HelloImpl() throws RemoteException {
    }

    @Override
    public String sayHello(User user) throws RemoteException {
        System.out.println(user.getName());
        return "user:"+user.getName();
    }
}
