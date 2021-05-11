package binaryTree;

/*
https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-ii/
 */
import dataStructure.TreeNode;

public class LowestCommonAncestorOfABinaryTreeII {
    private class ReturnType {
        public boolean isPExist;
        public boolean isQExist;
        public TreeNode LCA;
        public ReturnType(boolean isPExist, boolean isQExist, TreeNode LCA) {
            this.isPExist = isPExist;
            this.isQExist = isQExist;
            this.LCA = LCA;
        }
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        ReturnType result = helper(root, p, q);
        return result.LCA;
    }

    private ReturnType helper(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return new ReturnType(false, false, null);
        }

        ReturnType left = helper(root.left, p, q);
        ReturnType right = helper(root.right, p, q);

        if (left.LCA != null) {
            return new ReturnType(true, true, left.LCA);
        }

        if (right.LCA != null) {
            return new ReturnType(true, true, right.LCA);
        }

        boolean isPExist = (left.isPExist || right.isPExist || root == p);
        boolean isQExist = (left.isQExist || right.isQExist || root == q);

        TreeNode LCA = (isPExist && isQExist) ? root : null;

        return new ReturnType(isPExist, isQExist, LCA);
    }
}
