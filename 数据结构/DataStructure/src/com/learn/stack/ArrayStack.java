package com.learn.stack;

/**
 * @author Administrator
 */
public class ArrayStack<E> implements Stack<E> {
    DynamicArray<E> dynamicArray = null;

    public ArrayStack() {
        dynamicArray = new DynamicArray<>();
    }

    @Override
    public E peek() {
        return dynamicArray.getLast();
    }

    @Override
    public boolean isEmpty() {
        return dynamicArray.isEmpty();
    }

    @Override
    public E pop() {
        if (isEmpty()) {
            throw new IllegalArgumentException("栈元素为空");
        }
        return dynamicArray.removeLast();
    }

    @Override
    public void push(E data) {
        dynamicArray.addLast(data);
    }

    @Override
    public int getSize() {
        return dynamicArray.getSize();
    }

    @Override
    public String toString() {
        return "ArrayStack{" +
                "dynamicArray=" + dynamicArray +
                '}';
    }

    public static void main(String[] args) {
        ArrayStack<Integer> arrayStack = new ArrayStack<>();

        for (int i = 0; i < 10; i++) {
            arrayStack.push(i);
        }
        System.out.println(arrayStack);
        arrayStack.pop();
        System.out.println(arrayStack);
        System.out.println(arrayStack.peek());
        System.out.println(arrayStack.getSize());
        System.out.println(arrayStack.isEmpty());
    }
}
