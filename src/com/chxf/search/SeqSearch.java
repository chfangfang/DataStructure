package com.chxf.search;

/**
 * @author ：chxiaofang
 * @date ：Created in 2019/9/14
 * @description ：顺序查找
 * @version: 1.0
 */
public class SeqSearch {
    public static void main(String[] args) {
        int[] arr = {12,52,585,64,78,123};

        int result = search(arr,64);
        if (result == -1){
            System.out.println("未查询到数据！");
        }else{
            System.out.println("数据所在的下标为："+result);
        }
    }

    public static int search(int[] sesrchArray,int value){
        int index = -1;

        for (int i = 0; i < sesrchArray.length; i++){
            if (value == sesrchArray[i]){
                index = i ;
                break;
            }
        }
        return index;
    }

}
