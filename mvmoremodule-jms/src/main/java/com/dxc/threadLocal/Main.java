package com.dxc.threadLocal;

public class Main {
    private static ThreadLocal<ThreadModel> threadLocal = new ThreadLocal<>();


    public static void main(String agrs[]) throws InterruptedException {
        ThreadModel threadModel = new ThreadModel("xuzy");
        threadLocal.set(threadModel);
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                ThreadModel threadAModel = threadLocal.get();
                if(threadAModel == null){
                    threadLocal.set(threadModel);
                    threadAModel = threadLocal.get();
                }
                threadAModel.setName("gaoys");
            }
        });
        threadA.start();
        threadA.join();
        System.out.println(threadLocal.get().getName());
    }

}
