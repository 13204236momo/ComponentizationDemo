package com.example.componentizationdemo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.CLASS)
public @interface Parameter {
    //不填写name的注解值表示该属性名就是key，填写了就用注解值作为key
    //从getIntent()方法中获取传递参数值
    String name() default "";
}
