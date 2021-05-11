package binaryTree.binaryTreeTraversal;

import dataStructure.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreePostorderTraversal {
    // Approach 1: traverse
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        traverse(result, root);

        return result;
    }

    private void traverse(List<Integer> result, TreeNode root) {
        if (root == null) {
            return;
        }

        traverse(result, root.left);
        traverse(result, root.right);
        result.add(root.val);
    }

    // Approach 2: divide & conquer
    public List<Integer> postorderTraversal2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        result.addAll(postorderTraversal(root.left));
        result.addAll(postorderTraversal(root.right));
        result.add(root.val);

        return result;
    }

    // Approach 3: iteration
    // Use Deque, to learn later
}
