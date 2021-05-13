package stack;
/*
https://leetcode.com/problems/decode-string/
 */

import java.util.Stack;

public class DecodeString {
    public String decodeString(String s) {
        if (s == null) {
            return "";
        }

        Stack<Object> stack = new Stack<>();
        int number = 0;
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                number = number * 10 + (c - '0');
            } else if (c == '[') {
                stack.push(number);
                number = 0;
            } else if (Character.isAlphabetic(c)) {
                stack.push(c);
            } else {
                StringBuilder sb = new StringBuilder();
                while (!(stack.peek() instanceof Integer)) {
                    sb.insert(0, stack.pop());
                }

                Integer times = (Integer)stack.pop();
                stack.push(sb.toString().repeat(times));
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.insert(0, stack.pop());
        }

        return sb.toString();
    }
}
