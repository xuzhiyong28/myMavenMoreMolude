package xzy.leetCode.sort;

import java.util.Arrays;

/***
 * 快速排序
 */
public class QuickSort {
    public static void main(String[] args){
        int[] arr = { 49, 38, 65, 97, 23, 22, 76, 1, 5, 8, 2, 0, -1, 22 };
        quickSort(arr, 0, arr.length - 1);
        Arrays.stream(arr).forEach(s -> System.out.println(s));
    }

    private static void quickSort(int[] arr, int left, int right) {
        if (left < right) {
            // 找寻基准数据的正确索引
            int index = getIndex(arr, left, right);
            // 进行迭代对index之前和之后的数组进行相同的操作使整个数组变成有序
            //quickSort(arr, 0, index - 1); 之前的版本，这种姿势有很大的性能问题，谢谢大家的建议
            quickSort(arr, left, index - 1);
            quickSort(arr, index + 1, right);
        }
    }
    


    private static int getIndex(int[] arr, int left , int right){
        int tmp = arr[left]; //确定基准数据
        while (left < right){
            //当队尾的元素大于基准数据时，向前挪动high指针
            while (left < right && arr[right] >= tmp){
                right-- ;
            }
            //如果队尾元素小于tmp，需要将其赋值给low
            arr[left] = arr[right];

            //当队首元素小于等于tmp时,向前挪动low指针
            while (left < right && arr[left] <= tmp){
                left++;
            }
            //当队首元素大于tmp时,需要将其赋值给high
            arr[right] = arr[left];

        }
        // 跳出循环时low和high相等,此时的low或high就是tmp的正确索引位置
        // 由原理部分可以很清楚的知道low位置的值并不是tmp,所以需要将tmp赋值给arr[low]
        arr[left] = tmp;
        return left;
    }
}
