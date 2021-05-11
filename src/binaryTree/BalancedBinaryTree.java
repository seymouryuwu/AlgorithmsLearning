package binaryTree;

/*
https://leetcode.com/problems/balanced-binary-tree/
 */
import dataStructure.TreeNode;

public class BalancedBinaryTree {
    private class ReturnType {
        public boolean isBalanced;
        public int maxDepth;
        public ReturnType(boolean isBalanced, int maxDepth) {
            this.isBalanced = isBalanced;
            this.maxDepth = maxDepth;
        }
    }

    public boolean isBalanced(TreeNode root) {
        return helper(root).isBalanced;
    }

    private ReturnType helper(TreeNode root) {
        if (root == null) {
            return new ReturnType(true, 0);
        }

        ReturnType left = helper(root.left);
        ReturnType right = helper(root.right);

        boolean isBalanced =
                left.isBalanced &&
                        right.isBalanced &&
                        (Math.abs(left.maxDepth - right.maxDepth) <= 1);

        if (isBalanced) {
            int maxDepth = 1 + Math.max(left.maxDepth, right.maxDepth);
            return new ReturnType(isBalanced, maxDepth);
        }

        return new ReturnType(false, -1);
    }
}
