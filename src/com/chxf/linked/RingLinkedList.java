package com.chxf.linked;

/**
 * @author ：chxiaofang
 * @date ：Created in 2019/8/28
 * @description ：循环单链表
 * @version: 1.0
 */
public class RingLinkedList {
    public static void main(String[] args) {
        SingleRingLinked linked = new SingleRingLinked();
        linked.addNode(new RingLink(1, "节点一"));
        linked.addNode(new RingLink(2, "节点二"));
        linked.addNode(new RingLink(3, "节点三"));
        linked.addNode(new RingLink(4, "节点四"));
        linked.addNode(new RingLink(5, "节点五"));
        System.out.println("添加后的数据：");
        linked.list();
        System.out.println("删除后的数据：");
        linked.deleteNode(4);
        linked.list();
        System.out.println("修改后的数据：");
        linked.updateNode(new RingLink(5,"！！！"));
        linked.list();
    }
}

// 链表管理类
class SingleRingLinked {
    private RingLink first = null;

    RingLink temp = null;

    // 添加节点
    public void addNode(RingLink node) {
        if (first == null) {
            first = node;
            first.next = first;
            temp = first;
        } else {
            temp.next = node;
            node.next = first;
            temp = node;
        }
    }

    // 删除节点
    public void deleteNode(int no){
        RingLink temp = first;
        while (true){
            if (temp.next.no == no) {
                temp.next = temp.next.next;
                break;
            }
            temp = temp.next;
        }
    }

    // 修改节点
    public void updateNode(RingLink node){
        RingLink temp = first.next;
        while (true){
            if (temp.no == node.no){
                temp.data = node.data;
                break;
            }
            temp = temp.next;
        }
    }

    // 遍历循环单链表
    public void list() {
        if (first == null) {
            System.out.println("链表为空无数据输出！");
            return;
        }
        RingLink temp = first;
        while (true) {
            System.out.println(temp.toString());
            if (temp.next == first){
                break;
            }
            temp = temp.next;
        }
    }
}

// 节点类
class RingLink {

    public int no;

    public String data;

    public RingLink next;

    // 构造器
    public RingLink() {
        this.next = null;
    }

    public RingLink(String data) {
        this.data = data;
        this.next = null;
    }

    public RingLink(int no, String data) {
        this.no = no;
        this.data = data;
        this.next = null;
    }

    @Override
    public String toString() {
        return "RingLink{" +
                "no=" + no +
                ", data='" + data + '\'' +
                '}';
    }

}
