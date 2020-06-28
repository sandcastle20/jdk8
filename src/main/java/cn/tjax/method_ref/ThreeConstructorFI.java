package cn.tjax.method_ref;

@FunctionalInterface
public interface ThreeConstructorFI<T,U,W,R> {
    R accept(T t,U u,W w);
}
