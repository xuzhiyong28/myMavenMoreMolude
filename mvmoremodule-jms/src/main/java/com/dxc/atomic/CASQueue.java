package com.dxc.atomic;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/***
 * CAS实现无锁队列
 * @param <V>
 */
public class CASQueue<V> {

    private class Node<V> {
        private V value;
        private AtomicReference<Node<V>> next;

        public Node(V value, Node<V> node) {
            this.value = value;
            this.next = new AtomicReference<>(node);
        }
    }

    private AtomicReference<Node<V>> head; //头元素
    private AtomicReference<Node<V>> tail; //尾元素
    //队列大小
    private AtomicInteger queueSize = new AtomicInteger(0);

    public CASQueue() {
        //这个表示头元素，他不列入队列中
        Node<V> node = new Node<>(null, null);
        this.head = new AtomicReference<>(node);
        this.tail = new AtomicReference<>(node);
    }

    /***
     * 入队
     * @param value
     */
    public void enQueue(V value) {
        Node<V> newNode = new Node<>(value, null);
        Node<V> oldNode = null;
        while (true) {
            oldNode = tail.get();
            AtomicReference<Node<V>> nextAtomic = oldNode.next;
            if (nextAtomic.compareAndSet(null, newNode)) {
                break;
            }
        }
        //队列长度+1
        queueSize.getAndIncrement();
        tail.compareAndSet(oldNode, newNode);
    }


    /***
     * 出队
     * @return
     */
    public V outQueue() {
        while (true) {
            Node<V> oldNode = head.get(); // 第一个节点 value为空的Node
            Node<V> oldTail = tail.get();
            AtomicReference<Node<V>> firstNode = oldNode.next; //真正要出队的节点
            if (firstNode.get() == null) {
                return null;
            }
            //表示此时正好只入队一个元素，并且尾节点还没有更新
            if (oldNode == oldTail) {
                tail.compareAndSet(oldTail, oldTail.next.get());
                continue;
            }
            //头节点替换成出队节点，下次出队的时候就指向下一个节点
            if (head.compareAndSet(oldNode, firstNode.get())) {
                queueSize.getAndDecrement(); //数量-1
                return oldNode.next.get().value;
            }
        }
    }


    public int getQueueSize() {
        return queueSize.get();
    }


    public static void main(String args[]) {
        CASQueue<String> casQueue = new CASQueue<>();
        casQueue.enQueue("123");
        casQueue.enQueue("456");
        casQueue.enQueue("789");
        casQueue.enQueue("abc");
    }
}
