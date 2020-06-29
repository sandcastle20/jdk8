package cn.tjax.stream_api;

import cn.tjax.lamdba_express.Employee;
import cn.tjax.lamdba_express.Status;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @ClassName TestStreamAPI
 * @Description TODO
 * @Author 洛上云雾
 * @Date 2020/6/28 15:54
 * @Version 1.0
 */
public class TestStreamAPI {

    List<Employee> employees = Arrays.asList(
            new Employee("张三", 18, 9999.99, Status.FREE),
            new Employee("李四", 38, 5555.99, Status.BUSY),
            new Employee("王五", 50, 6666.66, Status.VOCATION),
            new Employee("赵六", 16, 3333.33, Status.FREE),
            new Employee("田七", 8, 7777.77, Status.BUSY)
    );

    /**
     * 1.给定一个数字列表，如何返回一个由每个数的平方构成的列表呢？
     *
     * 给定【1，2，3，4，5】
     * 应该返回【1，4，9，16，25】
     **/
    @Test
    public void test01(){
        Integer[] nums = new Integer[]{1,2,3,4,5};
        Integer[] array = Arrays.stream(nums)
                .map((x) -> x * x)
                .toArray(Integer[]::new);
        System.out.println(Arrays.toString(array));//[1, 4, 9, 16, 25]
    }

    /**
     * 2.怎么用map和reduce方法数一数流中有多少个Employee？
     **/
    @Test
    public void test02(){
        Optional<Integer> count = employees.stream()
                .map((e) -> 1)
                .reduce(Integer::sum);
        System.out.println(count.get());//5
    }


}
