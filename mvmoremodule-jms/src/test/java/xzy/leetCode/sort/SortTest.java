package xzy.leetCode.sort;

import org.junit.Test;

public class SortTest {


    /***
     * 二分法查找
     */
    @Test
    public void test0() {
        int[] nums = {1, 1, 2, 3, 4, 5, 6, 7, 7, 8, 9, 9};
        sortAndGet(nums, 2, 0, nums.length);
    }


    public void sortAndGet(int[] nums, int targeValue, int left, int right) {
        int mid = (left + right) / 2;
        int midVal = nums[mid];
        if (midVal == targeValue) {
            System.out.println(mid);
        } else if (targeValue < midVal) {
            //查左边
            sortAndGet(nums, targeValue, left, mid - 1);
        } else if (targeValue > midVal) {
            sortAndGet(nums, targeValue, mid + 1, right);
        }
    }


    /***
     * 冒泡排序
     */
    @Test
    public void test1() {
        int[] arr = {1, 0, 3, 4, 5, -6, 7, 8, 9, 10};
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i; j++) {
                if(arr[j] > arr[j + 1]){
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        System.out.println(arr);
    }


}
