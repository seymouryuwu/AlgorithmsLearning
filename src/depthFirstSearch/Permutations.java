package depthFirstSearch;

/*
https://leetcode.com/problems/permutations/
 */
import java.util.ArrayList;
import java.util.List;

public class Permutations {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }

        List<Integer> permutation = new ArrayList<>();
        helper(result, nums, permutation);

        return result;
    }

    private void helper(List<List<Integer>> result,
                        int[] nums,
                        List<Integer> permutation) {
        for (int num : nums) {
            if (!permutation.contains(num)) {
                permutation.add(num);
                if (permutation.size() == nums.length) {
                    result.add(new ArrayList<>(permutation));
                } else {
                    helper(result, nums, permutation);
                }

                permutation.remove(permutation.size() - 1);
            }
        }
    }
}
