package xzy.leetCode.sort;

/***
 * 归并排序
 * https://www.cnblogs.com/chengxiao/p/6194356.html
 */
public class MergeSort {

    public static void main(String[] args) {
        int[] nums = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        int[] temps = new int[nums.length];
        sort(nums, 0, nums.length - 1, temps);
        System.out.println(temps);
    }

    private static void sort(int[] nums, int left, int right, int[] temps) {
        if (left < right) {
            int mid = (left + right) / 2;
            sort(nums, left, mid, temps); //左边归并排序，使得左子序列有序
            sort(nums, mid + 1, right, temps); //右边归并排序，使得右子序列有序
            merge(nums, left, mid, right, temps); //将两个有序子数组合并操作
        }
    }

    private static void merge(int[] nums, int left, int mid, int right, int[] temps) {
        int i = left;
        int j = mid + 1;
        int t = 0;
        while (i <= mid && j <= right) {
            if (nums[i] <= nums[j]) {
                temps[t++] = nums[i++];
            } else {
                temps[t++] = nums[j++];
            }
        }
        while (i <= mid) {//将左边剩余元素填充进temp中
            temps[t++] = nums[i++];
        }
        while (j <= right) {//将右序列剩余元素填充进temp中
            temps[t++] = nums[j++];
        }
        t = 0;
        //将temp中的元素全部拷贝到原数组中
        while (left <= right) {
            nums[left++] = temps[t++];
        }
    }

}
