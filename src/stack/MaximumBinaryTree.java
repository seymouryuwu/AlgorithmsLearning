package stack;
/*
https://leetcode.com/problems/maximum-binary-tree/
 */

import dataStructure.TreeNode;

import java.util.LinkedList;
import java.util.Stack;

public class MaximumBinaryTree {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }

        // pop out num that less than me.
        // stack stores the TreeNode
        Stack<TreeNode> stack = new Stack<>();
        for (int i = 0; i <= nums.length; i++) {
            int num = (i == nums.length) ? Integer.MAX_VALUE : nums[i];
            TreeNode right = new TreeNode(num);
            while (!stack.isEmpty() && stack.peek().val < num) {
                TreeNode child = stack.pop();
                TreeNode left = (stack.isEmpty()) ? null : stack.peek();
                if (left == null) {
                    right.left = child;
                } else {
                    if (left.val < right.val) {
                        left.right = child;
                    } else {
                        right.left = child;
                    }
                }
            }

            stack.push(right);
        }

        return stack.pop().left;
    }

    // same approach,
    // but always assign the right side candidate parent to curr when curr is pushed in stack,
    // when curr's left side candidate parent is pushed in, change it if it's necessary.
    public TreeNode constructMaximumBinaryTree2(int[] nums) {
        // because when return the root we need the bottom TreeNode in the stack, so we use LinkedList here
        LinkedList<TreeNode> stack = new LinkedList<>();

        for (int num: nums){
            TreeNode curr = new TreeNode(num);
            while (!stack.isEmpty() && stack.peekFirst().val < curr.val){
                curr.left = stack.pop();
            }

            if (!stack.isEmpty()){
                stack.peekFirst().right = curr;
            }
            stack.push(curr);
        }

        return stack.peekLast();
    }
}
