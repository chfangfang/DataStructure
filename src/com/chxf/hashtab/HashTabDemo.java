package com.chxf.hashtab;

/**
 * @author ：chxiaofang
 * @date ：Created in 2019/9/14
 * @description ：
 * @version: 1.0
 */
public class HashTabDemo {
    public static void main(String[] args) {
        HashTab hashTab = new HashTab(10);
        hashTab.add(new Emp(1,"诸葛亮"));
        hashTab.add(new Emp(2,"郭嘉"));
        hashTab.add(new Emp(3,"荀彧"));
        hashTab.add(new Emp(4,"司马懿"));
        hashTab.add(new Emp(5,"陆逊"));
        hashTab.add(new Emp(6,"周瑜"));

        hashTab.list();

        System.out.println(hashTab.getEmp(5));
    }
}

class HashTab{
    private EmpLinkList[] lists;

    private int size;// 多少条链表
    public HashTab(int size) {
        // 初始化
        this.size = size;
        this.lists = new EmpLinkList[size];
        //？留一个坑, 这时不要分别初始化每个链表
        for(int i = 0; i < size; i++) {
            lists[i] = new EmpLinkList();
        }
    }

    // 添加方法
    public void add(Emp emp){
        int listNo = hashFun(emp.id);
        // 加入到对应的链表
        lists[listNo].addEmp(emp);
    }

    // 查找的方法
    public Emp getEmp(int id){
        int listNo = hashFun(id);
        return lists[listNo].getEmp(id);
    }

    // 遍历哈希表的方法
    public void list(){
        for (int i = 0 ; i < lists.length; i++){
            lists[i].list();
        }
    }

    // 散列函数，使用取模函数
    public int hashFun(int id){
        return id % size;
    }

}
// 员工
class Emp{
    public int id;
    public String name;
    public Emp next;

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
        this.next = null;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

// 创建EmpLinkList，表示链表
class EmpLinkList{
    // 头指针是直接指向第一个节点
    private Emp head;
    // 添加雇员信息，将雇员直接添加到链表最后
    public void addEmp(Emp emp){
        // 判断是否添加到第一个位置
        if (head == null) {
            head = emp;
            return;
        }
        // 如果不是第一个位置，则需要一个辅助指针
        Emp temp = head;
        // 遍历链表
        while (true) {
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        // 退出时直接将emp加入到链表
        temp.next = emp;
    }

    // 遍历链表
    public void list(){
        Emp temp = head;
        if (temp == null){
            System.out.println("当前链表为空！！！");
            return;
        }
        while (true){
            if (temp == null) {
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }
    }

    // 获取某节点的信息
    public Emp getEmp(int id){
        Emp temp = head;

        while (true){
            if (temp == null) {
                System.out.println("当前链表为空！！！");
                return null;            }
            if (temp.id == id){
                return temp;
            }
            temp = temp.next;
        }
    }
}