package com.learn.queue;

/**
 * @author Administrator
 */
public interface Queue<E> {
    boolean isEmpty();

    int getSize();

    void enqueue(E data);

    void dequeue();

    E getFront();
}
