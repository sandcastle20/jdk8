package cn.tjax.lamdba_express;

/**
 * @ClassName MyPredicate
 * @Description TODO
 * @Author 洛上云雾
 * @Date 2020/6/23 10:50
 * @Version 1.0
 */
public interface MyPredicate<T> {
    boolean test(T t);
}
