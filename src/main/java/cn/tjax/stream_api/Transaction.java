package cn.tjax.stream_api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName Transaction
 * @Description TODO
 * @Author 洛上云雾
 * @Date 2020/6/28 16:48
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    private Trader trader;
    private int year;
    private int value;

}
