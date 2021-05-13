package stack;
/*
https://leetcode.com/problems/min-stack/
 */

import java.util.Stack;

public class MinStack {
    private Stack<Integer> myStack;
    // minStack stores the min number from this position to the bottom of the stack
    private Stack<Integer> minStack;

    /** initialize your data structure here. */
    public MinStack() {
        myStack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int val) {
        myStack.push(val);
        int min = (minStack.isEmpty()) ? val : Math.min(val, minStack.peek());
        minStack.push(min);
    }

    public void pop() {
        myStack.pop();
        minStack.pop();
    }

    public int top() {
        return myStack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}
