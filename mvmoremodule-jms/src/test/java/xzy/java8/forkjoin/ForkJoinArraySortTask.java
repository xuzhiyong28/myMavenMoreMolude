package xzy.java8.forkjoin;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;
import java.util.concurrent.RecursiveTask;

/**
 * @author xuzhiyong
 * @createDate 2019-09-07-20:06
 * 使用forkjoin对数组排序
 * https://blog.csdn.net/yinwenjie/article/details/71915811
 */
public class ForkJoinArraySortTask extends RecursiveTask<int[]> {
    private int source[];

    public ForkJoinArraySortTask(int[] source) {
        this.source = source;
    }

    @Override
    protected int[] compute() {
        int sourceSize = source.length;
        // 如果条件成立，说明任务中要进行排序的集合还不够小
        if (sourceSize > 10) {
            int midIndex = sourceSize / 2;
            //拆分成两个子任务
            ForkJoinArraySortTask leftTask = new ForkJoinArraySortTask(Arrays.copyOf(source, midIndex));
            leftTask.fork();
            ForkJoinArraySortTask rightTask = new ForkJoinArraySortTask(Arrays.copyOfRange(source, midIndex, sourceSize));
            rightTask.fork();
            int result1[] = leftTask.join();
            int result2[] = rightTask.join();
            int mer[] = joinInts(result1, result2);
            return mer;
        } else {
            //排序
            return Arrays.stream(source).sorted().toArray();
        }
    }

    /***
     * 这个方法用于合并两个有序集合
     * @param result1
     * @param result2
     * @return
     */
    private int[] joinInts(int[] result1, int[] result2) {
        //现将两个数组合起来
        int[] array = ArrayUtils.addAll(result1,result2);
        //排序并去重
        return Arrays.stream(array).sorted().toArray();
    }
}
