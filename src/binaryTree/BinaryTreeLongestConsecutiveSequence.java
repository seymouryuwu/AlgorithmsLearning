package binaryTree;

/*
https://leetcode.com/problems/binary-tree-longest-consecutive-sequence/
 */
import dataStructure.TreeNode;

public class BinaryTreeLongestConsecutiveSequence {
    private int longest = 0;

    public int longestConsecutive(TreeNode root) {
        longestWithRoot(root);

        return longest;
    }

    private int longestWithRoot(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = longestWithRoot(root.left);
        int right = longestWithRoot(root.right);

        if (root.left != null && root.val + 1 == root.left.val) {
            left += 1;
        } else {
            left = 1;
        }

        if (root.right != null && root.val + 1 == root.right.val) {
            right += 1;
        } else {
            right = 1;
        }

        int longestWithRoot = Math.max(left, right);
        longest = Math.max(longest, longestWithRoot);

        return longestWithRoot;
    }
}
