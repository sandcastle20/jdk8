package cn.tjax.method_ref;

import cn.tjax.lamdba_express.Employee;
import org.junit.Test;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.*;
import java.util.stream.Stream;

/**
 * @ClassName TestMethodRef
 * @Description TODO
 * @Author 洛上云雾
 * @Date 2020/6/24 14:04
 * @Version 1.0
 */
public class TestMethodRef {

    //对象::实例方法名
    @Test
    public void test1(){
        //Lambda表达式形式
        Consumer<String> consumer11 = (x) -> System.out.println(x);

        PrintStream ps = System.out;
        Consumer<String> tConsumer = (x) -> ps.println(x);

        //方法引用 对象：：实例方法名
        PrintStream printStream = System.out;
        Consumer<String> consumer12 = printStream::println;

        consumer12.accept("正在学习：方法引用1（对象::实例方法）");

        System.out.println("--------------------------------");
        //Lambda表达式形式
        Employee employee = new Employee("tjax",18,10000);
        Supplier<String> supplier13 = () -> employee.getName();
        String str1 = supplier13.get();
        System.out.println(str1);

        //方法引用 类::静态实例方法名
        Supplier<String> supplier14 = employee::getName;
        String str2 = supplier14.get();
        System.out.println(str2);
    }

    //类::静态方法名
    @Test
    public void test2(){
        //Lambda表达式形式
        Comparator<Integer> com21 = (x,y) -> Integer.compare(x, y);

        //方法引用 对象：：实例方法名
        Comparator<Integer> com22 = Integer::compare;
        System.out.println(com22.compare(1,2));//-1
        System.out.println(com22.compare(2,2));//0
        System.out.println(com22.compare(2,1));//1

    }

    //类::实例方法名
    @Test
    public void test3(){
        //Lambda表达式形式
        BiPredicate<String,String> biPredicate31 = (x,y) -> x.equals(y);

        //方法引用 类::实例方法名
        BiPredicate<String,String> biPredicate32 = String::equals;
        System.out.println(biPredicate32.test("我","我"));//true
    }

    //构造器引用     ClassName::new
    @Test
    public void test4(){
        //Lambda表达式形式
        Supplier<Employee> supplier41 = () -> new Employee();

        //构造器引用     ClassName::new
        Supplier<Employee> supplier42 = Employee::new;
        System.out.println(supplier42.get());//Employee(name=null, age=0, salary=0.0)

        System.out.println("-----------------------");

        //Lambda表达式形式
        Function<String, Employee> function43 = (x) -> new Employee(x);

        //构造器引用     ClassName::new
        Function<String, Employee> function44 = Employee::new;
        System.out.println(function44.apply("tjax"));//Employee(name=tjax, age=0, salary=0.0)

        System.out.println("-----------------------");
        BiFunction<String,Integer,Employee> function45 = Employee::new;
        System.out.println(function45.apply("tjax",18));//Employee(name=tjax, age=18, salary=0.0)

        System.out.println("---------------------------");
        ThreeConstructorFI<String,Integer,Double,Employee> threeConstructorFI = Employee::new;
        System.out.println(threeConstructorFI.accept("tjax",18,10000.0));//Employee(name=tjax, age=18, salary=10000.0)
    }

    //数组引用 Type::new
    @Test
    public void test5() {
        Function<Integer,String[]> fun = (x) -> new String[x];
        String[] strs = fun.apply(10);
        System.out.println(strs.length);//10

        Function<Integer, String[]> fun2 = String[]::new;
        String[] strs2 = fun2.apply(20);
        System.out.println(strs2.length);//20
    }




}
