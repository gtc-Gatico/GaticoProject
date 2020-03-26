package cn.com.gatico.反射.annotattions;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.TYPE})
public @interface API {

    String url() default "/";

}
