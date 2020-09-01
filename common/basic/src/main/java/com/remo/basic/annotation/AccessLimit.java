package com.remo.basic.annotation;

import java.lang.annotation.*;

/**
 * @Targe 用于提示该注解使用的地方
 * @Retention 用于提示注解被保留多长时间
 */

@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AccessLimit {
    int seconds() default 5;

    int maxCount() default 5;

    boolean needLogin() default true;
}
