package com.chxf.treesort;

/**
 * @author ：chxiaofang
 * @date ：Created in 2019/9/17
 * @description ：
 * @version: 1.0
 */
public class BinarySortTreeDemo {
    public static void main(String[] args) {
        // int[] arr = {7, 3, 10, 12, 5, 1, 9};
        //int[] arr = {4,5,6,7,8};
        int[] arr = {10,11,7,6,8,9};
        BinarySortTree binarySortTree = new BinarySortTree();
        //循环的添加结点到二叉排序树
        for(int i = 0; i< arr.length; i++) {
            binarySortTree.addNode(new Node(arr[i]));
        }
        System.out.println("二叉树的高度：" + binarySortTree.getRoot().height());
        System.out.println("二叉树左子树的高度：" + binarySortTree.getRoot().leftHeight());
        System.out.println("二叉树右子树的高度：" + binarySortTree.getRoot().rightHeight());
        System.out.println("当前根节点：" + binarySortTree.getRoot());
        System.out.println("中序遍历二叉排序树~");
        binarySortTree.mieOrder();
        // 删除叶子节点2
        //binarySortTree.delNode(10);
        //中序遍历二叉排序树
        //System.out.println("删除节点之后中序遍历二叉排序树~");
        //binarySortTree.mieOrder();
    }
}
