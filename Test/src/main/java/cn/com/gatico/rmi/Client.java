package cn.com.gatico.rmi;

import cn.com.gatico.rmi.public_interface.Hello;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Client {
    public static void main(String[] args) {
        try {
            Hello hello = (Hello) Naming.lookup("rmi://127.0.0.1:8080/hello");
            User user = new User();
            user.setName("124455");
            System.out.println(hello.sayHello(user));
        } catch (MalformedURLException | RemoteException | NotBoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
