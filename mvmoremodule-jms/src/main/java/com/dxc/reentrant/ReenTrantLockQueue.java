package com.dxc.reentrant;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/****
 * 使用ReenTrantLock实现队列
 * @param <T>
 */
public class ReenTrantLockQueue<T> {
    int size;
    ReentrantLock reentrantLock = new ReentrantLock();
    LinkedList<T> linkedList = new LinkedList<T>();
    Condition conditionFull = reentrantLock.newCondition();
    Condition conditionEmpty = reentrantLock.newCondition();

    public ReenTrantLockQueue(int size){
        this.size = size;
    }
    /***
     * 入队
     * @param e
     */
    public void enqueue(T e){
        reentrantLock.lock();
        try{
            while(linkedList.size() == size){
                conditionFull.await();
            }
            linkedList.add(e); //入队，加入链表对尾
            System.out.println("入队 ：" +  e);
            conditionEmpty.signal(); //通知可以出队
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            reentrantLock.unlock();
        }
    }

    public T dequeue() throws InterruptedException {
        T e;
        reentrantLock.lock();
        try{
            while (linkedList.size() == 0){
                conditionEmpty.await();
            }
            e = linkedList.removeFirst();
            System.out.println("出队 ：" + e);
            conditionFull.signal(); //通知可以入队
            return e;
        }finally {
            reentrantLock.unlock();
        }
    }


    public static void main(String[] args){
        final ReenTrantLockQueue<String> reenTrantLockQueue = new ReenTrantLockQueue<String>(1);
        //=========================入队线程======================
        for(int i = 0 ; i < 10 ; i++){
            final int finalI = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    reenTrantLockQueue.enqueue(String.valueOf(finalI));
                }
            }).start();
        }
        //========================出队线程=======================
        for(int i = 0 ; i < 10 ; i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        reenTrantLockQueue.dequeue();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

}
