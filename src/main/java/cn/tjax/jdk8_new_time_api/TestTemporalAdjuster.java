package cn.tjax.jdk8_new_time_api;

import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

/**
 * @ClassName TestTemporalAdjuster
 * @Description TODO
 * @Author 洛上云雾
 * @Date 2020/6/29 17:45
 * @Version 1.0
 */
public class TestTemporalAdjuster {

    //TemporalAdjuster : 时间校正器
    //TemporalAdjusters:实现TemporalAdjuster接口的工具类
    @Test
    public void test01(){
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt);//2020-06-29T17:59:02.151

        LocalDateTime ldt2 = ldt.withDayOfMonth(10);//指定月中的天是10号
        System.out.println(ldt2);//2020-06-10T17:59:02.151

        LocalDateTime ldt3 = ldt.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
        System.out.println(ldt3);//2020-07-05T17:59:02.151

        //自定义：下一个工作日
        LocalDateTime ldt5 = ldt.with((l) -> {
            LocalDateTime ldt4 = (LocalDateTime) l;
            DayOfWeek dayOfWeek = ldt4.getDayOfWeek();
            if (dayOfWeek.equals(DayOfWeek.FRIDAY)) {
                return ldt4.plusYears(3);
            } else if (dayOfWeek.equals(DayOfWeek.SATURDAY)) {
                return ldt4.plusDays(2);
            } else {
                return ldt4.plusDays(1);
            }
        });
        System.out.println(ldt5);//2020-06-30T17:59:02.151
    }
}
