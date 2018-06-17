package com.sort;/**
 * Created by Administrator on 2018-05-30.
 */

/**
 * @author xuzhiyong
 * @createDate 2018-05-30-10:28
 * 冒泡排序
 * 口诀 外层循环 0 到  n - 1
 * 内层循环 0 到   n - 1 - i
 */
public class MaoPaoSort {
    public static void main(String agrs[]) {
        int[] chars = {7, 4, 2, 9, 19, 34, 57, 3, 6, 1};
        for (int i = 0; i < chars.length - 1; i++) {
            for (int j = 0; j < chars.length - 1 - i; j++) {
                if (chars[j] > chars[j + 1]) {
                    int temp = chars[j];
                    chars[j] = chars[j + 1];
                    chars[j + 1] = temp;
                }
            }
            MaoPaoSort.printArray(chars);
        }
        MaoPaoSort.printArray(chars);
    }


    public static void printArray(int[] chars) {
        for (int k = 0; k < chars.length; k++) {
            System.out.print(chars[k] + "  ");
        }
        System.out.println();
    }

    public static void sort(int[] chars){
        for(int i = 0 ; i < chars.length - 1 ; i++){
            for(int j = 0 ; i < chars.length - 1 -i ; j++){
                if(chars[j] > chars[j + 1]){
                    int temp = chars[j];
                    chars[j] = chars[j + 1];
                    chars[j + 1] = temp;
                }
            }
        }
    }

}
