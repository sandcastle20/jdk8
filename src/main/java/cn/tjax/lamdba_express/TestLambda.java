package cn.tjax.lamdba_express;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @ClassName TestLambda
 * @Description TODO
 * @Author 洛上云雾
 * @Date 2020/6/23 14:39
 * @Version 1.0
 */
public class TestLambda {

    List<Employee> employees = Arrays.asList(
            new Employee("张三", 18, 9999.99),
            new Employee("李四", 38, 5555.99),
            new Employee("王五", 50, 6666.66),
            new Employee("赵六", 16, 3333.33),
            new Employee("田七", 8, 7777.77)
    );

    //练习一：
    /**
     * 调用Collections.sort()方法，通过定制排序比较两个Employee(先按照年龄比，年龄相同按照姓名比，
     * 使用Lambda作为参数传递
     **/
    @Test
    public void test1(){
        Collections.sort(employees,(e1,e2) -> {
            if (e1.getAge() == e2.getAge()){
                return e1.getName().compareTo(e2.getName());
            }else {
                //return Integer.compare(e1.getAge(),e2.getAge());//升序
                return -Integer.compare(e1.getAge(),e2.getAge());//降序
            }
        });
        employees.forEach(System.out::println);
    }

    //练习二
    /*
     * （1）声明函数式编程接口，接口中声明抽象方法，public String getValue(String str);
     * （2）声明类TestLambda,类中编写方法使用接口作为参数，将一个字符串转换成大写，并
     * 作为方法的返回值
     * （3）再将一个字符串的第2个和第4个索引位置进行截取字串
     **/
    @Test
    public void test2(){
        String trimStr = strHandler("\t\t\t 我再学习Lambda表达式！！", (str) -> str.trim());
        System.out.println(trimStr);//我再学习Lambda表达式！！

        String upper = strHandler("abcdef", (str) -> str.toUpperCase());
        System.out.println(upper);//ABCDEF

        String substringStr = strHandler("第一MAX?", (str) -> str.substring(2, 5));
        System.out.println(substringStr);//MAX
    }

    //处理字符串方法
    public String strHandler(String str,MyFunctionalInterface myFunctionalInterface){
        return myFunctionalInterface.getValue(str);
    }

    //练习三
    /**
     * (1)声明一个带两个泛型的函数式接口，泛型类型<T,R> T为参数，R为返回值
     * (2)接口中声明对应抽象方法
     * (3)在TestLambda类中声明方法，使用接口作为参数，计算两个long型参数的和
     * (4)再计算两个long型参数的乘积
     **/
    @Test
    public void test3(){
        cal(100L,200l,(x,y) -> x + y);//300

        cal(100L,200L,(x,y) -> x * y);//20000

    }

    //需求：对于两个Long型数据进行处理
    public void cal(Long l1,Long l2,MyFunctionInterface02<Long,Long> myFunctionInterface02){
        System.out.println(myFunctionInterface02.getValue(l1,l2));
    }

}
