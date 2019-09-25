package com.chxf.queue;

/**
 * @author ：chxiaofang
 * @date ：Created in 2019/8/26
 * @description ：循环队列
 * @version: 1.0
 */
public class CircleArrayQueueDemo {
    public static void main(String[] args) {
        CircleArrayQueue queue = new CircleArrayQueue(6);
        System.out.println(queue.addQueue(1));
        System.out.println(queue.addQueue(2));
        System.out.println(queue.addQueue(3));
        System.out.println(queue.addQueue(4));
        System.out.println(queue.addQueue(5));
        System.out.println("遍历队列：");
        queue.showQueue();
        System.out.println("获取队列元素"+queue.getQueue());
        System.out.println("获取队列元素"+queue.getQueue());
        System.out.println("获取队列元素"+queue.getQueue());
        System.out.println("遍历队列：");
        queue.showQueue();
        System.out.println(queue.addQueue(6));
        System.out.println(queue.addQueue(7));
        System.out.println(queue.addQueue(8));
        System.out.println(queue.addQueue(9));
        System.out.println(queue.addQueue(10));
        System.out.println("遍历队列：");
        queue.showQueue();

    }
}

/**
 * 循环队列
 */
class CircleArrayQueue{

    // 队列的最大容量
    private int maxSize;
    // 队列的队头,队尾指针
    private int front;
    private int rear;
    // 存放队列的数据
    private int[] arr;

    // 队列的构造器,初始化
    public  CircleArrayQueue(int maxSize){
        this.maxSize = maxSize;
        arr = new int[maxSize];
        front = 0;
        rear = 0;
    }

    // 判断队列是否满
    public boolean isFull(){
        return (rear+1) % maxSize == front;
    }

    // 判断队列是否为空
    public boolean isEmpty(){
        return front == rear;
    }

    // 添加数据至队列
    public String addQueue(int data){
        if (isFull()){
            return "队列已满";
        }
        arr[rear] = data;
        rear = (rear + 1) % maxSize;
        return "数据添加成功！";
    }

    // 获取队列数据，出队列
    public int getQueue(){
        if (isEmpty()){
            throw  new RuntimeException("数据为空");
        }
        // 这里需要分析出 front 是指向队列的第一个元素
        // 1. 先把 front 对应的值保留到一个临时变量
        // 2. 将 front 后移, 考虑取模
        // 3. 将临时保存的变量返回
        int data = arr[front];
        front = (front + 1) % maxSize;
        return data;
    }

    // 显示队列中的元素
    public void showQueue(){
        if (isEmpty()){
            throw  new RuntimeException("队列数据为空！");
        }
        // 遍历队列元素
        for (int i = front;i < (front+size());i++){
            System.out.printf("%d\t",arr[i % maxSize]);
        }
    }

    // 获取队列中的有效数据个数
    public int size(){
        return (rear + maxSize - front) % maxSize;
    }

}