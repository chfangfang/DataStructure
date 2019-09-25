package com.chxf.graph;

/**
 * @author ：chxiaofang
 * @date ：Created in 2019/9/24
 * @description ：图的测试类
 * @version: 1.0
 */
public class GraphTest {
    public static void main(String[] args) {
        int n = 5;
        Graph graph = new Graph(n);
        String nodeValue[] = {"A","B","C","D","E"};
        // 添加结点
        for (String value : nodeValue){
            graph.insertNode(value);
        }

        // 添加边 A-B  A-C  B-C B-D B-E
        graph.insertEdges(0,1,1);
        graph.insertEdges(0,2,1);
        graph.insertEdges(1,2,1);
        graph.insertEdges(1,3,1);
        graph.insertEdges(1,4,1);

        // 展示矩阵
        graph.showGraph();

        // 深度遍历
        //System.out.println("深度遍历结果：");
        //graph.dfs();

        // 广度优先遍历
        System.out.println("广度优先遍历结果：");
        graph.bfs();
    }
}
