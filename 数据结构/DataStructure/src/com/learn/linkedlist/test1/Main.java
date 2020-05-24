package com.learn.linkedlist.test1;

/**
 * @author Administrator
 */
public class Main {
    public static void main(String[] args) {
        LinkList<Integer> linkList = new  LinkList<Integer>();
        for (int i = 0; i < 5; i++) {
            linkList.add(i, i);
        }
        System.out.println("添加元素：" + linkList);
        linkList.addFirst(10);
        System.out.println("首部添加元素：" + linkList);
        linkList.addLast(100);
        System.out.println("尾部添加元素：" + linkList);
        linkList.update(6,10);
        System.out.println("更新元素：" + linkList);
        linkList.removeFirst();
        System.out.println("删除元素：" + linkList);
        System.out.println("包含元素：" + linkList.contains(3));
    }
}
