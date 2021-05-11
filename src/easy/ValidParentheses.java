package easy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/*
Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

An input string is valid if:

1. Open brackets must be closed by the same type of brackets.
2. Open brackets must be closed in the correct order.

Example 1:

Input: s = "()"
Output: true

Example 2:

Input: s = "()[]{}"
Output: true

Example 3:

Input: s = "(]"
Output: false

Example 4:

Input: s = "([)]"
Output: false

Example 5:

Input: s = "{[]}"
Output: true


Constraints:

1 <= s.length <= 104
s consists of parentheses only '()[]{}'.
 */
public class ValidParentheses {
    public boolean isValid(String s) {
        ArrayList<String> openParentheses = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            String checkedChar = String.valueOf(s.charAt(i));
            if (checkedChar.equals("(")) openParentheses.add("(");
            if (checkedChar.equals("{")) openParentheses.add("{");
            if (checkedChar.equals("[")) openParentheses.add("[");

            if (checkedChar.equals(")")) {
                if (openParentheses.isEmpty()) return false;
                if (!openParentheses.remove(openParentheses.size()-1).equals("(")) return false;
            }
            if (checkedChar.equals("}")) {
                if (openParentheses.isEmpty()) return false;
                if (!openParentheses.remove(openParentheses.size()-1).equals("{")) return false;
            }
            if (checkedChar.equals("]")) {
                if (openParentheses.isEmpty()) return false;
                if (!openParentheses.remove(openParentheses.size()-1).equals("[")) return false;
            }
        }
        return openParentheses.isEmpty();
    }

    public boolean isValid2(String s) {
        Map<Character, Character> parenthesesMap = new HashMap<>();
        parenthesesMap.put(')', '(');
        parenthesesMap.put('}', '{');
        parenthesesMap.put(']', '[');

        Stack<Character> openParentheses = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char checkedChar = s.charAt(i);

            if (parenthesesMap.containsKey(checkedChar)) {
                if (openParentheses.isEmpty() || openParentheses.pop() != parenthesesMap.get(checkedChar)) return false;
            } else {
                openParentheses.push(checkedChar);
            }
        }
        return openParentheses.isEmpty();
    }
}
