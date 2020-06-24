package cn.tjax.lamdba_express;

/**
 * @ClassName MyPredicate
 * @Description TODO
 * @Author 洛上云雾
 * @Date 2020/6/23 10:50
 * @Version 1.0
 */
@FunctionalInterface//被其修饰的接口定义第二个抽象方法编译会不通过
public interface MyPredicate<T> {
    boolean test(T t);

    //boolean test3();
}
