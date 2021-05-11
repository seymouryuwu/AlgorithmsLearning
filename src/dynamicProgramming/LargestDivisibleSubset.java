package dynamicProgramming;

/*
https://leetcode.com/problems/largest-divisible-subset/
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LargestDivisibleSubset {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> largestSubsets = new ArrayList<>();
        List<Integer> firstSubset = new ArrayList<>();
        firstSubset.add(nums[0]);
        largestSubsets.add(firstSubset);

        int maxSize = 1;
        int answerIndex = 0;

        for (int i = 1; i < nums.length; i++) {
            List<Integer> subset = new ArrayList<>();
            int prevLargestSize = 0;
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0) {
                    List<Integer> prevSubset = largestSubsets.get(j);
                    if (prevSubset.size() > prevLargestSize) {
                        subset = new ArrayList<>(prevSubset);
                        prevLargestSize = prevSubset.size();
                    }
                }
            }

            subset.add(nums[i]);
            largestSubsets.add(subset);

            if (subset.size() > maxSize) {
                answerIndex = i;
                maxSize = subset.size();
            }
        }

        return largestSubsets.get(answerIndex);
    }
}
