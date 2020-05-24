package com.learn;

import com.learn.linkedlist.LinkedList;

/**
 * @author Administrator
 */
public class TestMain {
    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < 5; i++) {
            linkedList.add(i, i);
            System.out.println(linkedList);
        }
        linkedList.addLast(5);
        System.out.println(linkedList);
        System.out.println(linkedList.contains(4));
        System.out.println(linkedList.remove(4));
        System.out.println(linkedList);
        System.out.println(linkedList.contains(4));
    }
}
