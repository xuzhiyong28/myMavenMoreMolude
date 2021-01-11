package xzy.leetCode.offer;

import org.junit.Test;
import xzy.leetCode.model.ListNode;
import xzy.leetCode.model.TreeNode;

import java.util.*;


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
     * 前序遍历 preorder = [3,9,20,15,7] 前序遍历的前指的是根在前，所以是根左右
     * 中序遍历 inorder = [9,3,15,20,7]  中序遍历的中指的是根在中，所以是左根右
     */
    @Test
    public void test2() {
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};
        buildTree(preorder, inorder);
    }

    /***
     * 解题思路
     * 前序遍历的第一个节点是根节点，只要找到根节点在中序遍历中的位置，在根节点之前被访问的节点都位于左子树，在根节点之后被访问的节点都位于右子树，由此可知左子树和右子树分别有多少个节点。
     *int[] preorder = {3, 9, 20, 15, 7};
     *int[] inorder = {9, 3, 15, 20, 7};
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        Map<Integer, Integer> indexMap = new HashMap<Integer, Integer>();
        int length = preorder.length;
        for (int i = 0; i < length; i++) {
            indexMap.put(inorder[i], i);
        }
        TreeNode root = buildTree(preorder, 0, length - 1, inorder, 0, length - 1, indexMap);
        return root;
    }

    /***
     *
     * @param preorder 前序数组
     * @param preorderStart 前序数组开始节点
     * @param preorderEnd 前序数组尾节点
     * @param inorder 后序数组
     * @param inorderStart 后序数组开始节点
     * @param inorderEnd 后序数组尾节点
     * @param indexMap
     *int[] preorder = {3, 9, 20, 15, 7};
     *int[] inorder = {9, 3, 15, 20, 7};
     * @return
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     */
    public TreeNode buildTree(int[] preorder, int preorderStart, int preorderEnd, int[] inorder, int inorderStart, int inorderEnd, Map<Integer, Integer> indexMap) {
        if (preorderStart > preorderEnd) {
            return null;
        }
        int rootVal = preorder[preorderStart];
        TreeNode root = new TreeNode(rootVal);
        if (preorderStart == preorderEnd) {
            return root;
        } else {
            int rootIndex = indexMap.get(rootVal);
            int leftNodes = rootIndex - inorderStart, rightNodes = inorderEnd - rootIndex;
            TreeNode leftSubtree = buildTree(preorder, preorderStart + 1, preorderStart + leftNodes, inorder, inorderStart, rootIndex - 1, indexMap);
            TreeNode rightSubtree = buildTree(preorder, preorderEnd - rightNodes + 1, preorderEnd, inorder, rootIndex + 1, inorderEnd, indexMap);
            root.left = leftSubtree;
            root.right = rightSubtree;
            return root;
        }
    }


    /***
     * 替换空格
     */
    @Test
    public void test3() {
        System.out.println(replaceSpace("xu zhi yong"));
    }

    /***
     * 替换空格
     * 1.获取s的长度
     * 2.创建字符数组 array，其长度为 length * 3
     * 3.初始化 size 为 0，size 表示替换后的字符串的长度
     * 从左到右遍历字符串 s，如果出现空格要替换就替换成 % 2 0 三个字符， 且 size+3，如果不是空格就不用替换但是要+1
     * @param s
     * @return
     */
    public String replaceSpace(String s) {
        int length = s.length();
        char[] array = new char[length * 3];
        int size = 0;
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if (c == ' ') {
                array[size++] = '%';
                array[size++] = '2';
                array[size++] = '0';
            } else {
                array[size++] = c;
            }
        }
        String newStr = new String(array, 0, size);
        return newStr;
    }

    /***
     * 两个有序数组，数组中存在重复数字，合并成一个有序数组，去除重复数字
     */
    @Test
    public void test4() {
        int[] a = new int[]{1, 3, 5, 5, 6, 6, 6, 6, 7};
        int[] b = new int[]{3, 5, 6, 8};
        int[] result = hbArray(a, b);
        Arrays.stream(result).forEach(System.out::println);
    }

    /***
     * 第一个数组的第一个元素和第二个数组的第一个元素比较。小的插入到result数组中。若有重复的元素，则取其中一个。
     * 这个时候，相当于从两个数组中都取了一个元素，所以两个数组的下标都要后移，result的下标自然也要后移
     * @param a {1, 3, 5 , 5, 6, 7}
     * @param b {3, 5, 6, 8}
     * @return
     */
    public static int[] hbArray(int[] a, int[] b) {
        int[] result = new int[a.length + b.length];
        int i = 0, j = 0, k = 0;
        int preInt = a[0] < b[0] ? a[0] : b[0];
        while (i < a.length && j < b.length) {
            if (a[i] < b[j]) {
                result[k] = a[i];
                i++;
                k++;
            } else if (a[i] == b[j]) {
                //若二者相等这一步就是去重复
                result[k] = a[i];
                k++;
                i++;
                j++;
            } else {
                result[k] = b[j];
                k++;
                j++;
            }
            if ((k - 1 != 0) && preInt == result[k - 1]) {
                k--;
            }
            preInt = result[k - 1];
        }
        //由于上面的循环完后只是一个数组遍历完了，需要遍历另外一个数组。
        while (i < a.length) {
            result[k] = a[i];
            k++;
            i++;
        }
        while (j < b.length) {
            result[k] = b[j];
            k++;
            j++;
        }
        return result;
    }


    /***
     * 用两个栈实现队列
     */
    @Test
    public void test5(){
        CQueue cQueue = new CQueue();
        cQueue.appendTail(1);
        cQueue.appendTail(2);
        cQueue.appendTail(3);
        System.out.println(cQueue.deleteHead());
        System.out.println(cQueue.deleteHead());
        System.out.println(cQueue.deleteHead());
    }


    /***
     * 用两个栈实现队列
     */
    static class CQueue{
        //LinkedList可以作为栈来使用
        LinkedList<Integer> A,B;
        public CQueue(){
            A = new LinkedList<>();
            B = new LinkedList<>();
        }
        //添加到尾部
        public void appendTail(int value) {
            A.addLast(value);
        }
        //从头部删除
        public int deleteHead() {
            if(!B.isEmpty()) return B.removeLast();
            if(A.isEmpty()) return -1;
            while (!A.isEmpty()){
                B.addLast(A.removeLast());
            }
            return B.removeLast();
        }
    }


    @Test
    public void test6(){
        System.out.println(fib(10));
    }


    /***
     * 斐波那契优化 ： https://leetcode-cn.com/problems/fei-bo-na-qi-shu-lie-lcof/solution/di-gui-he-fei-di-gui-liang-chong-fang-shi-du-ji-ba/
     * @param n
     * @return
     * 通过定义个map来存储已经计算过的值，减少重复计算
     */
    public static int fib(int n){
        return fib(n,new HashMap());
    }
    public static int fib(int n, Map<Integer, Integer> map){
        if(n < 2)
            return n;
        if(map.containsKey(n)){
            return map.get(n);
        }
        int first = fib(n - 1, map) ;
        map.put(n -1 , first);
        int second = fib(n - 2, map);
        map.put(n - 2, second);
        int res = (first + second);
        map.put(n, res);
        return res;
    }










}
