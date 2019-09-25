package com.chxf.tree;

import java.util.Arrays;

/**
 * @author ：chxiaofang
 * @date ：Created in 2019/9/16
 * @description ：堆排序
 * @version: 1.0
 */
public class HeapSort {
    public static void main(String[] args) {
        int[] arr = {4,6,-7,12,8,5,-18,9};
        arr = heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static int[] heapSort(int[] sortArray){
        int[] arr = Arrays.copyOf(sortArray,sortArray.length);
        // 首次调成为大顶堆
        for (int i = arr.length/2 - 1 ;i >= 0 ; i--){
            adjustHeap(arr,i,arr.length);
        }
        // 2).将堆顶元素与末尾元素交换，将最大元素"沉"到数组末端;
        // 3).重新调整结构，使其满足堆定义，然后继续交换堆顶元素与当前末尾元素，反复执行调整+交换步骤，直到整个序列有序
        for (int i = arr.length-1;i > 0; i--){
            // 交换元素，重构堆
            swap(arr,0,i);
            adjustHeap(arr,0,i);
        }
        return  arr;
    }

    // 调整为大顶堆
    /**
     *
     * @param array 待调整的数组
     * @param i 非叶子节点再数组中的索引
     * @param length 对多少个元素进行调整，是在逐渐减少的
     */
    public static void adjustHeap(int[] array,int i,int length){
        int temp = array[i];

        for (int k = i*2+1;k<length;k = k*2 +1){
            // 判断左子节点是否小于右子节点,寻找一个较大的子节点
            if (k+1 < length && array[k] < array[k+1]){
                k++;
            }
            if (array[k] > temp) {
                array[i] = array[k];
                i = k;
            }else{
                break;
            }
        }
        // 当 for 循环结束后，我们已经将以 i 为父结点的树的最大值，放在了 最顶(局部)
        array[i] = temp;
    }
    // 交换算法
    public static void swap(int[] arr,int i,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
