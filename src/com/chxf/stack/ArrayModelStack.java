package com.chxf.stack;

/**
 * @author ：chxiaofang
 * @date ：Created in 2019/8/29
 * @description ：采用数组模拟栈结构
 * Java中有自带的Stack类
 * @version: 1.0
 */
public class ArrayModelStack {
    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(5);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.list();
        System.out.println("出栈："+stack.pop());
    }
}

class ArrayStack{
    // 栈的最大值
    private int maxSize;
    // 栈中的数据
    private int[] data;
    // 栈顶指针
    private int top = -1;

    // 构造器
    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        data = new int[maxSize];
    }

    // 栈满
    public boolean isFull(){
        return top == maxSize-1;
    }

    // 栈空
    public boolean isEmpty(){
        return  top == -1;
    }

    // 入栈操作
    public void push(int value){
        if (isFull()){
            return;
        }
        top++;
        data[top] = value;
    }

    // 出栈操作
    public int pop(){
        if (isEmpty()){
            throw new RuntimeException("栈空");
        }
        int value = data[top];
        top--;
        return  value;
    }

    // 遍历栈的元素
    public void list(){
        int temp = top;
        while (true){
            if (temp < 0){
                return;
            }
            System.out.printf("%d\t",data[temp]);
            temp--;
        }
    }
}
