package xzy.leetCode.offer;

import xzy.leetCode.model.ListNode;

import java.util.Stack;

/***
 * 输入一个链表的头结点，从尾到头反过来打印出每个结点的值
 */
public class PrintListReverse {
    public static void main(String args[]){
        ListNode<Integer> node = initNode();
        printListReverse2(node);

    }

    /***
     * 初始化一个单链表
     * @return
     */
    public static ListNode<Integer> initNode(){
        ListNode<Integer> node1 = new ListNode<>(1);
        ListNode<Integer> node2 = new ListNode<>(2);
        ListNode<Integer> node3 = new ListNode<>(3);
        node1.next = node2;
        node2.next = node3;
        return  node1;
    }


    /***
     * 方式 : 通过栈
     * 从尾到头反过来打印出每个结点的值
     * @param headNode
     */
    public static void printListReverse(ListNode<Integer> headNode){
        Stack<ListNode<Integer>> stack = new Stack<>();
        //从头节点开始遍历并加入到栈中
        while (headNode != null){
            stack.push(headNode);
            headNode = headNode.next;
        }
        //用于栈是先进后出的，所以取出来就是翻转后的值
        while (!stack.isEmpty()){
            System.out.println(stack.pop().value);
        }
    }

    /***
     * 方式 : 递归
     * 从尾到头反过来打印出每个结点的值
     * @param headNode
     */
    public static void printListReverse2(ListNode<Integer> headNode){
        if(headNode != null){
            if(headNode.next != null){
                printListReverse2(headNode.next);
            }
        }
        System.out.println(headNode.value);
    }


}
