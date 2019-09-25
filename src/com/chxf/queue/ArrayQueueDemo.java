package com.chxf.queue;

/**
 * @author ：chxiaofang
 * @date ：Created in 2019/8/26
 * @description ：用户模拟队列
 * @version: 1.0
 */
public class ArrayQueueDemo {
    public static void main(String[] args) {

        // 创建队列
        ArrayQueue queue = new ArrayQueue(5);
        queue.addQueue(1);
        queue.addQueue(2);
        queue.addQueue(3);
        queue.addQueue(4);
        queue.addQueue(5);
        System.out.println("遍历队列：");
        queue.showQueue();
        System.out.println("获取队列元素"+queue.getQueue());
        System.out.println("获取队列元素"+queue.getQueue());
        System.out.println("获取队列元素"+queue.getQueue());
        System.out.println("遍历队列：");
        queue.showQueue();

    }
}

/**
 * 使用数组模拟队列，队列类
 */
class ArrayQueue{
    // 表示队列的最大容量
    private int maxSize;
    // 队列头
    private int front;
    // 队列尾
    private int rear;
    // 存放队列的数据，数组模拟的队列
    private int[] arr;
    // 队列的数据个数
    private int num;

    //创建构造器
    public ArrayQueue(int maxSize){
        this.maxSize = maxSize;
        // 初始化队列
        arr = new int[maxSize];
        front = -1; // 指向队列的对头；fornt是指向对头的前一个位置
        rear = -1; // 指向队列的队尾，rear是指向队列的最后一个元素
        num = 0;
    }

    // 判断队列是否已满
    public boolean isFull(){
        return  front == maxSize-1;
    }

    // 判断队列是否为空
    public boolean isEmpty(){
        return num == 0;
    }

    // 添加数据到队列
    public String addQueue(int data){
        if (isFull()){
            return "队列信息已满！";
        }
        rear++;
        arr[rear] = data;
        num++;
        return "数据添加成功";
    }

    // 获取队列的数据
    public int getQueue(){
        if (isEmpty()){
            throw new RuntimeException("数据为空！");
        }
        front++;
        num--;
        return arr[front];
    }

    // 显示队列的所有数据
    public void showQueue(){
        if(isEmpty()){
            throw new RuntimeException("数据为空");
        }
        System.out.println(num);
        for (int i = front+1; i<maxSize;i++){
            System.out.printf("%d\t",arr[i]);
        }
    }

    // 显示对头元素
    public int showHeard(){
        if(isEmpty()){
            throw new RuntimeException("队列数据为空！");
        }
        return arr[front+1];
    }
}