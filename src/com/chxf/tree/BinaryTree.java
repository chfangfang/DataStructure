package com.chxf.tree;

/**
 * @author ：chxiaofang
 * @date ：Created in 2019/9/15
 * @description ：二叉树类
 * @version: 1.0
 */
public class BinaryTree {
    private TreeNode root;

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    // 前序遍历
    public void preOrder(){
        if (this.root != null) {
            this.root.preOrder();
        }else{
            System.out.println("二叉树为空！");
        }
    }
    // 中序遍历
    public void midOrder(){
        if (this.root != null) {
            this.root.midOrder();
        }else{
            System.out.println("二叉树为空！");
        }
    }
    // 后序遍历
    public void afterOrder(){
        if (this.root != null) {
            this.root.afterOrder();
        }else{
            System.out.println("二叉树为空！");
        }
    }
    // 删除节点
    public void delNode(int no){
        if (this.root != null) {
            // 如果只有一个 root 结点, 这里立即判断 root 是不是就是要删除结点
            if (root.getId() == no) {
                root = null;
            }else{
                this.root.deleteNode(no);
            }
        }else{
            System.out.println("二叉树为空！");
        }
    }

    // 只删除该节点，不删除子树，删除后左节点提前
    public void delOnlyNode(int no){

    }
}

class Test{
    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        // 创建节点
        TreeNode node1 = new TreeNode(1,"诸葛亮");
        TreeNode node2 = new TreeNode(2,"荀彧");
        TreeNode node3 = new TreeNode(3,"司马懿");
        TreeNode node4 = new TreeNode(4,"陆逊");

        // 设置根节点
        binaryTree.setRoot(node1);
        // 设置左右子节点
        node1.setLeft(node4);
        node1.setRight(node2);
        node2.setRight(node3);
        // 遍历二叉树
        binaryTree.preOrder();
        // 删除节点
        binaryTree.delNode(3);

        binaryTree.preOrder();
    }
}
