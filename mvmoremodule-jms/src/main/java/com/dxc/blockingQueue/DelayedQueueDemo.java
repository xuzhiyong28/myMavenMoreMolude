package com.dxc.blockingQueue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayedQueueDemo {

    public static void main(String[] args) throws InterruptedException {
        Task item1 = new Task("item1", 5, TimeUnit.SECONDS);
        Task item2 = new Task("item2", 10, TimeUnit.SECONDS);
        Task item3 = new Task("item3", 15, TimeUnit.SECONDS);
        DelayQueue<Task> queue = new DelayQueue<>();
        queue.put(item1);
        queue.put(item2);
        queue.put(item3);
        System.out.println("begin time:" + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        //TimeUnit.SECONDS.sleep(20);  // 延迟的时间是从put的时候开始算的
        for (int i = 0; i < 3; i++) {
            Task take = queue.take();
            System.out.format("name:{%s}, time:{%s}\n", take.name, LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
        }
    }
}


class Task implements Delayed {

    private long time;
    String name;

    public Task(String name, long time, TimeUnit unit) {
        this.name = name;
        this.time = System.currentTimeMillis() + (time > 0 ? unit.toMillis(time) : 0);
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return time - System.currentTimeMillis();
    }


    @Override
    public int compareTo(Delayed o) {
        Task item = (Task) o;
        long diff = this.time - item.time;
        if (diff <= 0) {// 改成>=会造成问题
            return -1;
        } else {
            return 1;
        }
    }

    @Override
    public String toString() {
        return "Item{" +
                "time=" + time +
                ", name='" + name + '\'' +
                '}';
    }

}

