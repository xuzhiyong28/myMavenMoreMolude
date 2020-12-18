package xzy.leetCode.linkedList;

import org.junit.Test;

/***
 * 链表题 技巧 : https://leetcode-cn.com/circle/article/Ej98dm/
 */
public class LinkedTest {

    /***
     * 实现一种算法，找出单向链表中倒数第 k 个节点。返回该节点的值。
     * 事例
     * 输入： 1->2->3->4->5 和 k = 2
     * 输出： 4
     */
    @Test
    public void test1() {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);
        ListNode l5 = new ListNode(5);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;
        System.out.println(kthToLast(l1, 1));
    }

    /***
     * 找出倒数第K个节点 可以采用“快慢指针”的方式来解答
     * https://leetcode-cn.com/problems/kth-node-from-end-of-list-lcci/solution/shuang-zhi-zhen-zhan-di-gui-3chong-jie-jue-fang-2/
     * @param head
     * @param k
     * @return
     */
    public static int kthToLast(ListNode head, int k) {
        //一个指针先移动k步，然后第二个指针再从头开始，这个时候这两个指针同时移动，当第一个指针到链表的末尾的时候，返回第二个指针即可
        ListNode first = head;
        ListNode second = head;
        //第一个指针先走k步
        while (k-- > 0) {
            first = first.next;
        }
        //然后两个指针同时走前进，直到快指针到最后一个点
        //那么慢指针刚好指向倒数第k个
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        return second.value;
    }

    /***
     * 删除中间节点
     * 输入：单向链表a->b->c->d->e->f中的节点c
     * 结果：不返回任何数据，但该链表变为a->b->d->e->f
     */
    @Test
    public void test2() {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);
        ListNode l5 = new ListNode(5);
        ListNode l6 = new ListNode(6);
        ListNode l7 = new ListNode(7);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;
        l5.next = l6;
        l6.next = l7;
        getMidNode(l1);
    }

    /***
     * 设有两个指针 fast 和 slow，初始时指向头节点。每次移动时，fast向后走两次，slow向后走一次，直到 fast 无法向后走两次。这使得在每轮移动之后。
     * fast 和 slow 的距离就会增加一。设链表有 n 个元素，那么最多移动 n/2 轮。
     * 当 n 为奇数时，slow 恰好指向中间结点，
     * 当 n 为 偶数时，slow 恰好指向中间两个结点的靠后一个
     */
    public void getMidNode(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        System.out.println(slow.value);
    }

    /***
     * 环路检测 ： 给定一个链表，如果它是有环链表，实现一个算法返回环路的开头节点
     * 这题的目的就是检测这个链表是不是一个环链表，如果是，返回那个环的开头节点
     */
    @Test
    public void test3() {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);
        ListNode l5 = new ListNode(5);
        ListNode l6 = new ListNode(6);
        ListNode l7 = new ListNode(7);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;
        l5.next = l6;
        l6.next = l7;
        l7.next = l4; //这里设置了一个环， l7回到了l4   1->2->3->4->5->6->7->4
        System.out.println(detectCycle(l1).value);
        ;
    }

    /***
     * 解题思路 : 使用快慢指针，快指针走2步，慢指针走1步。当第一次相遇时，将slow=head，然后按照1步的频率一起走直到再次相遇的点就是起始点
     * @param head
     * @return
     * https://leetcode-cn.com/problems/linked-list-cycle-lcci/solution/rang-ni-zhong-wen-shu-xue-tui-li-kuai-man-zhi-zhen/
     */
    public ListNode detectCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        int count = 0;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                if (count == 0) {
                    slow = head; //第一次相遇了
                    //当slow和fast第一次相遇时，把slow放到链表头部，与fast一起走，直到再次相遇
                    while (fast != slow) {
                        fast = fast.next;
                        slow = slow.next;
                    }
                    return slow;
                }
            }
        }
        return null;
    }

    /***
     * 给定单向链表的头指针和一个要删除的节点的值，定义一个函数删除该节点。(链表中节点的值互不相同)
     * 输入: head = [4,5,1,9], val = 5
     * 输出: [4,1,9]
     * 解释: 给定你链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9.
     */
    @Test
    public void test4() {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);
        ListNode l5 = new ListNode(5);
        ListNode l6 = new ListNode(6);
        ListNode l7 = new ListNode(7);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;
        l5.next = l6;
        l6.next = l7;

    }

    /***
     * 题目：给定单向链表的头指针和一个要删除的节点的值，然后删除要删除的节点
     * 解题思路 ： 使用虚拟头指针 + 双指针的方式
     * 单链表要删除节点必须先找到他的上一个节点。
     * 1.先初始化一个虚拟节点作为头节点，然后前置指针对应着虚拟头节点，这样指针和前置指针一起往后遍历，查到对应的值后就可以 pre.next = cur.next
     * @param head
     * @param val
     * @return
     */
    public ListNode deleteNode(ListNode head, int val) {
        //初始化一个虚拟指针
        ListNode dummy = new ListNode(0);
        //虚拟头指针指向头节点
        dummy.next = head;
        ListNode cur = head; // 移动指针
        ListNode pre = dummy; //前置节点指针
        while (cur != null) {
            if (cur.value == val) {
                //如果找到要删除的，直接删除
                pre.next = cur.next;
            }
            //如果没找到，pre指针和cur指针都同时往后移一步
            pre = cur;
            cur = cur.next;
        }
        return head; //返回头节点
    }

    /***
     * 题目：两个链表的第一个公共节点
     * ListA : 4 1 8 4 5
     * ListB : 5 0 1 8 4 5
     * 相交的节点是从8开始， 8->4->5
     */
    @Test
    public void test5() {
        ListNode A1 = new ListNode(4);
        ListNode A2 = new ListNode(1);
        ListNode A3 = new ListNode(8);
        ListNode A4 = new ListNode(4);
        ListNode A5 = new ListNode(5);
        A1.next = A2;
        A2.next = A3;
        A3.next = A4;
        A4.next = A5;
        ListNode B1 = new ListNode(5);
        ListNode B2 = new ListNode(0);
        ListNode B3 = new ListNode(1);
        ListNode B4 = new ListNode(8);
        ListNode B5 = new ListNode(4);
        ListNode B6 = new ListNode(5);
        B1.next = B2;
        B2.next = B3;
        B3.next = B4;
        B4.next = B5;
        B5.next = B6;
        getIntersectionNode(A1, B1);
    }


    /***
     * https://leetcode-cn.com/problems/liang-ge-lian-biao-de-di-yi-ge-gong-gong-jie-dian-lcof/solution/ji-he-shuang-zhi-zhen-deng-3chong-jie-jue-fang-shi/
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        return null;
    }

    /***
     * 反转链表
     */
    @Test
    public void test6(){
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);
        ListNode l5 = new ListNode(5);
        ListNode l6 = new ListNode(6);
        ListNode l7 = new ListNode(7);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;
        l5.next = l6;
        l6.next = l7;
        ListNode result = reverseList(l1);
        System.out.println(result);
    }


    /***
     * 反转链表
     * 截图思路 ： 画图跟一遍
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        //申请节点 pre cur  pre指向null
        ListNode pre = null;
        ListNode cur = head;
        ListNode tmp;
        while (cur != null){
            //记录当前节点的下一个节点
            tmp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tmp;
        }
        return pre;
    }


    /***
     * 回文链表
     * 输入： 1->2
     * 输出： false
     * 输入： 1->2->2->1
     * 输出： true
     */
    @Test
    public void test7(){
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);
        ListNode l5 = new ListNode(5);
        ListNode l6 = new ListNode(6);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;
        l5.next = l6;
        isPalindrome(l1);
    }

    /***
     * 1，采用快慢两个指针去寻找链表的中间节点；
     * 2，根据链表的中间节点反转后一半的链表；
     * 3，迭代比较链表前一半的元素和后一半的元素，判断节点的值是否相等，得出是否为回文。
     * 链接：https://leetcode-cn.com/problems/palindrome-linked-list-lcci/solution/kuai-man-zhi-zhen-jie-jue-by-lian-zhou/。
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        if(head == null) return true;
        ListNode midNode = findMidNode(head);
        ListNode secondHalfHead = reverseLinked(midNode.next);
        ListNode curr1 = head;
        ListNode curr2 = secondHalfHead;
        boolean palindrome = true;
        //开始比较两个链表
        while(palindrome && curr2 != null){
            if(curr1.value != curr2.value) palindrome = false;
            curr1 = curr1.next;
            curr2 = curr2.next;
        }
        return false;
    }

    /***
     * 寻找中间节点 ： 采用快慢指针找中间位置 + 反转链表中间位置后面的链表 + 两个链表逐个比较
     * @param head
     * @return
     */
    public ListNode findMidNode(ListNode head){
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    /***
     * 反转链表
     * @param head
     * @return
     */
    public ListNode reverseLinked(ListNode head){
        ListNode cur = head;
        ListNode pre = null;
        while (cur != null){
            ListNode tmp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tmp;
        }
        return pre;
    }


    /***
     * 分割链表
     * 编写程序以 x 为基准分割链表，使得所有小于 x 的节点排在大于或等于 x 的节点之前。如果链表中包含 x，
     * x 只需出现在小于 x 的元素之后(如下所示)。分割元素 x 只需处于“右半部分”即可，其不需要被置于左右两部分之间。
     * 输入: head = 3->5->8->5->10->2->1, x = 5
     * 输出: 3->1->2->10->5->5->8
     */
    @Test
    public void test8(){
        ListNode l1 = new ListNode(5);
        ListNode l2 = new ListNode(1);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(6);
        ListNode l5 = new ListNode(5);
        ListNode l6 = new ListNode(9);
        ListNode l7 = new ListNode(10);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;
        l5.next = l6;
        l6.next = l7;
        System.out.println(partition(l1,5));
    }

    /***
     * 分割链表
     * 具体方法 ：
     * 1。新建左右两个链表，左链表存放小于X的数据，右链表存放大于X的数据。
     * 2。从head开始遍历，大于的右链表，小于的放左链表
     * @param head
     * @param x
     * @return
     */
    public ListNode partition(ListNode head, int x) {
        ListNode leftHead = new ListNode(-1);
        ListNode left = leftHead;
        ListNode rightHead = new ListNode(-1);
        ListNode right = rightHead;
        //因为有断开操作，需要记录下一个遍历的节点
        ListNode next;
        while (head != null){
            next = head.next;
            if(head.value < x){
                left.next = head;
                left = left.next;
            }else{
                right.next = head;
                right = right.next;
            }
            //断开操作
            head.next = null;

            head = next;
        }
        //拼接操作 左链表尾节点指向右链表头节点
        left.next = rightHead.next;
        return leftHead.next;
    }

    /***
     * 移除未排序链表中的重复节点。保留最开始出现的节点
     *  输入：[1, 2, 3, 3, 2, 1]
     *  输出：[1, 2, 3]
     */
    @Test
    public void test9(){
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(2);
        ListNode l4 = new ListNode(2);
        ListNode l5 = new ListNode(5);
        ListNode l6 = new ListNode(6);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;
        l5.next = l6;
        ListNode listNode = removeDuplicateNodes(l1);
        System.out.println(listNode);
    }

    public ListNode removeDuplicateNodes(ListNode head) {
        int[] bits = new int[20000 / 32 + 1];
        ListNode cur = head;
        while (cur != null && cur.next != null){
            bits[cur.value /32] |= 1 << (cur.value % 32);
            if ((bits[cur.next.value / 32] & (1 << (cur.next.value % 32))) != 0)
                cur.next = cur.next.next;
            else
                cur = cur.next;
        }
        return head;
    }

}
