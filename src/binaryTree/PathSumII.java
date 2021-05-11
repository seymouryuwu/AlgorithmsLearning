package binaryTree;

/*
https://leetcode.com/problems/path-sum-ii/
 */
import dataStructure.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PathSumII {
    // Approach 1: divide & conquer
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> results = new ArrayList<>();
        if (root == null) {
            return results;
        }

        results = helper(root, targetSum);
        for (List<Integer> result : results) {
            Collections.reverse(result);
        }

        return results;
    }

    private List<List<Integer>> helper(TreeNode root, int targetSum) {
        List<List<Integer>> paths = new ArrayList<>();
        if (root.left == null && root.right == null) {
            if (root.val == targetSum) {
                List<Integer> path = new ArrayList<>();
                path.add(root.val);
                paths.add(path);
            }
            return paths;
        }

        List<List<Integer>> left = new ArrayList<>();
        if (root.left != null) {
            left = helper(root.left, targetSum - root.val);
        }

        List<List<Integer>> right = new ArrayList<>();
        if (root.right != null) {
            right = helper(root.right, targetSum - root.val);
        }

        for (List<Integer> path : left) {
            path.add(root.val);
            paths.add(path);
        }

        for (List<Integer> path : right) {
            path.add(root.val);
            paths.add(path);
        }

        return paths;
    }

    // Approach 2: traverse
    public List<List<Integer>> pathSum2(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        List<Integer> path = new ArrayList<>();
        helper(root, path, 0, targetSum, result);

        return result;
    }

    private void helper(TreeNode root,
                        List<Integer> path, // path so far
                        int sum, // sum so far
                        int target,
                        List<List<Integer>> result) {
        if (root.left == null && root.right == null) {
            if (sum + root.val == target) {
                path.add(root.val);
                result.add(new ArrayList<>(path));
                path.remove(path.size() - 1);
            }
            return;
        }

        if (root.left != null) {
            path.add(root.val);
            helper(root.left, path, sum + root.val, target, result);
            path.remove(path.size() - 1);
        }

        if (root.right != null) {
            path.add(root.val);
            helper(root.right, path, sum + root.val, target, result);
            path.remove(path.size() - 1);
        }
    }
}
