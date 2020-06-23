package cn.tjax.lamdba_express;

import org.junit.Test;

import java.util.*;

/**
 * @ClassName lamdba01
 * @Description TODO
 * @Author 洛上云雾
 * @Date 2020/6/19 10:38
 * @Version 1.0
 */
public class lamdba01 {

    //原来的匿名内部类
    @Test
    public void test1(){
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1,o2);//匿名内部类core代码！！！
            }
        };

        TreeSet<Integer> treeSet = new TreeSet<>(comparator);
    }

    //Lambda 表达式
    public void test2(){
        Comparator<Integer> comparator = (x,y)->Integer.compare(x, y);
        TreeSet<Integer> treeSet = new TreeSet<>(comparator);
    }

    List<Employee> employees = Arrays.asList(
            new Employee("张三", 18, 9999.99),
            new Employee("李四", 38, 5555.99),
            new Employee("王五", 50, 6666.66),
            new Employee("赵六", 16, 3333.33),
            new Employee("田七", 8, 7777.77)
    );

    //需求：获取当前公司中员工年薪大于35的员工信息
    @Test
    public void test3(){
        List<Employee> list = filterEmployee(employees);
        for (Employee employee : list) {
            System.out.println(employee);
        }

        //console打印：
        //Employee(name=李四, age=38, salary=5555.99)
        //Employee(name=王五, age=50, salary=6666.66)

    }

    public List<Employee> filterEmployee(List<Employee> list){
        List<Employee> employeesList = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee.getAge() >= 35){
                employeesList.add(employee);
            }
        }
        return employeesList;
    }

    //需求：获取当前公司中员工工资大于5000的员工信息
    public List<Employee> filterEmployee2(List<Employee> list){
        List<Employee> employeesList = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee.getSalary() >= 3000){
                employeesList.add(employee);
            }
        }
        return employeesList;
    }

    //优化方法一：设计模式 策略设计模式
    @Test
    public void test4(){
        List<Employee> list = filterEmployee(employees, new FilterEmployeeByAge());
        for (Employee employee : list) {
            System.out.println(employee);
        }
    }

    public List<Employee> filterEmployee(List<Employee> list,MyPredicate<Employee> myPredicate){
        List<Employee> employeeList = new ArrayList<>();
        for (Employee employee : employees){
            if (myPredicate.test(employee)){
                employeeList.add(employee);
            }
        }
        return employeeList;
    }

    //优化方式二：匿名内部类
    @Test
    public void test5(){
        List<Employee> employeesList = filterEmployee(this.employees, new MyPredicate<Employee>() {
            @Override
            public boolean test(Employee employee) {
                return employee.getSalary() <= 5000;
            }
        });
        for (Employee employee : employeesList){
            System.out.println(employee);
        }
    }

    //优化方式三：lamdba表达式
    @Test
    public void test6(){
        List<Employee> employees = filterEmployee(this.employees, (e) -> e.getSalary() <= 5000);
        employees.forEach(System.out::println);
    }

    //优化方式四：Stream API
    @Test
    public void test7(){
        employees.stream()
                .filter((e)->e.getSalary() >= 5000)
                .limit(2)
                .forEach(System.out::println);
        System.out.println("-----------------------------------");
        employees.stream()
                .map(Employee::getName)
                .forEach(System.out::println);

    }
}
