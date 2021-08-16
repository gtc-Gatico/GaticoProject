package cn.com.gatico.smailRest;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(value = RetentionPolicy.RUNTIME)
public @interface Restful {
    String url() default "/";
//    String method() default "GET" ;
}
