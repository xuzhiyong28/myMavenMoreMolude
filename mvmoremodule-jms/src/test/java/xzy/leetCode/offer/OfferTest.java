package xzy.leetCode.offer;

import org.junit.Test;
import xzy.leetCode.model.ListNode;
import xzy.leetCode.model.TreeNode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;


public class OfferTest {
    /***
     * 初始化一个单链表
     * @return
     */
    public static ListNode<Integer> initNode() {
        ListNode<Integer> node1 = new ListNode<>(1);
        ListNode<Integer> node2 = new ListNode<>(2);
        ListNode<Integer> node3 = new ListNode<>(3);
        node1.next = node2;
        node2.next = node3;
        return node1;
    }

    /***
     * 从尾到头反过来打印出每个结点的值
     */
    @Test
    public void test0() {
        ListNode<Integer> node = initNode();
        printListReverse2(node);
    }


    /***
     * 方式 : 通过栈
     * 从尾到头反过来打印出每个结点的值
     * @param headNode
     */
    public static void printListReverse(ListNode<Integer> headNode) {
        Stack<ListNode<Integer>> stack = new Stack<>();
        //从头节点开始遍历并加入到栈中
        while (headNode != null) {
            stack.push(headNode);
            headNode = headNode.next;
        }
        //用于栈是先进后出的，所以取出来就是翻转后的值
        while (!stack.isEmpty()) {
            System.out.println(stack.pop().value);
        }
    }

    /***
     * 方式 : 递归
     * 从尾到头反过来打印出每个结点的值
     * 因为栈的本质就是一个递归，所以递归也可以实现链表倒着输出，即每访问到一个结点的时候，先递归输出它后面的结点，再输出该结点自身，这样链表的结果就反过来了
     * @param headNode
     */
    public static void printListReverse2(ListNode<Integer> headNode) {
        if (headNode != null) {
            if (headNode.next != null) {
                printListReverse2(headNode.next);
            }
        }
        System.out.println(headNode.value);
    }

    /***
     * 反转链表后打印
     * 从尾到头反过来打印出每个结点的值
     * @param headNode
     */
    public static void printListReverse3(ListNode<Integer> headNode) {
        ListNode<Integer> pre = null;
        while (headNode != null) {
            ListNode<Integer> next = headNode.next;
            headNode.next = pre;
            pre = headNode;
            headNode = next;
        }
        //while结束后链表就被翻转了，只要遍历就可以了
    }


    /***
     * 输入某二叉树的前序遍历和中序遍历的结果，请重建该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
     * 前序遍历 preorder = [3,9,20,15,7]
     * 中序遍历 inorder = [9,3,15,20,7]
     */
    @Test
    public void test2() {

    }

    /***
     * 解题思路
     * 前序遍历的第一个节点是根节点，只要找到根节点在中序遍历中的位置，在根节点之前被访问的节点都位于左子树，在根节点之后被访问的节点都位于右子树，由此可知左子树和右子树分别有多少个节点。
     *
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        Map<Integer, Integer> indexMap = new HashMap<>();
        int length = preorder.length;
        for (int i = 0; i < length; i++) {
            indexMap.put(inorder[i], i);
        }

        return null;
    }


}
