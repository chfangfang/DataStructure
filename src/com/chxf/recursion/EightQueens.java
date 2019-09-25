package com.chxf.recursion;

/**
 * @author ：chxiaofang
 * @date ：Created in 2019/8/31
 * @description ：八皇后问题
 * @version: 1.0
 */
public class EightQueens {
    // 表示共有多少皇后
    int max = 8;
    // 定义一个数组表示皇后最后存放的位置
    int[] array = new  int[max];

    static int num = 0;

    public static void main(String[] args) {
        EightQueens eightQueens = new EightQueens();
        eightQueens.check(0);
        System.out.println("总共有多少解法："+num);
    }

    private void check(int n){
        // 当n=8时，就相当于要放置第九个皇后了（n从0开始），就相当于放完了八皇后啦
        if (n == max){
            System.out.println("****"+num+"****");
            print();

            return;
        }else{
            // 依次放入皇后并判断是否冲突,for循环是第一个皇后放的行数
            for (int i = 0; i<max;i++){
                // 将皇后先放在第一列，并依次向后延续
                array[n] = i;
                if (judge(n)){
                    // 如果不冲突，就继续放后面的
                    check(n+1);
                }
                // 如果冲突则返回执行
            }
        }
    }

    /**
     * 查看我们摆放的第n个皇后是否与前面的冲突
     * @param n
     * @return 返回位置是否可以存放
     */
    private boolean judge(int n){
        for (int i = 0;i < n;i++){
            // array[i] == array[n]判断同一列，Math.abs(n-i) == Math.abs(array[n]-array[i])判断是否在同一斜线
            if (array[i] == array[n] || Math.abs(n-i) == Math.abs(array[n]-array[i])){
                return false;
            }
        }
        return true;
    }

    // 打印数组的方法
    private void print(){
        num ++;
        for (int i = 0; i < max; i++){
            System.out.printf(array[i]+" ");
        }
        System.out.println();
    }
}
