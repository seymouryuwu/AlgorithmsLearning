package binaryTree;

import dataStructure.TreeNode;

public class SymmetricTree {
    // Make sure helper method's input parameters are not null.
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return false;
        }

        if (root.left == null && root.right == null) {
            return true;
        }

        if (root.left != null && root.right != null) {
            return isSymmetricTrees(root.left, root.right);
        }

        return false;
    }

    private boolean isSymmetricTrees(TreeNode l, TreeNode r) {
        if (l.val != r.val) {
            return false;
        }

        if (l.left == null && r.right == null &&
                l.right == null && r.left == null) {
            return true;
        }

        if (l.left != null && r.right == null ||
                l.left == null && r.right != null) {
            return false;
        }

        if (l.right != null && r.left == null ||
                l.right == null && r.left != null) {
            return false;
        }

        boolean outer = true;
        boolean inner = true;

        if (l.left != null) {
            outer = isSymmetricTrees(l.left, r.right);
        }

        if (l.right != null) {
            inner = isSymmetricTrees(l.right, r.left);
        }

        return outer && inner;
    }

    // check if the inputs are null in the method.
    public boolean isSymmetric2(TreeNode root) {
        if (root == null) {
            return false;
        }

        return isSymmetricTrees2(root.left, root.right);
    }

    private boolean isSymmetricTrees2(TreeNode l, TreeNode r) {
        if (l == null && r == null) {
            return true;
        }

        if (l == null || r == null) {
            return false;
        }

        return (l.val == r.val) &&
                isSymmetricTrees2(l.left, r.right) &&
                isSymmetricTrees2(l.right, r.left);
    }
}
