package com.sort;/**
 * Created by Administrator on 2018-05-30.
 */

/**
 * @author xuzhiyong
 * @createDate 2018-05-30-10:28
 * 冒泡排序
 * 口诀 外层循环 0 n - 1
 *     内层循环 0 n - i - 1
 */
public class MaoPaoSort {
    public static void main(String agrs[]) {
        char[] chars = {'a', 'v', 'r', 'g', 's', 'u', 'h', 'q', 'm', 'b', 'c'};
        for (int i = 0; i < chars.length - 1; i++) {
            for (int j = 0; j < chars.length - i - 1; j++) {
                if (chars[j] > chars[j + 1]) {
                    char temp = chars[j];
                    chars[j] = chars[j + 1];
                    chars[j + 1] = temp;
                }
            }
        }
        System.out.println(chars);
    }
}
