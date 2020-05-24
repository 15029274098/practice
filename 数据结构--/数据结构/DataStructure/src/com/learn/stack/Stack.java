package com.learn.stack;

/**
 * @author Administrator
 */
public interface Stack<E> {
    E peek();

    boolean isEmpty();

    E pop();

    void push(E data);

    int getSize();
}
