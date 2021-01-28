package xzy.leetCode.sort;

import org.junit.Test;

public class SortTest {


    /***
     * 二分法查找
     */
    @Test
    public void test0() {
        int[] nums = {1, 1, 2, 3, 4, 5, 6, 7, 7, 8, 9, 9};
        sortAndGet(nums,2,0,nums.length);
    }


    public void sortAndGet(int[] nums, int targeValue, int left, int right) {
        int mid = (left + right) / 2;
        int midVal = nums[mid];
        if (midVal == targeValue) {
            System.out.println(mid);
        } else if (targeValue < midVal) {
            //查左边
            sortAndGet(nums, targeValue, left, mid - 1);
        } else if(targeValue > midVal){
            sortAndGet(nums,targeValue,mid + 1 , right);
        }
    }


}
