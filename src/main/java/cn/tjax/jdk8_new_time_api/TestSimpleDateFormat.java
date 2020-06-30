package cn.tjax.jdk8_new_time_api;


import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.*;

/**
 * @ClassName TestSimpleDateFormat
 * @Description TODO
 * @Author 洛上云雾
 * @Date 2020/6/29 16:19
 * @Version 1.0
 */
public class TestSimpleDateFormat {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");

        Callable<LocalDate> task = () -> LocalDate.parse("20161218",dtf);

        ExecutorService pool = Executors.newFixedThreadPool(10);

        List<Future<LocalDate>> results = new ArrayList<>();

        for (int i = 0; i < 10 ; i++) {
            results.add(pool.submit(task));
        }

        for (Future<LocalDate> future : results ) {
            System.out.println(future.get());
        }

        pool.shutdown();
    }
}
