package cn.tjax.jdk8_new_time_api;

import org.junit.Test;

import java.time.*;

/**
 * @ClassName TestLocalDateTime
 * @Description TODO
 * @Author 洛上云雾
 * @Date 2020/6/29 17:00
 * @Version 1.0
 */
public class TestLocalDateTime {
    //1.LocalDate LocalTime LocalDateTime
    @Test
    public void test01(){
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt);//2020-06-29T17:02:20.311

        LocalDateTime ldt2 = LocalDateTime.of(2015, 10, 19, 13, 22, 33);
        System.out.println(ldt2);//2015-10-19T13:22:33

        System.out.println(ldt.plusYears(2));//2022-06-29T17:05:08.884

        System.out.println(ldt.minusMonths(2));//2020-04-29T17:05:44.907

        System.out.println(ldt.getYear());//2020
        System.out.println(ldt.getMonthValue());//6
        System.out.println(ldt.getDayOfMonth());//29
        System.out.println(ldt.getHour());//17
        System.out.println(ldt.getMinute());//7
        System.out.println(ldt.getSecond());//3
    }

    //2.时间戳 Instant: 以Unix 元年 1970年1月1日 00:00:00 到某个时间的毫秒值
    @Test
    public void test02(){
        //默认获取以UTC时区为主
        Instant ins1 = Instant.now();
        System.out.println(ins1);//2020-06-29T09:10:56.812Z

        OffsetDateTime odt = ins1.atOffset(ZoneOffset.ofHours(8));//东8区
        System.out.println(odt);//2020-06-29T17:13:20.246+08:00

        System.out.println(ins1.toEpochMilli());//1593422133208 毫秒时间戳

        Instant ins2 = Instant.ofEpochSecond(60);
        System.out.println(ins2);//1970-01-01T00:01:00Z
    }

    //3.
    // Duration 计算两个“时间”之间的间隔
    // Period 计算两个“日期”之间的间隔
    @Test
    public void test03() throws InterruptedException {
        Instant ins1 = Instant.now();
        Thread.sleep(1000);
        Instant ins2 = Instant.now();
        Duration duration = Duration.between(ins1, ins2);
        System.out.println(duration);//PT1S
        System.out.println(duration.toMillis());//1000

        System.out.println("------------------");

        LocalTime lt1 = LocalTime.now();
        Thread.sleep(1000);
        LocalTime lt2 = LocalTime.now();
        System.out.println(Duration.between(lt1,lt2).toMillis());//1000

        System.out.println("------------------");

        LocalDate ld1 = LocalDate.of(2015, 1, 1);
        LocalDate ld2 = LocalDate.now();
        Period period = Period.between(ld1, ld2);
        System.out.println(period.getYears());//5
        System.out.println(period.getMonths());//5
        System.out.println(period.getDays());//28
    }
}
