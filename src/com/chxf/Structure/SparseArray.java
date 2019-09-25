package com.chxf.Structure;

/**
 * @author ：chxiaofang
 * @date ：Created in 2019/8/25
 * @description ：稀疏数组的遍历
 * @version: 1.0
 */
public class SparseArray {
    public static void main(String[] args) {
        int sum = 0;
        // 1.创建7*8的二维数组
        int chessArray[][] = new int[7][8];
        chessArray[1][2] = 1;
        chessArray[2][3] = 2;

        // 2.遍历二维数组寻找元素个数
        System.out.println("二维数组：");
        for (int[] row : chessArray) {
            for (int data : row){
                System.out.printf("%d\t", data);
                if(data != 0){
                   sum++;
                }
            }
            System.out.println();
        }

        // 4.创建稀疏数组
        int[][] sparseArray = new int[sum+1][3];
        // 给稀疏数组赋值
        sparseArray[0][0] = chessArray.length;
        sparseArray[0][1] = chessArray[0].length;
        sparseArray[0][2] = sum;
        int row = 1;
        // 遍历二维数组为稀疏数组赋值
        for (int i = 0;i<chessArray.length;i++){
            for (int j =0; j<chessArray[i].length;j++){
                if (chessArray[i][j] != 0){
                    sparseArray[row][0] = i;
                    sparseArray[row][1] = j;
                    sparseArray[row][2] = chessArray[i][j];
                    row++;
                }
            }
        }

        // 5.遍历稀疏数组
        System.out.println("二维数组对应的稀疏数组如下：");
        for (int[] r : sparseArray) {
            for (int data : r){
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }

        // 6.根据稀疏数组创建对应的二维数组
        int[][] transArray = new int[sparseArray[0][0]][sparseArray[0][1]];
        // 7.遍历稀疏数组为二维数组赋值
        for (int i = 1;i < sparseArray.length;i++){
            transArray[sparseArray[i][0]][sparseArray[i][1]] = sparseArray[i][2];
        }
        // 8.遍历转换的二维数组
        System.out.println("由稀疏数组转换的二维数组：");
        for (int[] r : transArray){
            for (int data : r){
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }
    }

}
