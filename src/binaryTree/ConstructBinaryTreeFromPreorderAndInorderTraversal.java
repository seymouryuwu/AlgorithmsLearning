package binaryTree;

/*
https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 */
import dataStructure.TreeNode;

import java.util.Arrays;

public class ConstructBinaryTreeFromPreorderAndInorderTraversal {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0) {
            return null;
        }

        if (preorder.length == 1) {
            return new TreeNode(preorder[0]);
        }

        if (preorder.length == 2) {
            TreeNode root = new TreeNode(preorder[0]);
            TreeNode subtree = new TreeNode(preorder[1]);
            if (preorder[0] == inorder[0]) {
                root.right = subtree;
            } else {
                root.left = subtree;
            }

            return root;
        }

        TreeNode root = new TreeNode(preorder[0]);

        int i = 0;
        while (inorder[i] != preorder[0]) {
            i++;
        }

        int[] preorderLeft = Arrays.copyOfRange(preorder, 1, i + 1);
        int[] preorderRight = Arrays.copyOfRange(preorder, i + 1, preorder.length);

        int[] inorderLeft = Arrays.copyOfRange(inorder, 0, i);
        int[] inorderRight = Arrays.copyOfRange(inorder, i + 1, inorder.length);

        root.left = buildTree(preorderLeft, inorderLeft);
        root.right = buildTree(preorderRight, inorderRight);

        return root;
    }
}
