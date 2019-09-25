package com.chxf.stack;

/**
 * @author ：chxiaofang
 * @date ：Created in 2019/8/29
 * @description ：中缀表达式
 * @version: 1.0
 */
public class ReversePolish {
    public static void main(String[] args) {
        String expression = "3+2*6-2";
        // 符号栈
        ArrayStack2 symbol = new ArrayStack2(10);
        // 数栈
        ArrayStack2 number = new ArrayStack2(10);
        // 扫描表达式的索引值
        int index = 0;
        // 保存需要计算的值
        int num1 = 0,num2 = 0,oper = 0,res = 0;
        // 保存扫描到的符号
        char ch = ' ';

        // 扫面字符传
        while (true){
            // 一次得到字符串中的字符
            ch = expression.substring(index,index+1).charAt(0);
            // 判断是否为符号
            if (number.isOper(ch)){
                // 判断符号栈是否为空,如果为空则直接进栈，不为空则进行比较
                if (symbol.isEmpty()){
                    symbol.push(ch);
                }else{
                    // 判断二个符号的优先级
                    System.out.println(symbol.priority(ch) <= symbol.priority(symbol.getTop()));
                    if (symbol.priority(ch) >= symbol.priority(symbol.getTop())){
                        // 取出要计算的值
                        num1 = number.pop();
                        num2 = number.pop();
                        oper = symbol.pop();
                        // 进行计算
                        res = symbol.cal(num1,num2,oper);
                        // 将计算结果res添加到数栈，并把保留的符号与剩余的符号栈中的符号进行比较
                        number.push(res);
                        symbol.push(ch);
                    }else{
                        symbol.push(ch);
                    }
                }
            }else{
                // 如果为数值则直接进入数值栈，
                number.push(ch - 48);
            }
            index++;
            if(index == expression.length()){
                break;
            }
        }

        // 计算栈中剩余的数值
        while(true){
            if (symbol.isEmpty()){
                break;
            }
            num1 = number.pop();
            num2 = number.pop();
            oper = symbol.pop();
            // 进行计算
            res = symbol.cal(num1,num2,oper);
            // 将计算结果res添加到数栈，并把保留的符号与剩余的符号栈中的符号进行比较
            number.push(res);
        }
        System.out.println(expression+"表达式结果为："+number.getTop());
    }
}

class ArrayStack2 {
    // 栈的最大值
    private int maxSize;
    // 栈中的数据
    private int[] data;
    // 栈顶指针
    private int top = -1;

    // 构造器
    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        data = new int[maxSize];
    }

    // 栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    // 栈空
    public boolean isEmpty() {
        return top == -1;
    }

    // 入栈操作
    public void push(int value) {
        if (isFull()) {
            return;
        }
        top++;
        data[top] = value;
    }

    // 出栈操作
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空");
        }
        int value = data[top];
        top--;
        return value;
    }

    // 获取栈顶元素
    public int getTop(){
        return data[top];
    }

    // 遍历栈的元素
    public void list() {
        int temp = top;
        while (true) {
            if (temp < 0) {
                return;
            }
            System.out.printf("%d\t", data[temp]);
            temp--;
        }
    }

    // 判断运算符的优先级,数字越小优先级越高
    public int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '-' || oper == '+') {
            return 2;
        } else {
            return -1;
        }
    }

    // 判断是否为运算符
    public boolean isOper(char value) {
        if (value == '+' || value == '-' || value == '*' || value == '/') {
            return true;
        }
        return false;
    }

    // 计算两者的值
    public int cal(int num1, int num2, int oper) {
        int result = 0;
        switch (oper) {
            case '+':
                result = num2 + num1;
                break;
            case '-':
                result = num2 - num1;
                break;
            case '*':
                result = num2 * num1;
                break;
            case '/':
                result = num2 / num1;
                break;
        }
        return result;
    }
}
