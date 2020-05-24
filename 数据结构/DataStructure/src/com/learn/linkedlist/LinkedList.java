package com.learn.linkedlist;

/**
 * @author Administrator
 */
public class LinkedList<E> {

    private class Node<E> {
        public E data;
        public Node next;

        public Node(E data, Node next) {
            this.data = data;
            this.next = next;
        }

        public Node(E data) {
            this(data, null);
        }

        public Node() {
            this(null, null);
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    '}';
        }
    }

    private Node dynamicHead;
    private int size;

    public LinkedList() {
        dynamicHead = new Node(null, null);
        this.size = 0;
    }


    public void add(int index, E data) {
        if(index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed. Illegal index.");
        }
        Node prev = dynamicHead;
        for(int i = 0 ; i < index ; i ++) {
            prev = prev.next;
        }
        prev.next = new Node(data, prev.next);
        size ++;
    }

    /**
     * 在链表头添加新的元素e
     */
    public void addFirst(E e) {
        add(0, e);
    }

    public void addLast(E data) {
        add(size, data);
    }

    /**
     * 更新索引上的值
     */
    public void update(int index, E data) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("更新的时候，索引不合法");
        }
        Node cur = dynamicHead.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        cur.data = data;
    }

    /**
     * 查找元素是否存在链表
     */
    public boolean contains(E data) {
        Node cur = dynamicHead.next;
        while (cur != null) {
            if (cur.data.equals(data)) {
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    
    public E remove(int index){
        if(index < 0 || index >= size) {
            throw new IllegalArgumentException("Remove failed. Index is illegal.");
        }
        Node prev = dynamicHead;
        for(int i = 0 ; i < index ; i ++) {
            prev = prev.next;
        }
        Node retNode = prev.next;
        prev.next = retNode.next;
        retNode.next = null;
        size --;
        return (E) retNode.data;
    }
    
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        Node cur = dynamicHead.next;
        while (cur != null) {
            res.append(cur.data + "-->");
            cur = cur.next;
        }
        res.append("NUll");
        return res.toString();
    }
}
