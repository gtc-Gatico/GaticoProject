package cn.com.gatico;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ApplicationCodeImage {
//    @Bean
//    public RmiProxyFactoryBean rmiProxyFactoryBean(){
//        RmiProxyFactoryBean rmiProxyFactoryBean = new RmiProxyFactoryBean();
//        rmiProxyFactoryBean.setServiceUrl("rmi://127.0.0.1:998/helloService");
//        rmiProxyFactoryBean.setServiceInterface(HelloService.class);
//        return rmiProxyFactoryBean;
//    }
    public static void main(String[] args) {
        SpringApplication.run(ApplicationCodeImage.class, args);
    }

}
