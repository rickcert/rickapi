package com.rick.spring.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 小松
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Log {
    // 具体的日志的内容
    String title() default "";
    // 日志业务类型的分类，便于条件帅选
    String businessType() default "";

}
