package com.chxf.sort;

import java.util.Arrays;

/**
 * @author ：chxiaofang
 * @date ：Created in 2019/9/11
 * @description ：
 * @version: 1.0
 */
public class ShellSort implements IArraySort {
    @Override
    public int[] sort(int[] sourceArray) throws Exception {
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);

        // 设置增量gap
        int gap = (int) (arr.length / 2);

        // 进行排序
        while (gap > 0) {
            //进行排序
            for (int i = gap; i < arr.length; i++) {
                // 定义插入的元素
                int insertValue = arr[i];
                // 定义插入位置
                int insertIndex = i;
                //
                while (insertIndex-gap >= 0 && insertValue < arr[insertIndex - gap]) {
                    arr[insertIndex] = arr[insertIndex - gap];
                    insertIndex -= gap;
                }
                arr[insertIndex] = insertValue;
            }
            gap = (int) (gap / 2);
        }
        return arr;
    }
}
