package dynamicProgramming;

/*
https://leetcode.com/problems/longest-increasing-subsequence/
 */
public class LongestIncreasingSubsequence {
    public int lengthOfLIS(int[] nums) {
        int max = 1;
        int[] longest = new int[nums.length];
        longest[0] = 1;
        for (int i = 0; i < longest.length; i++) {
            int maxUntilThis = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    maxUntilThis = Math.max(maxUntilThis, longest[j] + 1);
                }
            }

            longest[i] = maxUntilThis;
            max = Math.max(max, maxUntilThis);
        }

        return max;
    }
}
