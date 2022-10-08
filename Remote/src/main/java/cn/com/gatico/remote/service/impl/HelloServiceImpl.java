package cn.com.gatico.remote.service.impl;

import cn.com.gatico.remote.bean.User;
import cn.com.gatico.remote.service.HelloService;
import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl implements HelloService {

    public User getUser(){
        User user = new User();
        user.setName("1234567");
        return user;
    }
}
