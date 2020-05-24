package com.learn.stack.leetcode;


import java.util.Stack;

/**
 * @author Administrator
 */
class Solution {

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '{' || c == '[' || c == '(') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                char charTop = stack.pop();
                if (c == '}' && charTop != '{') {
                    return false;
                }
                if (c == ']' && charTop != '[') {
                    return false;
                }
                if (c == ')' && charTop != '(') {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(new Solution().isValid("{[]}"));
    }

}



