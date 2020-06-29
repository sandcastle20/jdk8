package cn.tjax.optionalAPI;

import cn.tjax.lamdba_express.Employee;
import cn.tjax.lamdba_express.Status;
import org.junit.Test;

import java.util.Map;
import java.util.Optional;

/**
 * @ClassName TestOptional
 * @Description TODO
 * @Author 洛上云雾
 * @Date 2020/6/29 14:18
 * @Version 1.0
 */
public class TestOptional {
    /**
     * 1. Optional.of(T t)
     * --  创建一个Optional实例
     * 2. Optional.empty()
     * --  创建一个空的Optional实例
     * 3. Optional.ofNullable(T t)
     * --  若t不为null，创建Optional实例，否则创建空实例
     * 4. isPresent()
     * --  判断是否包含值
     * 5. orElse(T t)
     * --  如果调用对象包含值，返回该值，否则返回t
     * 6. orElseGet(Supplier s)
     * --  如果调用对象包含值，返回该值，否则返回s获取的值
     * 7.  map(Function f)
     * --  如果有值对其处理，并返回处理后的Optional，否则返回Optional.empty()
     * 8.  flatMap(Function mapper)
     * --  与map类似，要求返回值必须是Optional
     **/

    @Test
    public void test01(){
        Optional<Employee> op = Optional.of(new Employee());
        System.out.println(op.get());//Employee(name=null, age=0, salary=0.0, status=null)

        //注意of(null)，参数不能传入null，否则就会出现空指针异常
        //Java8也提供ofNullable（T t）灵活传入值，不会出现NPE异常
    }

    @Test
    public void test02(){
        Optional<Employee> empty = Optional.empty();
        System.out.println(empty.get());//java.util.NoSuchElementException: No value present
    }

    @Test
    public void test03(){
        Optional<Employee> op = Optional.ofNullable(new Employee());
        System.out.println(op.get());//Employee(name=null, age=0, salary=0.0, status=null)

        Optional<Employee> op2 = Optional.ofNullable(null);
//        System.out.println(op2.get());//java.util.NoSuchElementException: No value present

        if (op.isPresent()){
            System.out.println(op.get());//Employee(name=null, age=0, salary=0.0, status=null)
        }

        Employee employee = op2.orElse(new Employee("张三", 18, 888, Status.FREE));
        System.out.println(employee);//Employee(name=张三, age=18, salary=888.0, status=FREE)

        Employee employee1 = op2.orElseGet(Employee::new);
        System.out.println(employee1);//Employee(name=null, age=0, salary=0.0, status=null)
    }

    @Test
    public void test04(){
        Optional<Employee> op = Optional.ofNullable(new Employee("张三", 18, 999, Status.VOCATION));

        Optional<String> str = op.map((e) -> e.getName());
        System.out.println(str.get());//张三

        Optional<String> str2 = op.flatMap((e) -> Optional.of(e.getName()));
        System.out.println(str2.get());//张三
    }

    //例题
    @Test
    public void test05(){
//        Man man = new Man();
//        System.out.println(getGodnessName(man));//java.lang.NullPointerException

        Optional<Goddness> goddness = Optional.ofNullable(null);
        Optional<NewMan> op = Optional.ofNullable(new NewMan(goddness));
//        Optional<NewMan> op = Optional.ofNullable(null);//默认goddness
//        String godnessName2 = getGodnessName2(op);
        String godnessName2 = getGodnessName2(op);
        System.out.println(godnessName2);//默认goddness
    }

    //需求：获取一个男人心中男生的名字
    public String getGodnessName(Man man){
//        return man.getGoddness().getName();
        if (man != null){
            Goddness goddness = man.getGoddness();
            if (goddness != null){
                return goddness.getName();
            }
        }
        return "默认goddness";
    }

    public String getGodnessName2(Optional<NewMan> newMan){
        return newMan.orElse(new NewMan())
                .getGoddness()
                .orElse(new Goddness("默认goddness"))
                .getName();
    }
}
