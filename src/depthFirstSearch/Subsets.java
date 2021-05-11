package depthFirstSearch;

/*
https://leetcode.com/problems/subsets/
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Subsets {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null) {
            return result;
        }

        Arrays.sort(nums);

        List<Integer> subset = new ArrayList<>();
        result.add(subset);
        helper(result, subset, nums, 0);

        return result;
    }

    private void helper(List<List<Integer>> result,
                        List<Integer> subset,
                        int[] nums,
                        int nextIndex) {
        for (int i = nextIndex; i < nums.length; i++) {
            subset.add(nums[i]);
            result.add(new ArrayList<>(subset));
            helper(result, subset, nums, i + 1);
            subset.remove(subset.size() - 1) ;
        }
    }
}
