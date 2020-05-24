package com.learn.linkedlist.test.stack;

import com.learn.linkedlist.test.LinkedList;

/**
 * @author Administrator
 */
public class LinkedStack<E> implements Stack{
    LinkedList<E> linkedList= null;
    public LinkedStack(){
        linkedList= new LinkedList<>();
    }

    @Override
    public void push(Object data) {
        linkedList.addFirst((E) data);
    }


    @Override
    public void pop() {
        linkedList.removeFirst();
    }

    @Override
    public E peek() {
        return linkedList.getFirst();
    }

    @Override
    public int getSize() {
        return linkedList.size;
    }

    @Override
    public boolean isEmpty() {
        return linkedList.isEmpty();
    }

    @Override
    public String toString() {
        return "LinkedStack{" +
                "linkedList=" + linkedList +
                '}';
    }
}
