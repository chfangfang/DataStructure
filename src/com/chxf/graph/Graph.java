package com.chxf.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author ：chxiaofang
 * @date ：Created in 2019/9/24
 * @description ：图
 * @version: 1.0
 */
public class Graph {
    // 存储数据的结点
    private ArrayList<String> nodeList;
    // 存储图所对应的邻接矩阵
    private int[][] edges;
    // 存储变得数目
    private int numEdges;
    // 存储节点是否被访问
    private boolean[] isVisited;

    // 构造器,传入有多少顶点
    public Graph(int n) {
        edges = new int[n][n];
        nodeList = new ArrayList<String>(n);
        isVisited = new boolean[n];
        numEdges = 0;
    }

    // 插入结点
    public void insertNode(String value) {
        nodeList.add(value);
    }

    /**
     * 添加边
     *
     * @param v1     表示顶点的下标既第几个顶点
     * @param v2     表示顶点的下标
     * @param weight 表示两点直接有无边，1：有边；0：无边
     */
    public void insertEdges(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numEdges++;
    }

    // 返回结点的个数
    public int getNumOfGraph() {
        return nodeList.size();
    }

    // 返回边的数目
    public int getNumOfEdges() {
        return numEdges;
    }

    // 返回结点i对应的数据
    public String getNodeValue(int i) {
        return nodeList.get(i);
    }

    // 返回两个节点之间的权值
    public int getNodeWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    // 显示图对应的矩阵
    public void showGraph() {
        for (int[] link : edges) {
            System.out.println(Arrays.toString(link));
        }
    }

    /**
     * 获取结点的第一个邻接结点的下标
     * @param index
     * @return 如果存在就返回邻接结点的则返回对应的下标
     */
    public int getNodeFirstNeighbor(int index){
        for (int j = 0; j < nodeList.size() ;j++){
            if (edges[index][j] > 0){
                return j;
            }
        }
        return -1;
    }

    // 根据前一个邻接结点的下标来获取下一个邻接结点
    public int getNextNeighbor(int v1,int v2){
        for (int j = v2 + 1; j < nodeList.size(); j++){
            if (edges[v1][j] > 0){
                return j;
            }
        }
        return -1;
    }

    // 深度优先遍历算法
    public void dfs(boolean[] isVisited,int index){
        // 首先访问该节点
        System.out.print(getNodeValue(index)+"->");
        // 将该结点设置为已访问
        isVisited[index] = true;
        // 查找该节点的第一个邻接结点
        int w = getNodeFirstNeighbor(index);
        // 判断是否找到
        if (w != -1){
            // 判断是否被访问过,若为被访问过则进行递归操作
            if (!isVisited[w]){
                dfs(isVisited,w);
            }else{
                w = getNextNeighbor(index,w);
            }
        }
    }
    // 对dfs进行重载，遍历一遍所有的结点，并对每一个点都进行dfs
    public void dfs(){
        for (int i = 0; i < getNumOfGraph(); i++){
            // 判断该结点是否被访问
            if (!isVisited[i]){
                dfs(isVisited,i);
            }
        }
    }

    // 对一个节点进行广度优先遍历的算法
    public void bfs(boolean[] isVisited,int index){
        int u ; // 表示队列的头结点的下标
        int w; // 邻接点的下标
        // 队列，用于记录节点访问的顺序
        LinkedList queue = new LinkedList();
        // 输出结点
        System.out.print(getNodeValue(index)+"->");
        // 设置结点已访问
        isVisited[index] = true;
        // 将结点加入队列
        queue.addLast(index);
        // 只要队列非空，则循环
        while (!queue.isEmpty()){
            // 取出队列的头节点
            u = (Integer)queue.removeFirst();
            // 得到第一个临界点的下标
            w = getNodeFirstNeighbor(u);
            while (w != -1){
                // 判断是否访问过
                if (!isVisited[w]){
                    System.out.print(getNodeValue(w)+"->");
                    // 标记已经访问
                    isVisited[w] = true;
                    // 入队列
                    queue.addLast(w);
                }
                w = getNextNeighbor(u,w);
            }
        }
    }

    // 遍历所有的结点进行广度优先遍历
    public void bfs(){
        for (int i = 0; i < getNumOfGraph();i++){
            // 判断是否已经遍历
            if (!isVisited[i]){
                bfs(isVisited,i);
            }
        }
    }
}
