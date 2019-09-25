package com.chxf.tree;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author ：chxiaofang
 * @date ：Created in 2019/9/16
 * @description ：哈夫曼树
 * @version: 1.0
 */
public class HuffmanTree {
    public static void main(String[] args) {
        int[] arr = {13, 7, 8, 3, 29, 6, 1};
        //
        Node root = createHuffmanTree(arr);
        perOrder(root);
    }

    /**
     * 创建huffman树的方法
     * @param arr 需要构建哈夫曼树的数据
     * @return 返回构造完成时哈夫曼树的根节点
     */
    public static Node createHuffmanTree(int[] arr) {
        // 用来保存结点
        ArrayList<Node> nodes = new ArrayList<Node>();
        //
        for (int i : arr) {
            nodes.add(new Node(i));
        }

        while (nodes.size() > 1) {
            // 对节点进行排序
            Collections.sort(nodes);

            // 取出权值两个较小的节点
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            // 构建一个新的二叉树
            Node parent = new Node(leftNode.value + rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;
            // 删掉已用的节点，并将新节点加入到数组中
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(parent);
            // 再次进行排序
        }
        // 返回Huffman树的根节点就行
        return nodes.get(0);
    }

    // 编写遍历的方法
    public static void perOrder(Node root){
        if (root != null) {
            root.perOrder();
        }else{
            System.out.println("二叉树为空，无法遍历！！！");
        }
    }
}

// 为了利于排序则实现Comparable接口
class Node implements Comparable<Node> {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    // 前序遍历
    public void perOrder(){
        System.out.println(this);
        if (this.left != null) {
            this.left.perOrder();
        }
        if (this.right != null) {
            this.right.perOrder();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return this.value - o.value;
    }

}
