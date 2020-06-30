package cn.tjax.jdk8_annotation;

import com.sun.deploy.security.ValidationState;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.LOCAL_VARIABLE;

/**
 * @ClassName MyAnnotation
 * @Description TODO
 * @Author 洛上云雾
 * @Date 2020/6/30 9:32
 * @Version 1.0
 */
@Repeatable(MyAnnotations.class)
@Target({TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE, TYPE_PARAMETER})
//TYPE_PARAMETER 类型注解
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {

    String value() default "默认值";

}
