package com.dxc.lock;/**
 * Created by Administrator on 2018-06-08.
 */

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xuzhiyong
 * @createDate 2018-06-08-16:52
 */
public class ConditionTest {
    private int depotSize; //仓库目前大小
    private Lock lock;     //独占锁
    private int capaity;   //仓库容量
    private Condition fullCondition;
    private Condition emptyCondition;

    public ConditionTest(){
        this.depotSize = 0;
        this.lock = new ReentrantLock(); //可重入锁
        this.capaity = 15; //仓库容量为15
        this.fullCondition = lock.newCondition();
        this.emptyCondition = lock.newCondition();
    }

    public static void main(String[] agrs){
        ConditionTest conditionTest = new ConditionTest();
        Thread thread1 = new Thread(new Producer(conditionTest,10));
        Thread thread2 = new Thread(new Customer(conditionTest,5));
        Thread thread3 = new Thread(new Producer(conditionTest,1));
        Thread thread4 = new Thread(new Producer(conditionTest,5));
        Thread thread5 = new Thread(new Customer(conditionTest,20));
        Thread thread6 = new Thread(new Customer(conditionTest,1));
        Thread thread7 = new Thread(new Customer(conditionTest,5));
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        thread6.start();
        thread7.start();
    }

    /****
     * 商品入库
     * @param value
     */
    public void put(int value){
        try{
            lock.lock();
            int left = value; //需要入仓库的数量
            while(left > 0){
                while (depotSize >= capaity){
                    fullCondition.await(); // 当容量大于等于最大容量时就等待
                }
                int inc = depotSize + left > capaity ? (capaity - depotSize) : left; //判断当前要加入的数量是否大于仓库容量，若大于则添加的数量为允许的容量，否则全部加入
                depotSize = depotSize + inc;
                left = left - inc;
                System.out.println(Thread.currentThread().getName() + "----要入库数量: " + value +";;实际入库数量：" + inc + ";;仓库货物数量：" + depotSize + ";;没有入库数量：" + left);
                //通知消费者可以消费了
                emptyCondition.signal();
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    /***
     * 商品出库
     * @param value
     */
    public void get(int value){
        try{
            lock.lock();
            int left = value;
            while(left > 0){
                while (depotSize <= 0){
                    emptyCondition.await(); //当容量为空时停止消费
                }
                int dec = depotSize < left ? depotSize : left;
                depotSize = depotSize - dec;
                left = left - dec;
                System.out.println(Thread.currentThread().getName() + "----要消费的数量：" + value +";;实际消费的数量: " + dec + ";;仓库现存数量：" + depotSize + ";;有多少件商品没有消费：" + left);

                //通知生产者生产
                emptyCondition.signal();
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }


    static class Producer implements Runnable{
        private ConditionTest conditionTest;
        private int value;
        public Producer(ConditionTest conditionTest , int value){
            this.conditionTest = conditionTest;
            this.value = value;
        }
        @Override
        public void run() {
            conditionTest.put(value);
        }
    }

    static class Customer implements Runnable{
        private ConditionTest conditionTest;
        private int value;
        public Customer(ConditionTest conditionTest , int value){
            this.conditionTest = conditionTest;
            this.value = value;
        }
        @Override
        public void run() {
            conditionTest.get(value);
        }
    }


}
