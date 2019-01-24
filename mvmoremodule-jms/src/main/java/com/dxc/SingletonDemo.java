package com.dxc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

public class SingletonDemo {
    private static AtomicReference<SingletonDemo> singletonDemoAtomicReference = new AtomicReference<SingletonDemo>();
    public static SingletonDemo getInstanceByCAS(){
        for(;;){
            SingletonDemo singletonDemo = singletonDemoAtomicReference.get();
            if(singletonDemo != null){
                return singletonDemo;
            }
            singletonDemo = new SingletonDemo();
            if(singletonDemoAtomicReference.compareAndSet(null,singletonDemo)){
                return singletonDemo;
            }
        }
    }

    public static void main(String[] args){
        ExecutorService executorService = Executors.newFixedThreadPool(40);
        for(int i = 0 ; i < 10000 ; i++){
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    SingletonDemo singletonDemo = SingletonDemo.getInstanceByCAS();
                }
            });
        }
        executorService.shutdown();

    }
}
