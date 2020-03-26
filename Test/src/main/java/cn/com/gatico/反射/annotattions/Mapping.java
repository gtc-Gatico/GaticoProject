package cn.com.gatico.反射.annotattions;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface Mapping {
    String url() default "";

    String method() default "get";
}
