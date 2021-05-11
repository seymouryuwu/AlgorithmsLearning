package binaryTree;

/*
https://leetcode.com/problems/binary-tree-paths/
 */
import dataStructure.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreePaths {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        if (root.left == null && root.right == null) {
            result.add(root.val + "");
            return result;
        }

        for (String path : binaryTreePaths(root.left)) {
            result.add(root.val + "->" + path);
        }

        for (String path : binaryTreePaths(root.right)) {
            result.add(root.val + "->" + path);
        }

        return result;
    }

    // Approach 2: traverse
    public List<String> binaryTreePaths2(TreeNode root) {
        List<String> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        helper(result, root, root.val + "");

        return result;
    }

    private void helper(List<String> result, TreeNode root, String path) {
        if (root == null) {
            return;
        }

        if (root.left == null && root.right == null) {
            result.add(path);
            return;
        }

        if (root.left != null) {
            String newPath = path + "->" + root.left.val;
            helper(result, root.left, newPath);
        }

        if (root.right != null) {
            String newPath = path + "->" + root.right.val;
            helper(result, root.right, newPath);
        }
    }
}
