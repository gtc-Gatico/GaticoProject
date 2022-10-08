package cn.com.gatico.rmi;

import cn.com.gatico.rmi.public_interface.Hello;
import cn.com.gatico.rmi.server.HelloImpl;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class Server {

    public static void main(String[] args) {

        Hello hello;
        try {
            hello = new HelloImpl(); //生成了stubs skeleton 并且返回了stubs的代理应用
            LocateRegistry.createRegistry(8080);
            //将stub应用绑定到注册的服务地址
            Naming.bind("rmi://127.0.0.1:8080/hello", hello);
            System.out.println("完成服务注册及绑定");
        } catch (RemoteException | AlreadyBoundException | MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
