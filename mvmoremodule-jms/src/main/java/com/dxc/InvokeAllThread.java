package com.dxc;/**
 * Created by Administrator on 2018-10-11.
 */

import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * @author xuzhiyong
 * @createDate 2018-10-11-21:57
 */
public class InvokeAllThread {
    private static ExecutorService executorService = Executors.newFixedThreadPool(5);


    public static void main(String[] args) {
        try {
            InvokeAllThread it = new InvokeAllThread();
            it.getRankedTravelQuotes();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void getRankedTravelQuotes() throws InterruptedException, ExecutionException {
        List<QuoteTask> tasks = Lists.newArrayList();
        for (int i = 1; i <= 20; i++) {
            tasks.add(new QuoteTask(200, i));
        }
        List<Future<BigDecimal>> futures = executorService.invokeAll(tasks, 15, TimeUnit.SECONDS);
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        List<BigDecimal> totalPriceList = new ArrayList<BigDecimal>();
        Iterator<QuoteTask> taskIter = tasks.iterator();
        /*for (Future<BigDecimal> future : futures) {
            QuoteTask task = taskIter.next();
            try {
                totalPriceList.add(future.get());
            } catch (ExecutionException e) {
                totalPriceList.add(BigDecimal.valueOf(-1));
                System.out.println("任务执行异常,单价是" + task.price + "，人数是：" + task.num);
            } catch (CancellationException e) {
                totalPriceList.add(BigDecimal.ZERO);
                System.out.println("任务超时，取消计算,单价是" + task.price + "，人数是：" + task.num);
            }
        }
        for (BigDecimal bigDecimal : totalPriceList) {
            System.out.println(bigDecimal);
        }*/
        System.out.println("futureSize = " + futures.size());
        for (Future<BigDecimal> future : futures) {
            System.out.println(future.get());
        }


        executorService.shutdown();
    }


    private class QuoteTask implements Callable<BigDecimal> {
        public final double price;
        public final int num;

        public QuoteTask(double price, int num) {
            this.price = price;
            this.num = num;
        }




        @Override
        public BigDecimal call() throws Exception {
            Random r = new Random();
            long time = (r.nextInt(10) + 1) * 1000;
            Thread.sleep(time);
            BigDecimal d = BigDecimal.valueOf(price * num).setScale(2);
            System.out.println("耗时：" + time / 1000 + "s,单价是：" + price + ",人数是："
                    + num + "，总额是：" + d);
            return d;
        }
    }
}
