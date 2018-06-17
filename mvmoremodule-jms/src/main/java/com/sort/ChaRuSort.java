package com.sort;/**
 * Created by Administrator on 2018-06-12.
 */

/**
 * @author xuzhiyong
 * @createDate 2018-06-12-14:01
 * 假如有初始数据：25  11  45  26  12  78。
　　1、首先比较25和11的大小，11小，位置互换，第一轮排序后，顺序为：[11, 25, 45, 26, 12, 78]。
　　2、对于第三个数据45，其大于11、25，所以位置不变，顺序依旧为：[11, 25, 45, 26, 12, 78]。
　　3、对于第四个数据26，其大于11、25，小于45，所以将其插入25和45之间，顺序为：[11, 25, 26, 45, 12, 78]。
　　.......
　　4、最终顺序为：[11, 12, 25, 26, 45, 78]。
 */
public class ChaRuSort {
    public static void main(String args[]) {
        int[] chars = {25, 11, 45, 26, 12, 78};
        int temp;
        for (int i = 1; i < chars.length; i++) {
            temp = chars[i];
            int j;
            for (j = i - 1; j >= 0; j--) {
                if (chars[j] > temp)
                    chars[j + 1] = chars[j];
                else
                    break;
            }
            chars[j + 1] = temp;
            ChaRuSort.printArray(chars);
        }
    }

    public static void printArray(int[] chars) {
        for (int k = 0; k < chars.length; k++) {
            System.out.print(chars[k] + "  ");
        }
        System.out.println();
    }
}
