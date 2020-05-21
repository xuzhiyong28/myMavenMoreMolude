package com.dxc.threadLocal;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.TtlRunnable;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UuidHelper {
    private static ThreadLocal<String> threadLocal = new TransmittableThreadLocal();
    private final static ExecutorService logService = Executors.newFixedThreadPool(2);
    public static String get(){
        String uuid = threadLocal.get();
        if (uuid == null){
            uuid = UUID.randomUUID().toString();
            threadLocal.set(uuid);
        }
        return uuid;
    }

    public static void remove(){
        String uuid = get();
        threadLocal.remove();
    }

    public static void addQueue(String content){
        //logService.execute(TtlRunnable.get(new LoggerThread(UuidHelper.get() + "-" + content)));
        logService.execute(new LoggerThread(UuidHelper.get() + "-" + content));
    }

    static class LoggerThread implements Runnable{
        String uuid;
        public LoggerThread(String uuid){
            this.uuid = uuid;
        }
        @Override
        public void run() {
            System.out.println(uuid);
        }
    }

}
