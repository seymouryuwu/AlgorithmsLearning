package breadthFirstSearch;

/*
https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
 */
import dataStructure.TreeNode;

import java.util.*;

public class SerializeAndDeserializeBinaryTree {
    // Approach 1: breadth-first search
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder result = new StringBuilder();
        if (root == null) {
            return result.toString();
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        result.append(String.valueOf(root.val));
        while(!queue.isEmpty()) {
            int size = queue.size();
            ArrayList<String> children = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                    children.add(String.valueOf(node.left.val));
                } else {
                    children.add("#");
                }

                if (node.right != null) {
                    queue.offer(node.right);
                    children.add(String.valueOf(node.right.val));
                } else {
                    children.add("#");
                }
            }

            boolean isAllNull = true;
            for (String s : children) {
                if (!s.equals("#")) {
                    isAllNull = false;
                    break;
                }
            }

            if (!isAllNull) {
                StringBuilder childrenString = new StringBuilder();
                for (String s : children) {
                    childrenString.append(",").append(s);
                }
                result.append(childrenString.toString());
            }
        }

        return result.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.equals("")) {
            return null;
        }

        String[] dataArray = data.split(",");
        TreeNode root = new TreeNode(Integer.parseInt(dataArray[0]));

        List<TreeNode> parents = new ArrayList<>();
        parents.add(root);

        int index = 1;
        int levelSize = 2;
        while (index < dataArray.length) {
            int nonNullNumber = 0;
            List<TreeNode> children = new ArrayList<>();
            for (int i = 0; i < levelSize && i + index < dataArray.length; i++) {
                String childValue = dataArray[index + i];
                if (!childValue.equals("#")) {
                    nonNullNumber++;
                    TreeNode child = new TreeNode(Integer.parseInt(childValue));
                    children.add(child);
                    TreeNode parent = parents.get(i / 2);
                    if (i % 2 == 0) {
                        parent.left = child;
                    }

                    if (i % 2 == 1) {
                        parent.right = child;
                    }
                }
            }

            index = index + levelSize;
            levelSize = nonNullNumber * 2;
            parents = children;
        }

        return root;
    }

    // Approach 2: divide & conquer
    // Encodes a tree to a single string.
    public String serialize2(TreeNode root) {
        return rserialize(root, "");
    }

    public String rserialize(TreeNode root, String str) {
        // Recursive serialization.
        if (root == null) {
            str += "null,";
        } else {
            str += String.valueOf(root.val) + ",";
            str = rserialize(root.left, str);
            str = rserialize(root.right, str);
        }
        return str;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize2(String data) {
        String[] data_array = data.split(",");
        List<String> data_list = new LinkedList<String>(Arrays.asList(data_array));
        return rdeserialize(data_list);
    }

    public TreeNode rdeserialize(List<String> l) {
        // Recursive deserialization.
        if (l.get(0).equals("null")) {
            l.remove(0);
            return null;
        }

        TreeNode root = new TreeNode(Integer.parseInt(l.get(0)));
        l.remove(0);
        root.left = rdeserialize(l);
        root.right = rdeserialize(l);

        return root;
    }
}
