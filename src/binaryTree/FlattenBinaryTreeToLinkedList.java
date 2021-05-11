package binaryTree;

/*
https://leetcode.com/problems/flatten-binary-tree-to-linked-list/
 */
import dataStructure.TreeNode;

import java.util.Stack;

public class FlattenBinaryTreeToLinkedList {
    // Approach 1: divide & conquer
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }

        helper(root);
    }

    private TreeNode helper(TreeNode root) {
        TreeNode lastNode = root;
        TreeNode right = root.right;
        TreeNode left = root.left;
        if (left == null && right == null) {
            return lastNode;
        }

        if (left != null) {
            root.right = left;
            root.left = null;
            lastNode = helper(left);
        } else {
            lastNode = root;
        }

        if (right != null) {
            lastNode.right = right;
            lastNode = helper(right);
        }

        return lastNode;
    }

    // Approach 2: traverse
    private TreeNode lastNode = null;

    public void flatten2(TreeNode root) {
        if (root == null) {
            return;
        }

        if (lastNode != null) {
            lastNode.right = root;
            lastNode.left = null;
        }

        lastNode = root;
        TreeNode right = root.right;
        flatten(root.left);
        flatten(right);
    }

    // Approach 3: iteration
    public void flatten3(TreeNode root) {
        if (root == null) {
            return;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node.right != null) {
                stack.push(node.right);
            }

            if (node.left != null) {
                stack.push(node.left);
            }

            node.left = null;
            if (stack.isEmpty()) {
                node.right = null;
            } else {
                node.right = stack.peek();
            }
        }
    }
}
