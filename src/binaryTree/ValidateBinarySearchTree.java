package binaryTree;

/*
https://leetcode.com/problems/validate-binary-search-tree/
 */
import dataStructure.TreeNode;

public class ValidateBinarySearchTree {
    private class ReturnType {
        public boolean isBST;
        public int maxNode;
        public int minNode;

        public ReturnType(boolean isBST, int maxNode, int minNode) {
            this.isBST = isBST;
            this.maxNode = maxNode;
            this.minNode = minNode;
        }
    }

    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return false;
        }

        ReturnType result = helper(root);

        return result.isBST;
    }

    private ReturnType helper(TreeNode root) {
        boolean isBST = true;
        int maxNode = root.val;
        int minNode = root.val;

        if (root.left == null && root.right == null) {
            return new ReturnType(isBST, maxNode, minNode);
        }

        if (root.left == null) {
            ReturnType right = helper(root.right);
            isBST = right.isBST && (root.val < right.minNode);
            maxNode = Math.max(root.val, right.maxNode);
            minNode = Math.min(root.val, right.minNode);
            return new ReturnType(isBST, maxNode, minNode);
        }

        if (root.right == null) {
            ReturnType left = helper(root.left);
            isBST = left.isBST && (root.val > left.maxNode);
            maxNode = Math.max(root.val, left.maxNode);
            minNode = Math.min(root.val, left.minNode);
            return new ReturnType(isBST, maxNode, minNode);
        }

        ReturnType left = helper(root.left);
        ReturnType right = helper(root.right);

        isBST = (left.isBST &&
                right.isBST &&
                (root.val > left.maxNode) &&
                (root.val < right.minNode));
        maxNode = Math.max(Math.max(root.val, left.maxNode), right.maxNode);
        minNode = Math.min(Math.min(root.val, left.minNode), right.minNode);

        return  new ReturnType(isBST, maxNode, minNode);
    }
}
