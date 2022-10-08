package cn.com.gatico.remote;

import cn.com.gatico.remote.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.rmi.RmiServiceExporter;

import java.rmi.RemoteException;

@SpringBootApplication
public class RemoteApplication {

    @Autowired
    HelloService helloService;

    @Bean
    public RmiServiceExporter rmiServiceExporter(){
        RmiServiceExporter rmiServiceExporter = new RmiServiceExporter();
        rmiServiceExporter.setServiceName("helloService");
        rmiServiceExporter.setService(helloService);
        rmiServiceExporter.setServiceInterface(HelloService.class);
        rmiServiceExporter.setRegistryPort(998);// 默认为1099，注意占用问题
        try {
            rmiServiceExporter.afterPropertiesSet();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return rmiServiceExporter;
    }

    public static void main(String[] args) {
        SpringApplication.run(RemoteApplication.class, args);
    }

}
