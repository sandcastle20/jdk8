package cn.tjax.Fork_Join;

import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

/**
 * @ClassName TestForkJoin
 * @Description TODO
 * @Author 洛上云雾
 * @Date 2020/6/29 13:31
 * @Version 1.0
 */
public class TestForkJoin {

    //需要线程池的支持
    @Test
    public void test1(){
        Instant start = Instant.now();

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Long> task = new ForkJoinCalculate(0,10000000000L);
        Long sum = forkJoinPool.invoke(task);
        System.out.println(sum);

        Instant end = Instant.now();
        System.out.println("耗费时间为："+Duration.between(start,end).toMillis());//耗费时间为：≈2917ms
    }

    /*
     * 普通for循环
     **/
    @Test
    public void test2(){
        Instant start = Instant.now();

        long sum = 0L;
        for (long i = 0; i <= 10000000000L; i++) {
            sum += i;
        }
        System.out.println(sum);

        Instant end = Instant.now();
        System.out.println("耗费时间为："+Duration.between(start,end).toMillis());//耗费时间为：≈4100ms
    }

    /**
     * java8 并行流
     **/
    @Test
    public void test3(){
        Instant start = Instant.now();

        LongStream.rangeClosed(0,10000000000L)
                .parallel()
                .reduce(0,Long::sum);

        Instant end = Instant.now();
        System.out.println("耗费时间为："+Duration.between(start,end).toMillis());//耗费时间为：≈1711ms

    }

}
