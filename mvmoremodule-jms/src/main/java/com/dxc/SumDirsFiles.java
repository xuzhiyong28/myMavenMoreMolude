package com.dxc;/**
 * Created by Administrator on 2018-05-10.
 */

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @author xuzhiyong
 * @createDate 2018-05-10-19:27
 * Fork/join框架
 * 遍历指定目录（含子目录）统计文件个数
 */
public class SumDirsFiles extends RecursiveTask<Integer> {
    private File path;

    public SumDirsFiles(File path){
        this.path = path;
    }

    @Override
    protected Integer compute() {
        int count = 0 ;
        int dirCount = 0;
        List<SumDirsFiles> subTasks = new ArrayList<SumDirsFiles>();
        File[] files = path.listFiles(); //获取当前目录下的文件（包括文件夹）
        if(files != null){
            for(File file : files){
                if(file.isDirectory()){
                    //判断是否是文件夹，如果是的话交给fork/join框架
                    subTasks.add(new SumDirsFiles(file));
                    dirCount ++ ; //统计目录个数
                }else{
                    count ++ ; // 遇到文件，文件个数+1
                }
            }
            System.out.println("目录:" + path.getAbsolutePath() +"包含目录个数："+dirCount+",文件个数："+count);
            if (!subTasks.isEmpty()) {
                for (SumDirsFiles subTask : invokeAll(subTasks)) {
                    count = count + subTask.join();
                }
            }
        }
        return count;
    }

    public static void main(String agrs[]) {
        try {
            ForkJoinPool forkJoinPool = new ForkJoinPool(); //用一个ForkJoinPool实例调度总任务
            SumDirsFiles task = new SumDirsFiles(new File("E:/"));
            forkJoinPool.invoke(task);
            System.out.println("Task is Running......");
            System.out.println("File counts =" + task.join());
            System.out.println("Task end");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
