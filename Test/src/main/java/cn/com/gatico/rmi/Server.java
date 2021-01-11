package cn.com.gatico.rmi;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class Server {

    public static void main(String[] args) {

        try {
            Hello hello = new HelloImpl(); // 创建一个远程对象，同时也会创建stub对象、skeleton对象

            LocateRegistry.createRegistry(8080); //启动注册服务
            try {
                Naming.bind("//127.0.0.1:8080/hello", hello); //将stub引用绑定到服务地址上
            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (AlreadyBoundException e) {
                e.printStackTrace();
            }
            System.out.println("service bind already!!");

        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
