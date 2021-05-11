package binaryTree;

/*
https://leetcode.com/problems/maximum-average-subtree/
 */
import dataStructure.TreeNode;

public class MaximumAverageSubtree {
    private double maxAvg = Double.MIN_VALUE;

    private class ReturnType {
        public int sum;
        public int number;
        public ReturnType (int sum, int number) {
            this.sum = sum;
            this.number = number;
        }
    }

    public double maximumAverageSubtree(TreeNode root) {
        helper(root);

        return maxAvg;
    }

    private ReturnType helper(TreeNode root) {
        if (root == null) {
            return new ReturnType(0, 0);
        }

        ReturnType left = helper(root.left);
        ReturnType right = helper(root.right);

        int sum = root.val + left.sum + right.sum;
        int number = 1 + left.number + right.number;

        maxAvg = Math.max(maxAvg, (double)sum / number);

        return new ReturnType(sum, number);
    }
}
