package com.learn.linkedlist.test.stack;

/**
 * @author Administrator
 */
public class Main {
    public static void main(String[] args) {
        LinkedStack<Integer> linkedStack = new LinkedStack<Integer>();
        for (int i = 0; i < 10; i++) {
            linkedStack.push(i);
        }
        System.out.println(linkedStack);
        linkedStack.pop();
        System.out.println(linkedStack);
        System.out.println(linkedStack.peek());
        System.out.println(linkedStack.isEmpty());
        System.out.println(linkedStack.getSize());

    }
}
