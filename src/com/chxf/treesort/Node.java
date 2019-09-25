package com.chxf.treesort;

/**
 * @author ：chxiaofang
 * @date ：Created in 2019/9/17
 * @description ：二叉排序树节点
 * @version: 1.0
 */
public class Node {
    public int value;
    public Node left;
    public Node right;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    // 添加节点
    public void add(Node node) {
        if (node == null) {
            return;
        }
        // 判断value的值
        if (this.value < node.value) {
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.add(node);
            }
        } else {
            if (this.left == null) {
                this.left = node;
            } else {
                this.left.add(node);
            }
        }

        // 当添加完一个节点后，如果右子树的高度-左子树的高度的值大于一，则需要左旋转
        if (rightHeight() - leftHeight() > 1) {
            // 如果它的右子树的左子树的高度大于它的右子树的右子树的高度
            if (right != null && right.rightHeight() < right.leftHeight()) {
                // 先对右子结点进行右旋转
                right.rightRotate();
                // 然后在对当前结点进行左旋转
                leftRotate(); // 左旋转.
            }else{
                // 直接进行左旋转
                leftRotate();
            }
            // 防止继续向下执行
            return;
        }
        //当添加完一个结点后，如果 (度 左子树的高度 -  右子树的高度) > 1,
        if (leftHeight() - rightHeight() > 1) {
            // 如果它的左子树的右子树高度大于它的左子树的高度
            if(left != null && left.rightHeight() > left.leftHeight()) {
                // 先对当前结点的左结点( 左子树)-> 左旋转
                left.leftRotate();
                // 再对当前结点进行右旋转
                rightRotate();
            } else {
                // 直接进行右旋转即可
                rightRotate();
            }
        }
    }

    /**
     * 删除节点方法
     *
     * @param value 要删除的节点的值
     * @return 返回的是要删除的节点
     */
    public Node search(int value) {
        if (value == this.value) {
            return this;
        } else if (value < this.value) {
            if (this.left == null) {
                return null;
            } else {
                return this.left.search(value);
            }
        } else {
            if (this.right == null) {
                return null;
            } else {
                return this.right.search(value);
            }
        }
    }

    /**
     * 查找要删除节点的父节点
     *
     * @param value 要删除的节点
     * @return 要删除的节点的父节点
     */
    public Node searchParent(int value) {
        // 判断当前节点是否为要删除节点的父节点
        if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)) {
            return this;
        } else {
            // 如果要删除节点的值小于当前的值，并且当前节点的值不为空
            if (value < this.value && this.left != null) {
                return this.left.searchParent(value);
            } else if (value >= this.value && this.right != null) {
                return this.right.searchParent(value);
            } else {
                return null; // 没找到父节点
            }
        }
    }

    // 返回当前节点的高度
    public int height() {
        return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
    }

    // 返回左子树的高度
    public int leftHeight() {
        if (left == null) {
            return 0;
        }
        return left.height();
    }

    // 返回右子树的高度
    public int rightHeight() {
        if (right == null) {
            return 0;
        }
        return right.height();
    }

    // 平衡二叉树的左旋转的方法
    public void leftRotate() {
        // 1.创建新的节点，以当前根节点的值
        Node newNode = new Node(value);
        // 2.把新节点的左子节点设置为当前节点的左子树
        newNode.left = left;
        // 3. 把新建结点的右子树设置成原本结点的右子树的左子节点
        newNode.right = right.left;
        // 4.把当前结点的值替换为原本结点的左子节点的值
        value = right.value;
        // 5.把当前结点的左子树设置成当前节点左子结点的左子节点
        right = right.right;
        // 6.把当前结点的左子树（左子节点）设置成新的结点
        left = newNode;
    }

    // 平衡二叉树的右旋转的方法
    public void rightRotate() {
        // 1.创建新的节点，以当前根节点的值
        Node newNode = new Node(value);
        // 2.把新节点的右子节点设置为当前节点的右子树
        newNode.right = right;
        // 3. 把新建结点的z左子树设置成原本结点左子树的的右子树
        newNode.left = left.right;
        // 4.把当前结点的值替换为原本结点的右子节点的值
        value = left.value;
        // 5.把当前结点的右子树设置成当前节点右子树的右子树
        left = left.left;
        // 6.把当前结点的左子树（左子节点）设置成新的结点
        right = newNode;

    }

    // 中序遍历方法
    public void midOrder() {
        if (this.left != null) {
            this.left.midOrder();
        }
        System.out.println(this.toString());
        if (this.right != null) {
            this.right.midOrder();
        }
    }

}
