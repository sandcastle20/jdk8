package cn.tjax.lambda_inner_core_functionInterface;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @ClassName TestLambda3
 * @Description TODO
 * @Author 洛上云雾
 * @Date 2020/6/24 9:41
 * @Version 1.0
 */
//Java8内置的四大核心函数式接口
public class TestLambda3 {

    //Consumer<T> 消费型接口
    @Test
    public void test1(){
        happy(10000,(m) -> System.out.println("今天去消费"+m+"了"));
        //result:   今天去消费10000.0了
    }

    public void happy(double money, Consumer<Double> doubleConsumer){
        doubleConsumer.accept(money);
    }

    //Applier<T> 供给型接口
    @Test
    public void test2(){
        List<Integer> numList = getNumList(10, () -> (int) (Math.random() * 100));
        for (Integer num : numList) {
            System.out.println(num);
        };
    }

    //需求：产生一些整型，并放入集合中
    public List<Integer> getNumList(int num, Supplier<Integer> integerSupplier){
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            Integer integer = integerSupplier.get();
            list.add(integer);
        }
        return list;
    }

    //Function<T,R> 函数型接口：
    @Test
    public void test3(){
        String handleString = handleString("\t\t 正在学习四大内置函数接口", (string) -> string.trim());
        System.out.println(handleString);//正在学习四大内置函数接口

        String substring = handleString("声明式函数型接口呢？", (str) -> str.substring(3, 8));
        System.out.println(substring);//函数型接口
    }

    //需求：用于处理字符串
    public String handleString(String string, Function<String, String> function){
        return function.apply(string);
    }

    //Predicate<T> 断言型接口：
    @Test
    public void test4(){
        List<String> list = Arrays.asList("hello","bye","like","www");
        List<String> filterStr = filterStr(list, (s) -> s.length() > 3);
        for (String str : filterStr) {
            System.out.println(str);
        }
    }

    //需求：将满足条件的字符串，放入集合中
    public List<String> filterStr(List<String> list, Predicate<String> predicate){
        ArrayList<String> strList = new ArrayList<>();
        for (String str : list) {
            if (predicate.test(str)){
                strList.add(str);
            }
        }
        return strList;
    }



}
