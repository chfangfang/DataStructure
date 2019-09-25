package com.chxf.stack;

/**
 * @author ：chxiaofang
 * @date ：Created in 2019/8/29
 * @description ：
 * @version: 1.0
 */
public class linkedModelStack {
    public static void main(String[] args) {
        LinkeStack linkeStack = new LinkeStack(5);
        linkeStack.push(new Node(1,"栈1"));
        linkeStack.push(new Node(2,"栈2"));
        linkeStack.push(new Node(3,"栈3"));
        linkeStack.push(new Node(4,"栈4"));
        linkeStack.push(new Node(5,"栈5"));
        System.out.println("栈内元素：");
        linkeStack.list();
        System.out.println("出栈："+linkeStack.pop());
        System.out.println("出栈："+linkeStack.pop());
        linkeStack.push(new Node(6,"栈6"));
        linkeStack.push(new Node(7,"栈7"));
        System.out.println("栈内元素：");
        linkeStack.list();
        System.out.println("出栈："+linkeStack.pop());

    }
}
// 链式栈类
class LinkeStack{
    // 栈长度，栈顶指针，链表数据
    private int maxSize;
    private linked data = new linked();
    private int top = -1;

    // 构造器
    public LinkeStack(int maxSize) {
        this.maxSize = maxSize;
    }

    // 定义栈满
    public boolean isFull(){
        return top == maxSize-1;
    }

    // 定义栈空
    public boolean isEmpty(){
        return top == -1;
    }

    // 定义入栈操作
    public void push(Node node){
        if (isFull()){
            return;
        }
        top++;
        data.addNode(node, top);
    }

    // 定义出栈操作
    public Node pop(){
        if (isEmpty()) {
            return null;
        }
        Node value = data.getNode(top);
        top--;
        return  value;
    }

    // 遍历栈内元素
    public void list(){
        int temp = top;
        while (true){
            if (temp < 0){
                return;
            }
            System.out.println(data.getNode(temp));
            temp--;
        }
    }
}

// 链表管理类
class linked{
    Node headNode = new Node();

    // 添加节点
    public void addNode(Node node,int top){
        Node temp = headNode;
        for (int i = 0;i < top; i++){
            temp = temp.next;
        }
        temp.next = node;
    }

    // 获取节点
    public Node getNode(int top){
        Node temp = headNode;
        for (int i = 0;i <= top; i++){
            temp = temp.next;
        }
        return  temp;
    }
}

// 节点类
class Node{
    public int no;
    public String data;
    public Node next;

    // 构造方法
    public Node() {
        this.next = null;
    }

    public Node(int no, String data) {
        this.no = no;
        this.data = data;
        this.next = null;
    }

    @Override
    public String toString() {
        return "Node{" +
                "no=" + no +
                ", data='" + data + '\'' +
                '}';
    }
}
