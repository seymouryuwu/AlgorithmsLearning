package depthFirstSearch;

/*
https://leetcode.com/problems/permutations-ii/
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PermutationsII {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }

        Arrays.sort(nums);

        List<Integer> permutation = new ArrayList<>();
        boolean[] isVisited = new boolean[nums.length];
        helper(result, nums, permutation, isVisited);

        return result;
    }

    private void helper(List<List<Integer>> result,
                        int[] nums,
                        List<Integer> permutation,
                        boolean[] isVisited) {
        for (int i = 0; i < nums.length; i++) {
            if (isVisited[i]) {
                continue;
            }

            if (i != 0 && nums[i] == nums[i - 1] && !isVisited[i - 1]) {
                continue;
            }

            permutation.add(nums[i]);
            isVisited[i] = true;

            if (permutation.size() == nums.length) {
                result.add(new ArrayList<>(permutation));
            }

            helper(result, nums, permutation, isVisited);

            permutation.remove(permutation.size() - 1);
            isVisited[i] = false;
        }
    }
}
