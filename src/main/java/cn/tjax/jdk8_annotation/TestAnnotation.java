package cn.tjax.jdk8_annotation;

import lombok.NonNull;
import org.junit.Test;

import java.lang.annotation.Target;
import java.lang.reflect.Method;

/**
 * @ClassName TestAnnotation
 * @Description TODO
 * @Author 洛上云雾
 * @Date 2020/6/30 9:32
 * @Version 1.0
 */
public class TestAnnotation {

    //checker framework
    //场景：类型注解，防止空指针，否则编译时异常
    private @NonNull Object obj = null;

    @Test
    public void test01() throws NoSuchMethodException {
        Class<TestAnnotation> clazz = TestAnnotation.class;
        Method m1 = clazz.getMethod("show");

        MyAnnotation[] mas = m1.getAnnotationsByType(MyAnnotation.class);


        for (MyAnnotation myAnnotation : mas){
            System.out.print(myAnnotation.value());//HelloWorld
        }
    }

    //重复注解与类型注解
    //使用场景：联合主键要定义等...
    @MyAnnotation("Hello")
    @MyAnnotation("World")
    public void show(@MyAnnotation("abd")String str){

    }
}
