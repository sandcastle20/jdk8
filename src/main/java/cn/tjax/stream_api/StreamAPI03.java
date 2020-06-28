package cn.tjax.stream_api;

/**
 * @ClassName StreamAPI03
 * @Description TODO
 * @Author 洛上云雾
 * @Date 2020/6/28 13:59
 * @Version 1.0
 */

import cn.tjax.lamdba_express.Employee;
import cn.tjax.lamdba_express.Status;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 终止操作
 **/
public class StreamAPI03 {

    List<Employee> employees = Arrays.asList(
            new Employee("张三", 18, 9999.99, Status.FREE),
            new Employee("李四", 38, 5555.99, Status.BUSY),
            new Employee("王五", 50, 6666.66, Status.VOCATION),
            new Employee("赵六", 16, 3333.33, Status.FREE),
            new Employee("田七", 8, 7777.77, Status.BUSY)
    );

    /**
     * allMatch(Predicate p)
     * -- 检查是否匹配所有元素
     * anyMatch(Predicate p)
     * -- 检查是否至少匹配一个元素
     * noneMatch(Predicate p)
     * -- 检查是否没有匹配所有元素
     * findFirst()
     * -- 返回第一个元素
     * findAny()
     * -- 返回当前流中的任意元素
     * count
     * -- 返回流中元素的总个数
     * max
     * -- 返回流中最大值
     * min
     * -- 返回流中最小值
     **/
    @Test
    public void test01(){
        boolean b1 = employees.stream()
                .allMatch((employee -> employee.getStatus().equals(Status.BUSY)));
        System.out.println(b1);//false

        System.out.println("------");

        boolean b2 = employees.stream()
                .anyMatch(employee -> employee.getStatus().equals(Status.BUSY));
        System.out.println(b2);//true

        //联想：与SQL自查询 any all 关键字差不多

        System.out.println("------");

        boolean b3 = employees.stream()
                .noneMatch(employee -> employee.getStatus().equals(Status.BUSY));
        System.out.println(b3);//false

        System.out.println("------");
        // JAVA8中 Optional容器类 尽可能避免空指针异常
        Optional<Employee> optional = employees.stream()
                .sorted((e1, e2) -> -Double.compare(e1.getSalary(), e2.getSalary()))
                .findFirst();
        System.out.println(optional.get());

        //Employee employee = optional.orElse(new Employee("N/A", -1, 0.00, Status.FREE));//【core】如果optional容器类中为null，orElse（other）为替代的容器other，就可以避免为空
        //System.out.println(employee);
        //联想：相当与SQL中的 ifnull 关键字

        System.out.println("------");

        Optional<Employee> op2 = employees.parallelStream()//串行流：两个线程同时找
                .filter(e -> e.getStatus().equals(Status.FREE))
                .findAny();
        System.out.println(op2.get());

        System.out.println("------");

        long count = employees.stream()
                .count();
        System.out.println(count);//5
        Optional<Employee> max = employees.stream()
                .max((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()));
        System.out.println(max.get());//Employee(name=张三, age=18, salary=9999.99, status=FREE)

        Optional<Double> min = employees.stream()
                .map(Employee::getSalary)
                .min(Double::compareTo);
        System.out.println(min.get());//3333.33

    }

    /**
     * reduce(T identity,BindaryOperator)
     * reduce(BinaryOperator)
     * -- 可以讲流中元素反复结合起来，得到一个值
     **/
    @Test
    public void test02(){
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Integer sum = list.stream()
                .reduce(0, (x, y) -> x + y);//identity:起始值
        //原理：先将起始值：0作为x,在list中取一个值作为y：1 x+y = 1
        //      然后又将x+y的值作为x，又从流中去下一个值作为y，...
        System.out.println(sum);//55

        System.out.println("--------");
        Optional<Double> reduce = employees.stream()
                .map(Employee::getSalary)
                .reduce(Double::sum);
        System.out.println(reduce.get());//33333.740000000005
    }

    /**
     * collect
     * -- 将流转换为其他形式。接收一个Collector接口的实现，用于给Stream中元素做汇总的方法
     **/
    @Test
    public void test03(){
        List<String> employeeNameList = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.toList());
        employeeNameList.forEach(System.out::println);

        System.out.println("--------------");
        Set<String> set = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.toSet());
        set.forEach(System.out::println);

        System.out.println("--------------");
        HashSet<String> collect = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.toCollection(HashSet::new));
        collect.forEach(System.out::println);

    }


    @Test
    public void test04(){
        //总数
        Long count = employees.stream()
                .collect(Collectors.counting());
        System.out.println(count);//5

        System.out.println("----------");
        //平均值
        Double collect = employees.stream()
                .collect(Collectors.averagingDouble(Employee::getSalary));
        System.out.println(collect);//6666.748

        System.out.println("----------");
        //总和
        Double collect1 = employees.stream()
                .collect(Collectors.summingDouble(Employee::getSalary));
        System.out.println(collect1);//33333.74

        System.out.println("-------------");
        //最大值
        Optional<Employee> collect2 = employees.stream()
                .collect(Collectors.maxBy((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary())));
        System.out.println(collect2.get());//Employee(name=张三, age=18, salary=9999.99, status=FREE)

        System.out.println("-------------");
        //最小值
        Optional<Double> collect3 = employees.stream()
                .map(Employee::getSalary)
                .collect(Collectors.minBy(Double::compare));
        System.out.println(collect3.get());//3333.33
    }

    //分组
    @Test
    public void test05(){

        //分组
        Map<Status, List<Employee>> map = employees.stream()
                .collect(Collectors.groupingBy(Employee::getStatus));
        System.out.println(map);

        //多级分组
        Map<Status, Map<String, List<Employee>>> collect = employees.stream()
                .collect(Collectors.groupingBy(Employee::getStatus, Collectors.groupingBy(e -> {
                    if (((Employee) e).getAge() <= 35)
                        return "青年";
                    else if (((Employee) e).getAge() <= 50)
                        return "中年";
                    else
                        return "老年";
                })));
        System.out.println(collect);
    }

    //分区
    @Test
    public void test06(){
        Map<Boolean, List<Employee>> map = employees.stream()
                .collect(Collectors.partitioningBy((e) -> e.getSalary() > 8000));
        System.out.println(map);
    }

    //统计
    @Test
    public void test07(){
        DoubleSummaryStatistics collect = employees.stream()
                .collect(Collectors.summarizingDouble(Employee::getSalary));
        System.out.println(collect.getSum());
        System.out.println(collect.getAverage());
        System.out.println(collect.getMax());
    }

    //字符串连接
    @Test
    public void test08(){
        String collect = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.joining(",","First->","<-Last"));//First->张三,李四,王五,赵六,田七<-Last
        System.out.println(collect);

    }
}
