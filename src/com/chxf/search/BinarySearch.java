package com.chxf.search;

/**
 * @author ：chxiaofang
 * @date ：Created in 2019/9/14
 * @description ：二分法查找算法
 * @version: 1.0
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {12,52,64,78,123,585};

        int result = search(arr,0,arr.length-1,64);
        if (result == -1){
            System.out.println("未查询到数据！");
        }else{
            System.out.println("数据所在的下标为："+result);
        }
    }

    public static int search(int[] searchArrays,int left, int right,int value){
        int index = -1;

        if (left > right){
            return -1;
        }

        //int mid = (left + right)/2;
        int mid = left + (right-left)*(value-searchArrays[left])/(searchArrays[right] - searchArrays[left]);

        if (searchArrays[mid] < value) {
            return search(searchArrays,mid+1,right,value);
        }else if (searchArrays[mid] > value){
            return search(searchArrays,left,mid-1,value);
        }else{
            return mid;
        }
    }
}

