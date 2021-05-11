package depthFirstSearch;
/*
https://leetcode.com/problems/combination-sum/
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (candidates == null) {
            return result;
        }

        Arrays.sort(candidates);

        List<Integer> combination = new ArrayList<>();
        helper(result, candidates, combination, 0, target, 0);

        return result;
    }

    private void helper(List<List<Integer>> result,
                        int[] candidates,
                        List<Integer> combination,
                        int sum,
                        int target,
                        int nextIndex) {
        for (int i = nextIndex; i < candidates.length; i++) {
            int candidate = candidates[i];
            combination.add(candidate);
            sum = sum + candidate;

            if (sum >= target) {
                if (sum == target) {
                    result.add(new ArrayList<>(combination));
                }

                combination.remove(combination.size() - 1);
                sum = sum - candidate;
                break;
            } else {
                helper(result, candidates, combination, sum, target, i);

                combination.remove(combination.size() - 1);
                sum = sum - candidate;
            }
        }
    }
}
