package com.chxf.treesort;

/**
 * @author ：chxiaofang
 * @date ：Created in 2019/9/17
 * @description ：二叉排序树
 * @version: 1.0
 */
public class BinarySortTree {
    private Node root;

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    // 添加节点的方法
    public void addNode(Node node){
        if (root == null){
            root = node;
        }else{
            root.add(node);
        }
    }

    // 查找要删除的节点
    public Node search(int value){
        if (root == null) {
            return null;
        } else {
          return root.search(value);
        }
    }

    // 查找父节点
    public Node searchParent(int value){
        if (root == null) {
            return null;
        }else {
            return root.searchParent(value);
        }
    }

    // 删除节点的方法
    public void delNode(int value){
        if (root == null){
            return;
        } else {
            // 1. 需要先去找到要删除的节点
            Node targetNode = search(value);
            // 判断是否存在此节点
            if (targetNode == null) {
                return;
            }
            // 如果我们发现当前这颗二叉树只有一个节点
            if (root.left == null && root.right == null) {
                root = null;
                return;
            }
            // 2. 查找目标节点targetNode的父节点
            Node parent = searchParent(value);

            // 如果删除的节点为叶子节点
            if (targetNode.left == null && targetNode.right == null){
                if (parent.left != null && parent.left.value == value){
                    parent.left = null;
                    return;
                }else if (parent.right != null && parent.right.value == value){
                    parent.right = null;
                    return;
                }
            }else if (targetNode.left != null && targetNode.right != null){
                // 删除有两颗子节点的节点情况
                // 从右子树中获取最小节点的信息，并将最小节点删除
                Node minNode = delRightMin(targetNode.right);
                // 删除最小节点
                delNode(minNode.value);
                // 删除最小结点之后，将最小节点的值赋给目标节点，以达到节点替换的效果
                targetNode.value = minNode.value;
            }else{
                // 如果删除的节点只有一个叶子节点
                // 如果要删除的节点为有左子节点
                if (targetNode.left != null){
                    // 如果目标节点是父节点的左子节点
                    if (parent.left.value == value){
                        parent.left = targetNode.left;
                    }else{
                        // 如果目标节点是父节点的左子节点
                        parent.right = targetNode.left;
                    }
                }else{
                    // 要删除的目标节点有右子节点
                    if (parent.left.value == value) {
                        // 目标节点是父节点的左子节点
                        parent.left = targetNode.right;
                    }else{
                        // 目标节点是父节点的右子节点
                        parent.right = targetNode.right;
                    }
                }
            }
        }
    }

    // 获取右测子树的最小节点，并返回此节点的信息
    public Node delRightMin(Node node){
        Node target = node;
        // 循环查找左节点，直到找到最小节点,
        // 循环结束后target就指向最小节点
        while (target.left != null){
            target = target.left;
        }
        return target;
    }

    // 获取左子树的最大节点，并返回节点信息
    public Node delLeftMaxNode(Node node){
        Node target = node;
        // 循环查找左侧的最大节点信息
        while (target.right != null) {
            target = target.right;
        }
        return  target;
    }

    // 遍历方法
    public void mieOrder(){
        if (root != null){
            root.midOrder();
        }else {
            System.out.println("二叉树为空！");
        }
    }
}
