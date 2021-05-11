package binaryTree.binaryTreeTraversal;

/*
https://leetcode.com/problems/binary-tree-inorder-traversal/
 */
import dataStructure.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinaryTreeInorderTraversal {
    // Approach 1: traverse
    public List<Integer> inorderTraversal(TreeNode root) {
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

        traverse(result, treeNode.left);
        result.add(treeNode.val);
        traverse(result, treeNode.right);
    }

    // Approach 2: divide & conquer
    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        result.addAll(inorderTraversal(root.left));
        result.add(root.val);
        result.addAll(inorderTraversal(root.right));

        return result;
    }

    // Approach 3: iteration
    public List <Integer> inorderTraversal3(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            result.add(curr.val);
            curr = curr.right;
        }
        return result;
    }
}
