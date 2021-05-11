package depthFirstSearch;

/*
https://leetcode.com/problems/combination-sum-ii/
 */
import java.util.*;

public class CombinationSumII {
    // Approach 1
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (candidates == null) {
            return result;
        }

        Arrays.sort(candidates);

        List<Integer> combination = new ArrayList<>();
        helper(result, candidates, combination, 0, target, 0);

        for (int i = result.size() - 1; i >= 1; i--) {
            for (int j = i - 1; j >= 0; j--) {
                if (result.get(i).equals(result.get(j))) {
                    result.remove(i);
                    break;
                }
            }
        }
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
                helper(result, candidates, combination, sum, target, i + 1);

                combination.remove(combination.size() - 1);
                sum = sum - candidate;
            }
        }
    }

    // Approach 2, speed up using a counter.
    public List<List<Integer>> combinationSum2_2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (candidates == null) {
            return result;
        }

        Arrays.sort(candidates);

        Map<Integer, Integer> counter = new HashMap<>();
        List<Integer> candidatesList = new ArrayList<>();
        for (int candidate : candidates) {
            if (counter.containsKey(candidate)) {
                counter.put(candidate, counter.get(candidate) + 1);
            } else {
                counter.put(candidate, 1);
                candidatesList.add(candidate);
            }
        }

        List<Integer> combination = new ArrayList<>();
        helper2(result, candidatesList, counter, combination, 0, target, 0);

        return result;
    }

    private void helper2(List<List<Integer>> result,
                        List<Integer> candidatesList,
                        Map<Integer, Integer> counter,
                        List<Integer> combination,
                        int sum,
                        int target,
                        int nextIndex) {
        for (int i = nextIndex; i < candidatesList.size(); i++) {
            int candidate = candidatesList.get(i);

            if (counter.get(candidate) == 0) {
                continue;
            }

            combination.add(candidate);
            sum = sum + candidate;
            counter.put(candidate, counter.get(candidate) - 1);


            if (sum >= target) {
                if (sum == target) {
                    result.add(new ArrayList<>(combination));
                }

                combination.remove(combination.size() - 1);
                sum = sum - candidate;
                counter.put(candidate, counter.get(candidate) + 1);
                break;
            } else {
                helper2(result, candidatesList, counter, combination, sum, target, i);

                combination.remove(combination.size() - 1);
                sum = sum - candidate;
                counter.put(candidate, counter.get(candidate) + 1);
            }
        }
    }

    // Approach 3: best one
    public List<List<Integer>> combinationSum2_3(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (candidates == null) {
            return result;
        }

        Arrays.sort(candidates);

        List<Integer> combination = new ArrayList<>();
        helper3(result, candidates, combination, 0, target, 0);

        return result;
    }

    private void helper3(List<List<Integer>> result,
                        int[] candidates,
                        List<Integer> combination,
                        int sum,
                        int target,
                        int nextIndex) {
        for (int i = nextIndex; i < candidates.length; i++) {
            if (i > nextIndex && candidates[i] == candidates[i - 1]) {
                continue;
            }

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
                helper3(result, candidates, combination, sum, target, i + 1);

                combination.remove(combination.size() - 1);
                sum = sum - candidate;
            }
        }
    }
}
