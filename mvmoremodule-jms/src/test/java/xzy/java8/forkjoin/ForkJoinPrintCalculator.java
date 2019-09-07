package xzy.java8.forkjoin;

import java.util.concurrent.RecursiveAction;

/**
 * @author xuzhiyong
 * @createDate 2019-09-07-19:14
 * 简单低打印1~300的数值
 * 继承RecursiveAction 表示不用返回值的ForkJoin任务
 */
public class ForkJoinPrintCalculator extends RecursiveAction {
    private static final int THRESHOLD = 10; //一个任务最大打印10个，forkJoin会充分利用CPU将任务细分成你指定的最小任务
    private int start;
    private int end;

    public ForkJoinPrintCalculator(int start, int end) {
        this.start = start;
        this.end = end;
    }


    @Override
    protected void compute() {
        if (end - start < THRESHOLD) {
            for (int i = start; i < end; i++) {
                System.out.println(Thread.currentThread().getName() + "的i值：" + i);
            }
        } else {
            int middle = (start + end) / 2;
            ForkJoinPrintCalculator leftTask = new ForkJoinPrintCalculator(start, middle);
            ForkJoinPrintCalculator rightTask = new ForkJoinPrintCalculator(middle, end);
            //并行执行两个任务
            leftTask.fork();
            rightTask.fork();
        }
    }
}
