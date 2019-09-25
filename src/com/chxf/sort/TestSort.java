package com.chxf.sort;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ：chxiaofang
 * @date ：Created in 2019/9/9
 * @description ：测试排序算法
 * @version: 1.0
 */
public class TestSort {
    public static void main(String[] args) throws Exception {
        // 初始化测试数组
        int[] sortArray = new int[8000000];
        for (int i = 0;i < 8000000; i++){
            sortArray[i] = (int)(Math.random() * 8000000);
        }
//        for (int i = 0; i <sortArray.length; i++){
//            System.out.println(sortArray[i]);
//        }

        // 创建时间对象
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String startTime = simpleDateFormat.format(date);
        System.out.println("开始排序时间为："+startTime);

        // 冒泡排序
        // BubbleSort bubbleSort = new BubbleSort();
        // sortArray = bubbleSort.sort(sortArray);

        // 选择排序
        //SelectSort selectSort = new SelectSort();
        //sortArray = selectSort.sort(sortArray);

        // 插入排序
        //InsertSort insertSort = new InsertSort();
        //sortArray = insertSort.sort(sortArray);

        // 希尔排序
        //ShellSort shellSort = new ShellSort();
        //sortArray = shellSort.sort(sortArray);

        // 归并排序
        //MergeSort mergeSort = new MergeSort();
        //sortArray = mergeSort.sort(sortArray);

        //快速排序
        QuickSort quickSort = new QuickSort();
        sortArray = quickSort.sort(sortArray);

        Date endDate = new Date();
        String endTime = simpleDateFormat.format(endDate);
        System.out.println("排序结束的时间为："+endTime);
//        for (int i = 0; i <sortArray.length; i++){
//            System.out.println(sortArray[i]);
//        }

    }
}
