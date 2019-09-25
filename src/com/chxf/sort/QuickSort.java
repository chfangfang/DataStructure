package com.chxf.sort;

import java.util.Arrays;

/**
 * @author ：chxiaofang
 * @date ：Created in 2019/9/11
 * @description ：快速排序
 * @version: 1.0
 */
public class QuickSort implements IArraySort {
    @Override
    public int[] sort(int[] sourceArray) throws Exception {
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);

        int left = 0;
        int right = arr.length-1;
        quickSort(arr,left,right);
        return arr;
    }

    public void quickSort(int[] arr, int left, int right) {
        int l = left;
        int r = right;
        int pivot = arr[(left + right) / 2];
        // 比pivot小的放在左边，大的放在右边
        while (l < r) {
            // 从左边一直寻找到一个大于privot的值才不满足
            while (arr[l] < pivot) {
                l += 1;
            }
            // 从右边一直找一个小的
            while (arr[r] > pivot) {
                r -= 1;
            }
            // 若l >= r满足则说明pivot两边已经完全按照顺序进行排序好
            // 左边全是小于等于pivot的值，右边全是大于等于pivot的值
            if (l >= r) {
                break;
            }
            // 交换
            swap(arr, l, r);

            // ???????????????
            // 如果交换完后，发现这个 arr[l] == pivot 值 相等 r--， 前移
            if (arr[l] == pivot) {
                r--;
            }
            if (arr[r] == pivot) {
                l++;
            }
        }

        // 如果 l == r, 必须 l++, r--, 否则为出现栈溢出
        if (l == r) {
            l += 1;
            r -= 1;
        }
        //向左递归
        if (left < r) {
            quickSort(arr, left, r);
        }
        //向右递归
        if (right > l) {
            quickSort(arr, l, right);
        }
    }

    // 交换方法
    public void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
