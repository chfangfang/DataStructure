package com.chxf.recursion;

/**
 * @author ：chxiaofang
 * @date ：Created in 2019/8/31
 * @description ：
 * @version: 1.0
 */
public class printReverse {
    // 定义string变量
    private static String str = "梁荣秀大傻逼！";

    //
    private static char[] s ={'h','e','l','l','o'};//
    // ['h','e','l','l','o'];
    public static void main(String[] args) {
        //
        printReverse printReverse = new printReverse();
        printReverse.reverse(0);
        printReverse.rever(0,s);

    }

    private void reverse(int n){
        if (n >= str.length()){
            return;
        }
        print(n);
        reverse(n+1);
    }

    public static void rever(int index,char[] str){
        if (str == null || index >= str.length) {
            return;
        }
        System.out.print(str[str.length-index-1]);
        rever(index + 1, str);
    }
    /**
     * 打印方法
     * @param n
     */
    private static void print(int n){
        System.out.println(str.substring(str.length()-n-1).charAt(0));
    }
}
