package xzy.leetCode.array;

import com.google.common.collect.Sets;
import org.junit.Test;

import java.util.Arrays;
import java.util.Set;

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
}
