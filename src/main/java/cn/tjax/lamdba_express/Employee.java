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
}
