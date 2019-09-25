package com.chxf.linked;

/**
 * @author ：chxiaofang
 * @date ：Created in 2019/8/28
 * @description ：
 * @version: 1.0
 */
public class DoubleLinkedList {
    public static void main(String[] args) {

        SingleDoubleLinked linked = new SingleDoubleLinked();
        linked.addNode(new DoubleNode(1,"节点一"));
        linked.addNode(new DoubleNode(2,"节点二"));
        linked.addNode(new DoubleNode(3,"节点三"));
        linked.addNode(new DoubleNode(4,"节点四"));
        System.out.println("添加后的链表情况：");
        linked.list();
        linked.updateNode(new DoubleNode(4,"修改节点四"));
        System.out.println("修改后的链表情况：");
        linked.list();
        linked.deleteNode(2);
        System.out.println("删除后的链表情况：");
        linked.list();
    }
}

// 链表类
class SingleDoubleLinked{
    // 创建头节点
    private DoubleNode headNode = new DoubleNode();

    // 添加节点
    public void addNode(DoubleNode doubleNode){
        DoubleNode temp = headNode;
        while (temp.next != null){
            temp = temp.next;
        }
        temp.next = doubleNode;
        doubleNode.pre = temp;
    }

    // 删除节点
    public void deleteNode(int nodeid){

        if (headNode.next == null){
            System.out.println("链表为空，无法删除!");
            return;
        }
        DoubleNode temp = headNode.next;
        boolean flag = false;
        while (true){
            if (temp == null){
                break;
            }
            if (temp.nodeId == nodeid){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag){
            temp.pre.next = temp.next;
            //判断是否为最后的节点，若是则让next等于null
            if (temp.next != null) {
                temp.next.pre = temp.pre.next;
            }else{
                temp.next = null;
            }
        }
    }
    // 修改节点
    public void updateNode(DoubleNode doubleNode){
        if (headNode.next == null){
            System.out.println("链表数据为空，无法执行修改操作");
            return;
        }
        DoubleNode temp = headNode.next;
        while(true){
            if (temp == null){
                break;
            }
            if (temp.nodeId == doubleNode.nodeId){
                temp.data = doubleNode.data;
                break;
            }
            temp = temp.next;
        }
    }

    // 显示链表节点
    public void list(){
        DoubleNode temp = headNode.next;
        while (true){
            if (temp == null){
                break;
            }
            System.out.println(temp.toString());
            temp = temp.next;
        }
    }

}

// 链表节点类
class DoubleNode{

    public int nodeId;

    public String data;

    public DoubleNode pre;

    public DoubleNode next;

    // 构造器
    public DoubleNode() {
        this.next = null;
        this.pre = null;
        this.data = null;
    }

    public DoubleNode(int nodeId, String data) {
        this.nodeId = nodeId;
        this.data = data;
        this.next = null;
        this.pre = null;

    }

    public DoubleNode(String data) {
        this.data = data;
        this.next = null;
        this.pre = null;

    }

    @Override
    public String toString() {
        return "DoubleLinked{" +
                "nodeId=" + nodeId +
                ", data='" + data + '\'' +
                '}';
    }
}
