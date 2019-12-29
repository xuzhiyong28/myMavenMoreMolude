package com.dxc.blockingQueue;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * https://www.cnblogs.com/geektcp/p/11410074.html
 * @author xuzhiyong
 * @createDate 2019-12-29-17:22
 * 1. 无界队列 + 优先级 + 扩容
 * 2. 优先级主要是元素实现Comparable接口
 * 3. 底层是用数组来存储的，因为是无界的，所以当数组满以后会自动扩容。
 * 4. 为什么数组能用来表示优先级呢 ？
 *      其实数据结构是二叉树小顶堆(父节点都小于左右节点)，可以用数组来存储，通过计算就可以得到一个节点的父节点或者左右节点
 *      leftNo = parentNo*2+1
 *      rightNo = parentNo*2+2
 *      parentNo = (nodeNo-1)/2
 * 5. 扩容时使用一个单独变量的CAS操作来控制只有一个线程进行扩容
 *
 */
public class PriorityBlockingQueueTest {

    public static void main(String[] args) throws InterruptedException {
        PriorityBlockingQueue<Person> pbq = new PriorityBlockingQueue<>();
        pbq.add(new Person(3,"person3"));
        pbq.add(new Person(2,"person2"));
        pbq.add(new Person(1,"person1"));
        pbq.add(new Person(4,"person4"));
        System.out.println("容器为：" + pbq);
        System.err.println("获取元素 " + pbq.take().getId());
        System.err.println("获取元素 " + pbq.take().getId());
        System.err.println("获取元素 " + pbq.take().getId());
        System.err.println("获取元素 " + pbq.take().getId());


    }

    static class Person implements Comparable<Person>{
        private int id;
        private String name;


        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public int compareTo(Person person) {
            return this.id > person.getId() ? 1 : ( this.id < person.getId() ? -1 :0);
        }

        public Person(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
