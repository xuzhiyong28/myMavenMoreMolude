package com.dxc.atomic;/**
 * Created by Administrator on 2018-06-08.
 */

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author xuzhiyong
 * @createDate 2018-06-08-15:06
 * AtomicReference :如果使用原子性对象引用，在多线程情况下进行对象的更新可以确保一致性
 */
public class AtomicReferenceTest {
    private static Person person;
    private static AtomicReference<Person> atomicReference = null;

    public static void main(String[] agrs) throws InterruptedException {
        person = new Person("Tom", 18);
        atomicReference = new AtomicReference<Person>(person);
        System.out.println("Atomic Person is " + atomicReference.get().toString());
        Thread thread1 = new Thread(new Task1());
        Thread thread2 = new Thread(new Task2());
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("Now Atomic Person is " + atomicReference.get().toString());
    }

    static class Task1 implements Runnable {
        @Override
        public void run() {
            atomicReference.getAndSet(new Person("Tom1", atomicReference.get().getAge() + 1));
            System.out.println("Thread1 Atomic References " + atomicReference.get().toString());
        }
    }

    static class Task2 implements Runnable {
        @Override
        public void run() {
            atomicReference.getAndSet(new Person("Tom2", atomicReference.get().getAge() + 1));
            System.out.println("Thread2 Atomic References " + atomicReference.get().toString());
        }
    }

}
