package com.chxf.sort;

import java.util.Arrays;

/**
 * @author ：chxiaofang
 * @date ：Created in 2019/9/9
 * @description ：
 * @version: 1.0
 */
public class InsertSort implements IArraySort {
    @Override
    public int[] sort(int[] sourceArray) throws Exception {
        int[] arr = Arrays.copyOf(sourceArray,sourceArray.length);

        for (int i = 1; i < arr.length; i++){
            // 保存当前插入的元素
            int insertValue = arr[i];
            // 保存插入的元素最初下标
            int insertIndex = i;
            // insertIndex > 0 ：保证数组不会越界
            // insertValue < arr[insertIndex]：待插入的数是否小于排好序中的元素
            // 数组中有序的部分末尾下标为 insertIndex - 1；
            // 当待插入数大于有序部分最后的一个元素时则无法进入循环
            while (insertIndex > 0 && insertValue < arr[insertIndex-1]){
                // 若插入数值小于末尾有序数值则将有序数值末尾值后移
                arr[insertIndex] = arr[insertIndex-1];
                insertIndex--;
            }
            // 如果未排序序列中第一个数比排序数列中最后一个数大，则直接放原位不用动
            if (insertIndex != i){
                arr[insertIndex] = insertValue;
            }
        }
        return arr;
    }
}
