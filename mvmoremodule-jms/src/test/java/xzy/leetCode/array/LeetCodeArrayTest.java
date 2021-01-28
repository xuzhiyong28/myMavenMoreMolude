package xzy.leetCode.array;

import com.google.common.collect.Sets;
import org.junit.Assert;
import org.junit.Test;
import xzy.leetCode.linkedList.ListNode;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class LeetCodeArrayTest {


    /***
     * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
     请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
     你可以假设 nums1 和 nums2 不会同时为空。
     示例 1:
     nums1 = [1, 3]
     nums2 = [2]
     则中位数是 2.0
     示例 2:
     nums1 = [1, 2]
     nums2 = [3, 4]
     则中位数是 (2 + 3)/2 = 2.5
     */
    @Test
    public void test1() {
        int[] A = {1, 3};
        int[] B = {2};
        double d = LeetCodeArrayUtils.findMedianSortedArrays(A, B);
        System.out.println(d);
    }


    /***
     * 给出一个区间的集合，请合并所有重叠的区间。

     示例 1:

     输入: [[1,3],[2,6],[8,10],[15,18]]
     输出: [[1,6],[8,10],[15,18]]
     解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
     */
    @Test
    public void test2() {

    }

    /***
     * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
     * (你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍)
     * 给定 nums = [2, 7, 11, 15], target = 9
     *
     * 因为 nums[0] + nums[1] = 2 + 7 = 9
     * 所以返回 [0, 1]
     */
    @Test
    public void test3() {
        int[] nums = {2, 7, 11, 15};
        int[] ints = LeetCodeArrayUtils.twoSum(nums, 17);
        Arrays.stream(ints).forEach(System.out::println);
    }


    /***
     * 删除排序数组重的重复项
     */
    @Test
    public void test4() {
        int[] nums = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        LeetCodeArrayUtils.removeDuplicates(nums);
        Arrays.stream(nums).forEach(System.out::println);
    }


    /***
     * 合并两个有序链表
     * 1 -> 2 -> 4 -> 5
     * 1 -> 3 -> 7 -> 8
     */
    @Test
    public void test5() {
        ListNode l1 = LeetCodeArrayUtils.initListNode(new int[]{1, 1, 2, 4, 5});
        ListNode l2 = LeetCodeArrayUtils.initListNode(new int[]{1, 2, 2, 8});
        ListNode listNode = LeetCodeArrayUtils.mergeTwoLists(l1, l2);
        System.out.println();
    }


    /***
     * 逆波兰表达式求值
     * 输入: ["2", "1", "+", "3", "*"]
     * 输出: 9
     * 解释: 该算式转化为常见的中缀算术表达式为：((2 + 1) * 3) = 9
     */
    @Test
    public void test6() {
        System.out.println(evalRPN(new String[]{"2", "1", "+", "3", "*"}));
    }

    public static int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<Integer>(); //定义一个栈
        Integer op1, op2;
        for (int i = 0; i < tokens.length; i++) {
            if (!"+-*/".contains(tokens[i])) {
                stack.push(Integer.valueOf(tokens[i]));
            } else {
                op2 = stack.pop();
                op1 = stack.pop();
                switch (tokens[i]) {
                    case "+":
                        stack.push(op1 + op2);
                        break;
                    case "-":
                        stack.push(op1 - op2);
                        break;
                    case "*":
                        stack.push(op1 * op2);
                        break;
                    case "/":
                        stack.push(op1 / op2);
                        break;
                }
            }
        }
        return stack.peek();
    }

    /***
     * 数组特点 : 前面有序 后面有序。找到一个其中一个值
     */
    @Test
    public void zrSort() {
        int[] nums = {4, 5, 6, 7, 8, 9, 0, 1, 2, 3};
        Assert.assertEquals(sortAndGet(nums, 4, 0, nums.length), 0);
        Assert.assertEquals(sortAndGet(nums, 5, 0, nums.length), 1);
        Assert.assertEquals(sortAndGet(nums, 6, 0, nums.length), 2);
        Assert.assertEquals(sortAndGet(nums, 7, 0, nums.length), 3);
        Assert.assertEquals(sortAndGet(nums, 8, 0, nums.length), 4);
        Assert.assertEquals(sortAndGet(nums, 9, 0, nums.length), 5);
        Assert.assertEquals(sortAndGet(nums, 0, 0, nums.length), 6);
        Assert.assertEquals(sortAndGet(nums, 1, 0, nums.length), 7);
        Assert.assertEquals(sortAndGet(nums, 2, 0, nums.length), 8);
        Assert.assertEquals(sortAndGet(nums, 3, 0, nums.length), 9);
    }

    public int sortAndGet(int[] nums, int targeVal, int left, int right) {
        int mid = (left + right) / 2;
        int midValue = nums[mid];
        int leftVal = nums[left];
        int result = 0;
        if (left <= right) {
            if (midValue == targeVal) {
                result = mid;
                return result;
            } else if (targeVal > midValue) {
                //找右数组
                result = sortAndGet(nums, targeVal, mid + 1, right);
            } else if (targeVal < midValue && targeVal < leftVal) {
                //找右数组
                result = sortAndGet(nums, targeVal, mid + 1, right);
            } else if (targeVal < midValue && targeVal >= leftVal) {
                //找左边
                result = sortAndGet(nums, targeVal, left, mid - 1);
            }
        }
        return result;
    }

    /***
     * 青蛙跳
     */
    @Test
    public void testWays(){
        System.out.println(numWays(1));
        System.out.println(numWays(2));
        System.out.println(numWays(3));
        System.out.println(numWays(4));
        System.out.println(numWays(5));
    }

    public int numWays(int n){
        int a = 1 , b = 1 , sum;
        for(int i = 0 ; i < n ; i++){
            sum = a + b;
            a = b;
            b = sum;
        }
        return a;
    }

    /***
     * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。
     * 例如，数组 [3,4,5,1,2] 为 [1,2,3,4,5] 的一个旋转，该数组的最小值为1
     */
    @Test
    public void minArrayTest(){
        int[] nums = {3,1,1,2};
        System.out.println(minArray(nums));
    }


    public int minArray(int[] numbers) {
        int low = 0;
        int high = numbers.length - 1;
        while (low < high){
            int mid = (low + high ) / 2;
            if(numbers[mid] < numbers[high]){
                //找左边
                high = mid;
            }else if(numbers[mid] > numbers[high]){
                low = mid + 1;
            }else{
                high = high - 1;
            }
        }
        return numbers[low];
    }
}
