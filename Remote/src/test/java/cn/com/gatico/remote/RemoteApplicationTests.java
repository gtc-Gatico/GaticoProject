package cn.com.gatico.remote;

import cn.com.gatico.remote.bean.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RemoteApplicationTests {

    @Autowired
    HelloService helloService;

    @Test
    void contextLoads() {
        User user = helloService.getUser();
        System.out.println(user);
    }

}
