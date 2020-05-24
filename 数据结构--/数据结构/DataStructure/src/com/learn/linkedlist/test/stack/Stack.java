package com.learn.linkedlist.test.stack;

/**
 * @author Administrator
 */
public interface Stack<E> {
    void push(E data);

    void pop();

    E peek();

    int getSize();

    boolean isEmpty();
}
