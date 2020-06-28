package cn.tjax.stream_api;

import cn.tjax.lamdba_express.Employee;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

/**
 * @ClassName Stream02
 * @Description TODO
 * @Author 洛上云雾
 * @Date 2020/6/28 11:03
 * @Version 1.0
 */
public class StreamAPI02 {

    /**
     * filter(Predicate p)
     * -- 接收Lambda，从流中排除某些元素
     * distinct()
     * -- 筛选，通过流所生成的元素的hashcode（）和equal（）去除重复元素
     * limit(long maxSize)
     * -- 截断流，使其元素不超过给定数量
     * skip(long n)
     * -- 跳过元素，返回一个扔掉了前n个元素的流。若流中元素不足n个，则返回一个空流。与limit（n）互补。
     **/

    List<Employee> employees = Arrays.asList(
            new Employee("张三", 18, 9999.99),
            new Employee("李四", 38, 5555.99),
            new Employee("王五", 50, 6666.66),
            new Employee("赵六", 16, 3333.33),
            new Employee("田七", 8, 7777.77),
            new Employee("田七", 15, 7777.77),
            new Employee("田七", 8, 7777.77)
    );


    //filter(Predicate p)
    //内部迭代:迭代操作由Stream API 完成
    @Test
    public void Test01(){
        //中间操作：不会执行任何操作
        Stream<Employee> employeeStream = employees.stream()
                .filter((e) -> e.getAge() > 35);
                //注意：filter()方法不会执行，直到中止操作时执行

        //终止操作：一次性执行全部操作，即“惰性求值”
        employeeStream.forEach(System.out::println);
        //Employee(name=李四, age=38, salary=5555.99)
        //Employee(name=王五, age=50, salary=6666.66)

        //结论：Stream API 存在迭代操作
    }

    //外部迭代：
    @Test
    public void Test01_2(){
        Iterator<Employee> iterator = employees.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }


    //limit(maxSize)
    @Test
    public void Test02(){
        employees.stream()
                .filter((e) ->{
                    System.out.println("短路现象");
                    return e.getSalary()>5000;
                })//注意：【提高效率】一旦存在limit（maxSize），不会内部迭代所有数据，与“&&”,"||"类似
                .limit(2)
                .forEach(System.out::println);
    }

    //skip(long n)
    @Test
    public void Test03(){
        employees.stream()
                .filter((e) -> e.getSalary()>5000)
                .skip(2)//“跳过前两个去后两个”
                .forEach(System.out::println);
    }

    //distinct()
    @Test
    public void Test04(){
        employees.stream()
                .distinct()
                .forEach(System.out::println);
    }

    /**
     * 映射
     * map
     * -- 接收Lambda，将元素转换成其他形式或者提取信息。接受一个函数作为参数，该函数会被应用到每个元素上，将其映射成一个新的元素。
     *
     * flatMap
     * -- 接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流
     **/
    //map  && flatMap
    @Test
    public void Test05(){
        List<String> list = Arrays.asList("aaa", "bbb", "ccc", "ddd", "eee");

        list.stream()
                .map((str) -> str.toUpperCase())
                .forEach(System.out::println);

        System.out.println("-------------");

        employees.stream()
                .map(Employee::getName)
                .forEach(System.out::println);

        System.out.println("-------------");

        Stream<Stream<Character>> stream = list.stream()
                .map(StreamAPI02::filterCharater);
        stream.forEach((sm) -> sm.forEach(System.out::println));

        System.out.println("-------------");

        Stream<Character> stream1 = list.stream()
                .flatMap(StreamAPI02::filterCharater);
        stream1.forEach(System.out::println);
    }



    //需求：将字符串提取出每个字母加入集合创建流
    public static Stream<Character> filterCharater(String str){
        ArrayList<Character> list = new ArrayList<>();
        for (Character ch : str.toCharArray()) {
            list.add(ch);
        }
        return list.stream();
    }


    /**
     * sorted()
     * -- 自然排序
     * sorted(Comparator comp)
     * -- 定制排序
     **/
    @Test
    public void Test06(){
        List<String> list = Arrays.asList("ccc", "bbb", "aaa", "ddd", "eee");
        list.stream()
                .sorted()
                .forEach(System.out::println);

        System.out.println("-----");

        employees.stream()
                .sorted((e1, e2) -> {
                    if (e1.getAge() == e2.getAge()){
                        return e1.getName().compareTo(e2.getName());
                    }else {
                        return -Integer.compare(e1.getAge(),e2.getAge());
                    }
                })
                .forEach(System.out::println);
    }
}
