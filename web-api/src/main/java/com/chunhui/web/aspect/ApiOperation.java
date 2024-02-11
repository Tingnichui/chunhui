package com.chunhui.web.aspect;


import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface ApiOperation {

    String name();

    String desc() default "";

}
