package linkedList;

/*
https://leetcode.com/problems/convert-binary-search-tree-to-sorted-doubly-linked-list/
 */
import dataStructure.TreeNode;

public class ConvertBinarySearchTreeToSortedDoublyLinkedList {
    // Approach 1
    private TreeNode leftmost;
    private TreeNode rightmost;

    public TreeNode treeToDoublyList(TreeNode root) {
        if (root == null) {
            return null;
        }

        leftmost = root;
        rightmost = root;

        if (root.left != null) {
            leftmost = root.left;
            insertChild(root.left, root);
        }

        if (root.right != null) {
            rightmost = root.right;
            insertChild(root.right, root);
        }

        leftmost.left = rightmost;
        rightmost.right = leftmost;

        return leftmost;
    }

    private void insertChild(TreeNode node, TreeNode parent) {
        TreeNode leftChild = node.left;
        TreeNode rightChild = node.right;

        if (node.val < parent.val) {
            TreeNode temp = parent.left;
            if (temp.val < node.val) {
                node.left = temp;
                temp.right = node;
            }
            parent.left = node;
            node.right = parent;

        } else {
            TreeNode temp = parent.right;
            if (temp.val > node.val) {
                node.right = temp;
                temp.left = node;
            }
            parent.right = node;
            node.left = parent;
        }

        if (leftChild != null) {
            if (leftChild.val <= leftmost.val) {
                leftmost = leftChild;
            }

            insertChild(leftChild, node);
        }

        if (rightChild != null) {
            if (rightChild.val >= rightmost.val) {
                rightmost = rightChild;
            }

            insertChild(rightChild, node);
        }
    }

    // Approach 2: see a better solution on Leetcode
}
