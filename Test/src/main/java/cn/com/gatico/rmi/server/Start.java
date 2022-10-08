package cn.com.gatico.rmi.server;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class Start {
    public static void main(String[] args) throws RemoteException, MalformedURLException, AlreadyBoundException {
        //Set<Class<?>> classes = ClassUtil.getClasses(Start.class.getPackage().getName());
        //List<Class<?>> collect = classes.stream().filter(aClass -> aClass.getAnnotation(RemoteService.class) != null).collect(Collectors.toList());
        try {
            LocateRegistry.createRegistry(8080); //启动注册服务
        } catch (RemoteException e) {
            e.printStackTrace();
        }
//        collect.forEach(aClass -> {
//            try {
//                String path = aClass.getSimpleName();
//                RemoteService annotation = aClass.getAnnotation(RemoteService.class);
//                if (annotation.path() != null && !annotation.path().equals("")) {
//                    path = annotation.path();
//                }
//                Hello o = (Hello)aClass.newInstance();
//                System.out.println(o.sayHello(new User()));
////                Remote hello = (Remote) aClass.newInstance();
//                Naming.bind("rmi://127.0.0.1:8080/" + path, o);
//            } catch (MalformedURLException | AlreadyBoundException | RemoteException | InstantiationException | IllegalAccessException e) {
//                e.printStackTrace();
//            }
//        });
        HelloImpl o = new HelloImpl();
        Naming.bind("rmi://127.0.0.1:8080/Hello", o);
        System.out.println("service bind already!!");
    }
}
