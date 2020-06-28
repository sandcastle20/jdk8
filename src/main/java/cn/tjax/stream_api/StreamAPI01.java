package cn.tjax.stream_api;

/**
 * @ClassName StreamAPI01
 * @Description TODO
 * @Author 洛上云雾
 * @Date 2020/6/28 10:33
 * @Version 1.0
 */

import cn.tjax.lamdba_express.Employee;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * 一、Stream的三个操作步骤
 *
 * 1.创建Stream
 *
 * 2.中间操作
 *
 * 3.中止操作（终端操作）
 *
 **/
public class StreamAPI01 {

    //1.创建Stream
    @Test
    public void test1(){
        //1.可以通过Collection 系列集合提供的stream（）或者parallelStream（）
        List<String> list = new ArrayList<>();
        Stream<String> stream1 = list.stream();//获取串行流

        //2.通过Arrays 中的静态方法获取数组流
        Employee[] emps = new Employee[10];
        Stream<Employee> stream2 = Arrays.stream(emps);

        //3.通过Stream类中的静态方法of（）
        Stream<String> stream3 = Stream.of("aa", "bb", "cc");

        //4.创建无限流
        //4.1 创建无限流方式一：迭代
        Stream<Integer> stream4 = Stream.iterate(0, (x) -> x + 2);

        //为了演示方便，用中止操作
//        stream4.forEach(System.out::println);

        //为了演示方便，用中间操作（只要10个）
        stream4.limit(10).forEach(System.out::println);

        //4.2 创建无限流方式一：生成
//        Stream<Double> generate = Stream.generate(() -> Math.random());
        Stream<Double> stream5 = Stream.generate(Math::random);
        stream5.limit(10).forEach(System.out::println);

    }
}
