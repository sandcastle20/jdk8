package cn.tjax.lamdba_express;

@FunctionalInterface
public interface MyFunctionInterface02<T,R> {
    R getValue(T t1,T t2);
}
