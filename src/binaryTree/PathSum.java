package binaryTree;

/*
https://leetcode.com/problems/path-sum/
 */
import dataStructure.TreeNode;

public class PathSum {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }

        if (root.left == null && root.right == null) {
            return (root.val == targetSum);
        }

        boolean left = false;
        if (root.left != null) {
            left = hasPathSum(root.left, targetSum - root.val);
        }

        boolean right = false;
        if (root.right != null) {
            right = hasPathSum(root.right, targetSum - root.val);
        }

        return (left || right);
    }
}
