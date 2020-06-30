package cn.tjax.jdk8_new_time_api;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @ClassName TestDateTimeFormatter
 * @Description TODO
 * @Author 洛上云雾
 * @Date 2020/6/29 18:02
 * @Version 1.0
 */
public class TestDateTimeFormatter {

    //DateTimeFormatter:格式化时间/日期
    @Test
    public void test6(){
        DateTimeFormatter dtf = DateTimeFormatter.ISO_DATE_TIME;
        DateTimeFormatter dtf2 = DateTimeFormatter.ISO_DATE;
        LocalDateTime ldt = LocalDateTime.now();

        String strDate = ldt.format(dtf);
        String strDate2 = ldt.format(dtf2);
        System.out.println(strDate);//2020-06-29T18:04:34.317
        System.out.println(strDate2);//2020-06-29

        System.out.println("-----------------------");
        DateTimeFormatter dtf3 = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss");
        System.out.println(dtf3.format(ldt));//2020年06月29日 18:08:02

        LocalDateTime time = ldt.parse("2020年06月29日 18:08:02", dtf3);
        System.out.println(time);//2020-06-29T18:08:02
    }
}
