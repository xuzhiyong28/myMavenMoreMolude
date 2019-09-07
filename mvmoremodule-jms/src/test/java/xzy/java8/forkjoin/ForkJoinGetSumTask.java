package xzy.java8.forkjoin;

import java.util.concurrent.RecursiveTask;

/**
 * @author xuzhiyong
 * @createDate 2019-09-07-19:32
 */
public class ForkJoinGetSumTask extends RecursiveTask<Integer> {

    private static final int THRESHOLD = 20; //每个小任务 最多只累加20个数
    private Integer arry[];
    private int start;
    private int end;

    public ForkJoinGetSumTask(Integer[] arry, int start, int end) {
        this.arry = arry;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;
        if (end - start < THRESHOLD) {
            for (int i = start; i < end; i++) {
                sum += arry[i];
            }
            return sum;
        } else {
            int middle = (start + end) / 2;
            ForkJoinGetSumTask leftTask = new ForkJoinGetSumTask(arry, start, middle);
            ForkJoinGetSumTask rightTask = new ForkJoinGetSumTask(arry, middle, end);
            leftTask.fork();
            rightTask.fork();
            //把两个小任务累加的结果合并起来
            return leftTask.join() + rightTask.join();
        }
    }
}
