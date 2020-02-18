package com.sort;

public class ArrayQueueDemo {
    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue(10);
        queue.addQueue(1);
        queue.addQueue(1);
        queue.addQueue(1);
        queue.addQueue(1);
        queue.addQueue(1);
        queue.addQueue(1);
        queue.addQueue(1);
        queue.addQueue(1);
        queue.addQueue(1);
        queue.addQueue(1);
        queue.addQueue(1);
        queue.addQueue(1);
    }
}

/***
 * 使用数组做队列
 * 缺点:只能做一次性队列，队列满就满了，即使有数据出队列也不能再继续入队列
 * @param
 */
class ArrayQueue {
    private int maxSize; //队列最大容量
    private int front; // 指向队列头
    private int rear; //指向队列尾
    private int arr[]; //数组 模拟队列

    public ArrayQueue(int size) {
        this.maxSize = size;
        this.arr = new int[size];
        this.front = -1; //指向队列头部，分析出front指向队列的前一个位置
        this.rear = -1; //执行队列尾部，指向队列的尾部的数据(就是最后一个数据)
    }


    /***
     * 判断是否是满的队列
     * @return
     */
    public boolean isFull() {
        return rear == maxSize - 1;
    }

    /***
     * 判断是否为空
     * @return
     */
    public boolean isEmpty() {
        return rear == front;
    }

    /***
     * 添加数据
     * @param n
     */
    public void addQueue(int n) {
        if (isFull()) {
            throw new RuntimeException("队列满");
        }
        rear++;
        arr[rear] = n;
    }


    /***
     * 出队列
     * @return
     */
    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列空");
        }
        front++;
        return arr[front];
    }
}
