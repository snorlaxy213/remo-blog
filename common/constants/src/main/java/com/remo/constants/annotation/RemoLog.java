package com.remo.constants.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Targe 用于提示该注解使用的地方
 * @Retention 用于提示注解被保留多长时间
 */
//所有从其他module引用此common，需要common执行maven install
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RemoLog {
    String value() default "";
}
