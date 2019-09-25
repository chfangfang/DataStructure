package com.chxf.sort;

import java.util.Arrays;

/**
 * @author ：chxiaofang
 * @date ：Created in 2019/9/12
 * @description ：
 * @version: 1.0
 */
public class MergeSort implements IArraySort {
    @Override
    public int[] sort(int[] sourceArray) throws Exception {
        int[] arr = Arrays.copyOf(sourceArray,sourceArray.length);
        // 判断数组是否还可再分
        if (arr.length < 2) {
            return arr;
        }
        // 获取数组的中位数
        int middle = (int)Math.floor(arr.length/2);

        // 将数组拷贝至两个数组
        int[] left = Arrays.copyOfRange(arr,0,middle);
        int[] right = Arrays.copyOfRange(arr,middle,arr.length);

        return merge(sort(left),sort(right));
    }

    // 排序方法
    public int[] merge(int[] left,int[] right){
        // 声明长度为两个数组长度的数组
        int[] result = new int[left.length+right.length];
        // 声明每个数组的小标
        int l = 0, r = 0, len = 0;
        // 对数组进行排序
        while (len < left.length+right.length){
            if (left[l] < right[r]){
                result[len++] = left[l++];
                // 判断左边数组是否遍历完毕
                if (l == left.length){
                    for (int i = r; i < right.length; i++){
                        result[len++] = right[r++];
                    }
                }
            }else{
                result[len++] = right[r++];
                if (r == right.length) {
                    for (int j = l; j< left.length;j++){
                        result[len++] = left[l++];
                    }
                }
            }
        }

        return result;
    }
}
