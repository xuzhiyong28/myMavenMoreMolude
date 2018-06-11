package com.dxc.semaphore;/**
 * Created by Administrator on 2018-06-09.
 */

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xuzhiyong
 * @createDate 2018-06-09-10:16
 * 资源的多副本的并发访问控制
 */
public class SemaphoreTest {
    private final Semaphore semaphore;
    private boolean resourceArray[] ;
    private ReentrantLock lock;

    public static void main(String[] args){
        SemaphoreTest semaphoreTest = new SemaphoreTest();
        Thread[] threads = new Thread[100];
        for(int i = 0 ; i < threads.length ; i++){
            threads[i] = new Thread(new ResourceUser(semaphoreTest,"用户_" + i));
        }
        for(int i = 0 ; i < threads.length ; i++){
            threads[i].start();
        }

    }



    public SemaphoreTest() {
        this.semaphore = new Semaphore(10,true);//控制10个共享资源的使用，使用先进先出的公平模式进行共享;公平模式的信号量，先来的先获得信号量
        this.resourceArray = new boolean[10];//存放厕所状态
        this.lock = new ReentrantLock(true); //公平锁
        for(int i = 0 ; i < 10 ;i++)
            this.resourceArray[i] = true;
    }




    //使用资源
    public void useResource(String userName){
        try {
            semaphore.acquire(); //获得信号量
            int id = getResourceId(); //获取资源
            System.out.print("userName:"+userName+"正在使用资源，资源id:"+id+"\n");
            TimeUnit.SECONDS.sleep(1); //模拟使用资源
            resourceArray[id] = true; //使用完了释放
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            semaphore.release(); //是否信号量 计数器+1 释放信号量
        }
    }

    //获取到对应的资源
    public int getResourceId(){
        int id = -1;
        try{
            lock.lock();
            //遍历循环还没有使用过的坑并返回
            for(int i = 0 ; i < 10 ; i++){
                if(resourceArray[i]){
                    resourceArray[i] = false;
                    id = i;
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
        return id;
    }

    static class ResourceUser implements Runnable{
        private SemaphoreTest semaphoreTest;
        private String userName;
        public ResourceUser (SemaphoreTest semaphoreTest,String userName){
            this.semaphoreTest = semaphoreTest;
            this.userName = userName;
        }
        @Override
        public void run() {
            System.out.print("userName:"+userName+"准备使用资源...\n");
            semaphoreTest.useResource(userName);
            System.out.print("userName:"+userName+"使用资源完毕...\n");
        }
    }


}
