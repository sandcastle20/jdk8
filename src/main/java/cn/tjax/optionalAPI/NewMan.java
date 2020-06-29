package cn.tjax.optionalAPI;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

/**
 * @ClassName NewMan
 * @Description TODO
 * @Author 洛上云雾
 * @Date 2020/6/29 14:46
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewMan {

    //保证Optional有对象，创建一个null对象
    private Optional<Goddness> goddness = Optional.empty();

}
