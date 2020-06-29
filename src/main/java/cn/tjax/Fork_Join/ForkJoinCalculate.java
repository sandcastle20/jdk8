package cn.tjax.Fork_Join;

import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

/**
 * @ClassName ForkJoinCalculate
 * @Description TODO
 * @Author 洛上云雾
 * @Date 2020/6/29 13:14
 * @Version 1.0
 */
public class ForkJoinCalculate extends RecursiveTask<Long> {

    //1.序列化
    private static final long serialVersionUID = 1L;

    private long start;
    private long end;

    public ForkJoinCalculate(long start,long end){
        this.start = start;
        this.end = end;
    }

    //2.定义（算法的）临界值
    private static final  long THRESHOLD = 10000;

    @Override
    protected Long compute() {
        long length = end - start;
        if (length <= THRESHOLD){
            long sum = 0;
            for (long i = start; i <=end ; i++) {
                sum += i;
            }
            return sum;
        }else { //拆子任务
            long middle = (start + end) / 2;
            ForkJoinCalculate left = new ForkJoinCalculate(start, middle);
            left.fork();//拆分子任务，压入线程队列

            ForkJoinCalculate right = new ForkJoinCalculate(middle + 1, end);
            right.fork();

            return left.join() + right.join();
        }
    }
}
