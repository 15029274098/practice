package com.learn.queue;

/**
 * @author Administrator
 */
public class ArrayQueue<E> implements Queue<E> {
    DynamicArray<E> dynamicArray = null;

    public ArrayQueue() {
        dynamicArray = new DynamicArray<>();
    }

    @Override
    public boolean isEmpty() {
        return dynamicArray.isEmpty();
    }

    @Override
    public int getSize() {
        return dynamicArray.getSize();
    }

    @Override
    public void enqueue(E data) {
        dynamicArray.addLast(data);
    }

    @Override
    public void dequeue() {
        if (isEmpty()) {
            throw new IllegalArgumentException("队列为空");
        }
        dynamicArray.removeFirst();
    }

    @Override
    public E getFront() {
        return dynamicArray.getFirst();
    }

    @Override
    public String toString() {
        return "ArrayQueue{" +
                "dynamicArray=" + dynamicArray +
                '}';
    }

    public static void main(String[] args) {
        ArrayQueue<Integer> arrayQueue = new ArrayQueue<>();
        for (int i = 0; i < 10; i++) {
            arrayQueue.enqueue(i);
        }
        System.out.println(arrayQueue);
        arrayQueue.dequeue();
        System.out.println(arrayQueue);
        System.out.println(arrayQueue.getSize());
        System.out.println(arrayQueue.isEmpty());
        System.out.println(arrayQueue.getFront());
    }
}
