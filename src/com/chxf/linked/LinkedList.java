package com.chxf.linked;

import java.util.Stack;

/**
 * @author ：chxiaofang
 * @date ：Created in 2019/8/27
 * @description ：单链表
 * @version: 1.0
 */
public class LinkedList {
    public static void main(String[] args) {
        Node node1 = new Node(1, "节点一");
        Node node2 = new Node(2, "节点二");
        Node node3 = new Node(3, "节点三");
        Node node4 = new Node(4, "节点四");

        // 创建一个链表
        SingleLinkedList linkedList = new SingleLinkedList();
        linkedList.addNode(node1);
        linkedList.addNode(node4);
        linkedList.addNode(node2);
        linkedList.addNode(node3);

        linkedList.list();
        System.out.println("===================");
        SingleLinkedList linkedList2 = new SingleLinkedList();
        linkedList2.addOrderByNode(new Node(1, "节点一"));
        linkedList2.addOrderByNode(new Node(4, "节点四"));
        linkedList2.addOrderByNode(new Node(3, "节点三"));
        linkedList2.addOrderByNode(new Node(2, "节点二"));
        System.out.println("===================");

        linkedList2.updateNode(new Node(4, "修改节点三"));
        linkedList2.list();
        System.out.println("===================");
        linkedList2.deleteNode(2);
        linkedList2.list();
        System.out.println("获取链表的总长度："+linkedList2.getSize());
        System.out.println("获取倒数倒数第k个节点的信息："+linkedList2.getKnode(2));
        linkedList2.reversetList();
        linkedList2.list();
        System.out.println("*************** ");
        linkedList2.recoverList();

    }

}

// 管理节点类
class SingleLinkedList {
    // 先创建头节点，头节点不能动
    private Node headNode = new Node(null);

    public Node getHeadNode() {
        return headNode;
    }

    // 添加节点到头节点
    public void addNode(Node node) {
        // 遍历链表，寻找最后一个节点,当循环退出时，temp就指向最后一个节点
        Node temp = headNode;
        while (true) {
            if (temp.getNextNode() == null) {
                break;
            }
            temp = temp.getNextNode();
        }
        temp.setNextNode(node);
    }

    // 按照顺序添加节点
    public void addOrderByNode(Node node) {
        Node temp = headNode;
        // 指定添加的节点编号是否存在
        boolean flag = false;
        while (true) {
            if (temp.getNextNode() == null) {
                break;
            }
            // 寻找节点的位置，在 temp 的后面插入，判断temp的后一个节点与新节点两者进行比较
            if (temp.getNextNode().getNodeId() > node.getNodeId()) {
                break;
            } else {
                if (temp.getNextNode().getNodeId() == node.getNodeId()) {
                    System.out.println("链表中已存在当前标号的节点！");
                    flag = true;
                    break;
                }
            }

            temp = temp.getNextNode();
        }

        if (flag) {
            System.out.println("节点编号已存在！");
        } else {
            // 将节点插入到链表中, 先将temp后的节点给node
            node.setNextNode(temp.getNextNode());
            temp.setNextNode(node);
        }
    }

    // 修改链表数据
    // 根据节点的id修改，若当前id不存在就新增
    public void updateNode(Node newNode) {
        boolean flag = false;
        if (headNode.getNextNode() == null) {
            addOrderByNode(newNode);
        }
        Node temp = headNode.getNextNode();
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.getNodeId() == newNode.getNodeId()) {
                flag = true;
                break;
            }
            temp = temp.getNextNode();
        }
        if (flag) {
            temp.setData(newNode.getData());
        } else {
            System.out.println("修改失败！");
        }
    }

    // 删除链表节点
    public Node deleteNode(int nodeId) {
        Node delete = new Node();
        if (headNode.getNextNode() == null) {
            throw new RuntimeException("链表为空？");
        }
        Node temp = headNode;
        while (true) {
            if (temp.getNextNode() == null) {
                break;
            } else {
                if (temp.getNextNode().getNodeId() == nodeId) {
                    delete = temp.getNextNode();
                    temp.setNextNode(temp.getNextNode().getNextNode());
                }
            }
            temp = temp.getNextNode();
        }
        return delete;
    }

    // 显示链表
    public void list() {
        if (headNode.getNextNode() == null) {
            throw new RuntimeException("链表为空");
        }
        Node temp = headNode.getNextNode();
        while (true) {
            // 此处判断temp是否为空
            if (temp == null) {
                break;
            }
            // 输出节点信息
            System.out.println(temp.toString());
            // 后移节点
            temp = temp.getNextNode();
        }
    }

    // 获取链表的节点个数
    public int getSize(){
        int size = 0;
        Node temp = headNode.getNextNode();
        while (true){
            if (headNode.getNextNode() == null){
                size = 0;
            }
            if (temp == null){
                break;
            }
            size++;
            temp = temp.getNextNode();
        }
        return size;
    }

    // 查找单链表的倒数第k个节点
    public Node getKnode(int k){
        if (headNode.getNextNode() == null){
            return null;
        }
        int size = getSize();
        Node temp = headNode.getNextNode();
        int i = 0;
        for (int j = 0; j < size - k; j++){
            temp = temp.getNextNode();
        }
        /**与for循环效果一样
            while(true){
                if (i < size-k){
                    i++;
                    temp = temp.getNextNode();
                }else{
                    break;
                }
            }
         */
        return temp;
    }

    // 逆置链表节点
    public void  reversetList(){
        Node reversetHead = new Node(null);
        //定义一个辅助的指针(变量)，帮助我们遍历原来的链表
        Node temp = headNode.getNextNode();
        // 指向当前节点[cur]的下一个节点
        Node tempNext = null;
        // 遍历原来的链表，每遍历一个节点，就将其取出，并放在新的链表 reverseHead 的最前端
        // 想保存temp的下一个节点，将新的头节点的next域赋值给temp的next，再将temp给新的头节点，
        while (temp != null){
            tempNext = temp.getNextNode();
            temp.setNextNode(reversetHead.getNextNode());
            reversetHead.setNextNode(temp);
            temp = tempNext;
        }
        headNode.setNextNode(reversetHead.getNextNode());
    }

    // 逆序遍历链表中的节点
    public void recoverList(){
        // 判断是否为空
        if (headNode.getNextNode() == null){
            System.out.println("链表为空！");
        }
        // 创建一个栈来进行存放节点
        Stack<Node> stack = new Stack<Node>();
        Node temp = headNode.getNextNode();
        while (temp != null){
            stack.push(temp);
            temp = temp.getNextNode();
        }
        // 将栈中的元素进行遍历
        while (stack.size() > 0){
            System.out.println(stack.pop());
        }
    }
}

// 节点类
class Node {
    private int nodeId;
    private String data;
    private Node nextNode;

    // 构造器

    public Node() {
    }

    public Node(String data) {
        this.data = data;
        this.nextNode = null;
    }

    public Node(int nodeId, String data) {
        this.nodeId = nodeId;
        this.data = data;
    }

    public int getNodeId() {
        return nodeId;
    }

    public void setNodeId(int nodeId) {
        this.nodeId = nodeId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Node getNextNode() {
        return nextNode;
    }

    public void setNextNode(Node nextNode) {
        this.nextNode = nextNode;
    }

    //

    @Override
    public String toString() {
        return "Node{" +
                "nodeId=" + nodeId +
                ", data='" + data + '\'' +
                '}';
    }
}