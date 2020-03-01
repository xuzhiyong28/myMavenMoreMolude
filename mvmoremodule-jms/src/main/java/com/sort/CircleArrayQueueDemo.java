package com.sort;

public class CircleArrayQueueDemo {


    /***
     * 环形数组
     * @param <T>
     */
    class CircleArrayQueue<T> {
        private int maxSize; //队列最大容量
        //front指向队列的第一个元素，也就是arr[front]就是队列的第一个元素 初始值 = 0
        private int front; // 指向队列头
        //rear指向队列的最后一个元素的后一个位置，因为希望空出一个空间作为约定 初始值 = 0
        private int rear; //指向队列尾
        private int arr[]; //数组 模拟队列

        public CircleArrayQueue(int size) {
            this.maxSize = size;
            this.arr = new int[size];
            this.front = 0;
            this.rear = 0;
        }


        /***
         * 判断是否是满的队列
         * @return
         */
        public boolean isFull() {
            return (rear + 1) % maxSize == front;
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
            arr[rear] = n;
            //将rear后移，这里考虑取模
            rear = (rear + 1) % maxSize;
        }


        /***
         * 出队列
         * @return
         */
        public int getQueue() {
            if (isEmpty()) {
                throw new RuntimeException("队列空");
            }
            //这里需要分析出front是指向队列的第一个元素
            // 先把front对应的值保留到一个临时变量
            // 将front后移，考虑取模
            int value = arr[front];
            front = (front + 1) % maxSize;
            return value;
        }


        public void showQueue() {
            if (isEmpty()) {
                throw new RuntimeException("队列空");
            }
            //思路 ： 从front开始遍历
            for (int i = front; i < front + size(); i++) {
                System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
            }
        }

        public int size() {
            return (rear + maxSize - front) % maxSize;
        }

    }
}
