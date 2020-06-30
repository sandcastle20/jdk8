package cn.tjax.jdk8_new_time_api;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName DateFormatThreadLocal
 * @Description TODO
 * @Author 洛上云雾
 * @Date 2020/6/29 16:34
 * @Version 1.0
 */
public class DateFormatThreadLocal {

    private static final ThreadLocal<DateFormat> df = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyyMMdd"));

    public static Date convert(String soure) throws ParseException {
        return df.get().parse(soure);
    }
}
