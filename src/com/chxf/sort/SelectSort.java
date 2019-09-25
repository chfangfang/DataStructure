package com.chxf.sort;

import java.util.Arrays;

/**
 * @author ：chxiaofang
 * @date ：Created in 2019/9/9
 * @description ：
 * @version: 1.0
 */
public class SelectSort implements IArraySort {
    @Override
    public int[] sort(int[] sourceArray) throws Exception {
        int[] arr = Arrays.copyOf(sourceArray,sourceArray.length);

        for (int i = 0; i < arr.length-1; i++){
            int min = arr[i];
            int minIndex = i;
            for (int j = i;j < arr.length;j++){
                if (min > arr[j]){
                    min = arr[j];
                    minIndex = j;
                }
            }

            if (minIndex != i){
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
        }
        return arr;
    }
}
