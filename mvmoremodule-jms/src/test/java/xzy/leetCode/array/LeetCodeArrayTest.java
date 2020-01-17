package xzy.leetCode.array;

import org.junit.Test;

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
    public void test1(){
        int[] A = {1,3};
        int[] B = {2};
        double d = LeetCodeArrayUtils.findMedianSortedArrays(A,B);
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
    public void test2(){

    }

}
