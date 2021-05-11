package breadthFirstSearch;

/*
https://leetcode.com/problems/print-binary-tree/
 */
import dataStructure.TreeNode;

import java.util.*;

public class PrintBinaryTree {
    private class NodeCoordinate {
        public TreeNode node;
        public int row;
        public int column;
        public NodeCoordinate(TreeNode node, int row, int column) {
            this.node = node;
            this.row = row;
            this.column = column;
        }
    }

    public List<List<String>> printTree(TreeNode root) {
        List<List<String>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        int height = findHeight(root);
        int width = (int)Math.pow(2, height) - 1;

        String[][] resultArray = new String[height][width];
        for (String[] row : resultArray) {
            Arrays.fill(row, "");
        }

        Queue<NodeCoordinate> queue = new LinkedList<>();
        queue.offer(new NodeCoordinate(root, 0, width / 2));
        while (!queue.isEmpty()) {
            NodeCoordinate nodeCo = queue.poll();
            TreeNode node = nodeCo.node;
            int row = nodeCo.row;
            int column = nodeCo.column;

            resultArray[row][column] = node.val + "";

            int columnDifference = (int)Math.pow(2, height - row - 2);
            if (node.left != null) {
                queue.offer(new NodeCoordinate(node.left, row + 1, column - columnDifference));
            }

            if (node.right != null) {
                queue.offer(new NodeCoordinate(node.right, row + 1, column + columnDifference));
            }
        }

        for (String[] rowArray : resultArray) {
            List<String> row = Arrays.asList(rowArray);
            result.add(row);
        }

        return result;
    }

    private int findHeight(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        int height = 0;
        queue.offer(root);
        while (!queue.isEmpty()) {
            height++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }

                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }

        return height;
    }
}
