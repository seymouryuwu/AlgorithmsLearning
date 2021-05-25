package array;
/*
https://leetcode.com/problems/longest-continuous-increasing-subsequence/
 */

public class LongestContinuousIncreasingSubsequence {
    public int findLengthOfLCIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int max = 1;
        int count = 1;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i + 1] > nums[i]) {
                count++;
            } else {
                max = Math.max(max, count);
                count = 1;
            }
        }

        return max = Math.max(max, count);
    }
}
