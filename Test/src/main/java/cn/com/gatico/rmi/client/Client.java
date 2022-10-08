package cn.com.gatico.rmi.client;

import cn.com.gatico.rmi.User;
import cn.com.gatico.rmi.anotation.RemoteClient;
import cn.com.gatico.rmi.public_interface.Hello;
import cn.com.gatico.utils.ClassUtil;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Client {
    public static Hello hello;

    public static void main(String[] args) throws MalformedURLException, NotBoundException, RemoteException {
        hello = (Hello) Naming.lookup("rmi://127.0.0.1:8080/Hello" );//获取远程对象
        User user = new User();
        user.setName("124455");
        String sayHello = null;
        try {
            sayHello = hello.sayHello(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(sayHello);
    }


}
