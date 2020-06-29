package cn.tjax.jdk8_interface;

import cn.tjax.lamdba_express.MyFunction;

/**
 * @ClassName SubClass
 * @Description TODO
 * @Author 洛上云雾
 * @Date 2020/6/29 15:17
 * @Version 1.0
 */
public class SubClass /*extends MyClass*/ implements MyFun, MyInterface {

    @Override
    public String getName() {
        return MyInterface.super.getName();
    }

}
