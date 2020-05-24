package com.learn.linkedlist.test;

/**
 * @author Administrator
 */
public class LinkedList<E> {
    // Node节点
    private class Node {
        // 数据域
        public E data;
        // 指针
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

    public LinkedList() {
        this.dynamicHead = new Node();
        this.size = 0;
    }

    /**
     * 添加元素
     *
     * @param index 索引
     * @param data  元素
     */
    public void add(int index, E data) {
        // index 不判断等于size,因为需要考虑尾节点插入的情况
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
        // 等价于上面的三句话
        prev.next = new Node(data, prev.next);
        size++;
    }

    public void addLast(E data) {
        add(size, data);
    }

    public void addFirst(E data) {
        add(0, data);
    }

    /**
     * 依次遍历节点，节点指针指向空的时候，说明遍历完了
     *
     * @param data 元素
     * @return false true
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

    /**
     * 更新索引
     *
     * @param index 索引
     * @param data  元素
     */
    public void update(int index, E data) {
        // index需要判断等于size
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("index is illegal");
        }
        Node cur = dynamicHead.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        cur.data = data;
    }

    /**
     * 删除指定索引
     *
     * @param index 索引
     * @return 删除元素
     */
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
        size--;
        return cur.data;
    }

    public E removeLast() {
        return remove(size - 1);
    }

    public E removeFirst() {
        return remove(0);
    }

    /**
     * 获取指定索引的值
     *
     * @param index 索引
     * @return 元素
     */
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("index is illegal");
        }
        Node cur = dynamicHead.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur.data;
    }


    public E getFirst() {
        return get(0);
    }


    public E getLast() {
        return get(size - 1);
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
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
