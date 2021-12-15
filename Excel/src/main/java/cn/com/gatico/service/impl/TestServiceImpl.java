package cn.com.gatico.service.impl;

import cn.com.gatico.service.TestService;
import com.d2rabbit.exception.annotation.CheckExceptionx;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {
    @Override
    @CheckExceptionx(code = 1,message = "算错了")
    public int getRes() {
        int x = 10/0;
        return x;
    }
}
