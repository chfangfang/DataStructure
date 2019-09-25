package com.chxf.sort;

import java.util.Arrays;

/**
 * @author ：chxiaofang
 * @date ：Created in 2019/9/9
 * @description ：冒泡排序（相邻两者进行比较，交换双方）
 * @version: 1.0
 */
public class BubbleSort implements IArraySort {
    @Override
    public int[] sort(int[] sourceArray) throws Exception {
        // 将参数进行复制
        int[] arr = Arrays.copyOf(sourceArray,sourceArray.length);
        // 进行排序
        for (int i = 1;i < arr.length; i++){
            boolean flag = true;
            for (int j = 0;j < arr.length - i ; j++){
                if (arr[j] > arr[j+1]){
                    int temp = arr[j+1];
                    arr[j+1] = arr[j];
                    arr[j] = temp;
                    // 如果进行了交换就将标志位设为false
                    flag = false;

                }
            }
            if (flag){
                break;
            }
        }
        return arr;
    }
}
