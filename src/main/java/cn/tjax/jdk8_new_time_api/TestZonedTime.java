package cn.tjax.jdk8_new_time_api;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Set;

/**
 * @ClassName TestZonedTime
 * @Description TODO
 * @Author 洛上云雾
 * @Date 2020/6/30 9:18
 * @Version 1.0
 */
public class TestZonedTime {

    //ZonedDate、ZonedTime、ZonedDateTime
    @Test
    public void test01(){
        Set<String> set = ZoneId.getAvailableZoneIds();
        set.forEach(System.out::println);
    }

    @Test
    public void test02(){
        LocalDateTime ldt = LocalDateTime.now(ZoneId.of("Asia/Shanghai"));
        System.out.println(ldt);//2020-06-30T09:22:17.099

        LocalDateTime ldt2 = LocalDateTime.now(ZoneId.of("Asia/Shanghai"));
        ZonedDateTime zdt = ldt2.atZone(ZoneId.of("Asia/Shanghai"));
        System.out.println(zdt);//2020-06-30T09:24:27.853+08:00[Asia/Shanghai]
    }
}
