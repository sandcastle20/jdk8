package cn.tjax.lamdba_express;

/**
 * @ClassName FilterEmployeeByAge
 * @Description TODO
 * @Author 洛上云雾
 * @Date 2020/6/23 10:52
 * @Version 1.0
 */
public class FilterEmployeeByAge implements MyPredicate<Employee> {

    @Override
    public boolean test(Employee employee) {
        return employee.getAge()>= 35;
    }

}
