package com.learn.linkedlist.test1;

/**
 * @author Administrator
 */
public class LinkList<E> {
    private class Node {
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
    }

    public Node dynamicHead;
    public int size;

    public LinkList() {
        this.dynamicHead = new Node();
        this.size = 0;
    }


    public void add(int index, E data) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("index is illegal");
        }
        Node prev = dynamicHead;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
//        Node node = new Node(data);
//        prev.next = node.next;
//        prev.next = node;
        prev.next = new Node(data, prev.next);
        size++;
    }

    public void addLast(E data) {
        add(size, data);
    }

    public void addFirst(E data) {
        add(0, data);
    }

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

    public void update(int index, E data) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("index is illegal");
        }
        Node cur = dynamicHead.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        cur.data = data;
    }

    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("index is illegal");
        }
        Node prev = dynamicHead;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        Node cur = prev.next;
        prev.next = cur.next;
        cur.next = null;
        return cur.data;
    }
    public E removeLast() {
        return remove(size - 1);
    }

    public E removeFirst() {
        return remove(0);
    }
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        Node cur = dynamicHead.next;
        while (cur != null) {
            res.append(cur.data + "-->");
            cur = cur.next;
        }
        res.append("NULL");
        return res.toString();
    }
}
