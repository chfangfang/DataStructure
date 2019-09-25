package com.chxf.tree;

/**
 * @author ：chxiaofang
 * @date ：Created in 2019/9/14
 * @description ：二叉树节点类
 * @version: 1.0
 */
public class TreeNode {
    private int id;
    private String name;
    private TreeNode left;
    private TreeNode right;

    public TreeNode(int id, String name) {
        this.id = id;
        this.name = name;
        this.left = null;
        this.right = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    // 前序遍历
    public void preOrder() {
        System.out.println(this.toString());
        // 递归左子树
        if (this.left != null) {
            this.left.preOrder();
        }
        // 递归右子树
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    // 中序遍历
    public void midOrder() {
        if (this.left != null) {
            this.left.midOrder();
        }
        System.out.println(this.toString());
        if (this.right != null) {
            this.right.midOrder();
        }
    }

    // 后序遍历
    public void afterOrder() {
        if (this.left != null) {
            this.left.afterOrder();
        }
        if (this.right != null) {
            this.right.afterOrder();
        }
        System.out.println(this.toString());
    }

    // 删除节点
    /**
     * 1. 因为我们的二叉树是单向的，所以我们是判断当前结点的子结点是否需要删除结点，而不能去判断当前这个结点是不是需要删除结点.
     * 2. 如果当前结点的左子结点不为空，并且左子结点 就是要删除结点，就将 this.left = null; 并且就返回(结束递归删除)
     * 3. 如果当前结点的右子结点不为空，并且右子结点 就是要删除结点，就将 this.right= null ;并且就返回(结束递归删除)
     * 4. 如果第 2 和第 3 步没有删除结点，那么我们就需要向左子树进行递归删除
     * 5. 如果第 4 步也没有删除结点，则应当向右子树进行递归删除.
     */
    public void deleteNode(int no){
        // 判断左子节点
        if (this.left != null && this.left.id == no) {
            this.left = null;
            return;
        }
        // 判断右子节点
        if (this.right != null && this.right.id == no) {
            this.right = null;
            return;
        }
        // 左子节点不是要删除的则递归左右子节点
        if (this.left != null) {
            this.left.deleteNode(no);
        }
        if (this.right != null) {
            this.right.deleteNode(no);
        }
    }

    // 只删除节点
    public void delOnlyNode(int no){

    }
}

