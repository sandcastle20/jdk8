package cn.tjax.lamdba_express;

import lombok.*;

/**
 * @ClassName Employee
 * @Description TODO
 * @Author 洛上云雾
 * @Date 2020/6/23 10:19
 * @Version 1.0
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Data
public class Employee {
    private String name;
    private int age;
    private double salary;
    private Status status;

    public Employee(String name){
        this.name = name;
    }

    public Employee(String name,int age){
        this.name = name;
        this.age = age;
    }

    public Employee(String name,int age,double salary){
        this.name = name;
        this.age = age;
        this.salary = salary;
    }


}
