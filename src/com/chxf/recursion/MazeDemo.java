package com.chxf.recursion;

/**
 * @author ：chxiaofang
 * @date ：Created in 2019/8/31
 * @description ：迷宫
 * @version: 1.0
 */
public class MazeDemo {
    public static void main(String[] args) {
        // 采用二维数组来模拟一个迷宫
        int[][] maze = new int[8][7];

        // 设置迷宫的墙，用1表示
        for (int i = 0; i < 7;i++){
            maze[0][i] = 1;
            maze[7][i] = 1;
        }
        for (int i = 0; i < 8;i++){
            maze[i][0] = 1;
            maze[i][6] = 1;
        }
        maze[3][1] = 1;
        maze[3][2] = 1;
        maze[6][4] = 1;

        // 使用递归寻找迷宫通路
        setWay2(maze,1,1);

        //遍历
        for (int i = 0; i < maze.length;i++){
            for (int j = 0; j<maze[i].length;j++){
                System.out.printf("%d\t",maze[i][j]);
            }
            System.out.println();
        }


    }

    // 使用递归调用来给小老鼠找路

    /**
     * 如果能到达（6，5）则表示找到通路
     * 当map中的点为0时表示还未走过，为1表示是墙，为2表示通路，为3表示此路走过但是走不通
     * 迷宫走的策略为：下>右>上>左，如果走不动就回溯
     * @param map 地图
     * @param i 从那个地方出发
     * @param j  （i，j）表示从地图的那个点开始寻路
     * @return 如果找到路返回true，否则返回false；
     */
    public static boolean setWay(int[][] map,int i,int j){
        // 通路已找到
        if (map[6][5] == 2){
            return true;
        }else{
            // 当前路还未走过
            if (map[i][j] == 0){
                map[i][j] = 2;
                //向下走
                if (setWay(map,i+1,j)){
                    return true;
                } else if(setWay(map,i,j+1)){
                    return true;
                }else if (setWay(map,i-1,j)){
                    return true;
                }else if(setWay(map,i,j-1)){
                    return true;
                }else{
                    //,递归出口
                    map[i][j] = 3;
                    return false;
                }
            }else{
                // 不为0的话，可能为
                return false;
            }
        }
    }
    // 先上再右再下再左
    public static boolean setWay2(int[][] map,int i,int j){
        // 通路已找到  ,递归出口
        if (map[6][5] == 2){
            return true;
        }else{
            // 当前路还未走过
            if (map[i][j] == 0){
                map[i][j] = 2;
                //向下走
                if (setWay(map,i-1,j)){
                    return true;
                } else if(setWay(map,i,j+1)){
                    return true;
                }else if (setWay(map,i+1,j)){
                    return true;
                }else if(setWay(map,i,j-1)){
                    return true;
                }else{
                    map[i][j] = 3;
                    return false;
                }
            }else{
                return false;
            }
        }
    }
}
