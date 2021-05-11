package binaryTree.binaryTreeTraversal;

/*
https://leetcode.com/problems/binary-tree-preorder-traversal/
 */
import dataStructure.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinaryTreePreorderTraversal {
    // Approach 1: traverse
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        traverse(result, root);

        return result;
    }

    private void traverse(List<Integer> result, TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }

        result.add(treeNode.val);
        traverse(result, treeNode.left);
        traverse(result, treeNode.right);
    }

    // Approach 2: divide & conquer
    public List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        result.add(root.val);
        result.addAll(preorderTraversal(root.left));
        result.addAll(preorderTraversal(root.right));

        return result;
    }

    // Approach 3: iteration
    public List<Integer> preorderTraversal3(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode treeNode = stack.pop();
            result.add(treeNode.val);
            if (treeNode.right != null) {
                stack.push(treeNode.right);
            }

            if (treeNode.left != null) {
                stack.push(treeNode.left);
            }
        }

        return result;
    }
}
