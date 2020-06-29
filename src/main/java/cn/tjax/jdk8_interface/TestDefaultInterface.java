package cn.tjax.jdk8_interface;

/**
 * @ClassName TestDefaultInterface
 * @Description TODO
 * @Author 洛上云雾
 * @Date 2020/6/29 15:21
 * @Version 1.0
 */
public class TestDefaultInterface {

    public static void main(String[] args) {
        SubClass subClass = new SubClass();
        System.out.println(subClass.getName());//嘿嘿嘿
        MyInterface.show();//接口中的静态方法
    }

}
