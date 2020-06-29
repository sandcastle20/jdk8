package cn.tjax.jdk8_interface;

/**
 * @ClassName MyInterface
 * @Description TODO
 * @Author 洛上云雾
 * @Date 2020/6/29 15:48
 * @Version 1.0
 */
public interface MyInterface {
    default String getName(){
        return "呵呵呵";
    }

    static void show(){
        System.out.println("接口中的静态方法");
    }
}
