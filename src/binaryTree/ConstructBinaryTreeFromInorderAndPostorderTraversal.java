package binaryTree;
/*
https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
 */

import dataStructure.TreeNode;

import java.util.Arrays;

public class ConstructBinaryTreeFromInorderAndPostorderTraversal {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        TreeNode left = null;
        TreeNode root = null;
        TreeNode right = null;
        for (int i = 0; i < inorder.length; i++) {

            int inorderRootIndex = i;
            root = new TreeNode(inorder[i]);

            while (postorder[i] != root.val) {
                i++;
            }

            int[] inorderRight = Arrays.copyOfRange(inorder, inorderRootIndex + 1, i + 1);
            int[] postorderRight = Arrays.copyOfRange(postorder, inorderRootIndex, i);
            right = buildTree(inorderRight, postorderRight);

            root.left = left;
            root.right = right;

            left = root;
        }

        return left;
    }
}
