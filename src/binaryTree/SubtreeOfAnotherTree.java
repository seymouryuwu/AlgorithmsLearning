package binaryTree;

/*
https://leetcode.com/problems/subtree-of-another-tree/
 */
import dataStructure.TreeNode;

public class SubtreeOfAnotherTree {
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null) {
            return false;
        }

        if (isEqual(s, t)) {
            return true;
        } else {
            return (isSubtree(s.left, t) || isSubtree(s.right, t));
        }
    }

    private boolean isEqual(TreeNode s, TreeNode t) {
        if (s == null && t == null) {
            return true;
        }

        if (s == null || t == null) {
            return false;
        }

        if (s.val == t.val &&
                isEqual(s.left, t.left) &&
                isEqual(s.right, t.right)) {
            return true;
        }

        return false;
    }
}
