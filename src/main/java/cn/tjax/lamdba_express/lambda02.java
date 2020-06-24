package cn.tjax.lamdba_express;

import org.junit.Test;

import java.util.*;
import java.util.function.Consumer;

/**
 * @ClassName lambda02
 * @Description TODO
 * @Author 洛上云雾
 * @Date 2020/6/23 11:31
 * @Version 1.0
 */
public class lambda02 {

    @Test
    public void test1(){

        //JDK1.8 final不用加
        int num = 0;//局部变量num jdk1.7 前必须是final，默认加了，其值还是不能改变。

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello world!");
            }
        };//局部内部类

        runnable.run();

        System.out.println("-------------");

        Runnable r1 = () -> System.out.println("Hello Lambda!");
        r1.run();

        Runnable r2 = () -> System.out.println("Hello Lambda!"+num);//其中num不能++,语法糖！
    }


    @Test
    public void test2(){

        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println("匿名内部类Consumer:"+s);
            }
        };
        consumer.accept(" Hello world!");

        System.out.println("------------------------------");

        Consumer<String> conn = (x) -> System.out.println("Lamdba语法糖Consumer:"+x);
        conn.accept(" Hello Lambda!");

    }


    @Test
    public void test3(){
        Comparator<Integer> com = (x,y) -> {
            System.out.println("函数式接口");
            return Integer.compare(x, y);
        };
        System.out.println(com.compare(3,5));//-1
        System.out.println(com.compare(5,3));//1
        System.out.println(com.compare(3,3));//0
    }

    @Test
    public void test4(){
        Comparator<Integer> com = (x,y) -> Integer.compare(x, y);
        System.out.println(com.compare(3,5));//-1
        System.out.println(com.compare(5,3));//1
        System.out.println(com.compare(3,3));//0
    }

    @Test
    public void test5(){
        String[] strs = {"aa","bb","cc"};//"aa"也是推断出的
        //不能这样写
        //String[] strs;
        //strs = {"aa","bb","cc"};

        List<String> objects = new ArrayList<>();//ArrayList<>也是类型推断

        show(new HashMap<>());//jdk8中HashMap<>也是类型推断
    }

    public void show(Map<String, Integer> map){
    }


    //需求：对一个数进行运算
    @Test
    public void test6() {
        Integer num = operation(100, x -> x * x);
        System.out.println(num);

        System.out.println(operation(200, y -> y+200));
    }

    public Integer operation(Integer num,MyFunction myFun){
        return myFun.getValue(num);
    }
}
