package cn.com.gatico.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {
    @ExceptionHandler(Exception.class)
    public String getException(RuntimeException e){
        return e.getMessage();
    }
}
