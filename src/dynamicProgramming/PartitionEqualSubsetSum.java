package dynamicProgramming;
/*
https://leetcode.com/problems/partition-equal-subset-sum/
 */

public class PartitionEqualSubsetSum {
    public boolean canPartition(int[] nums) {
        if (nums == null) {
            return false;
        }

        int n = nums.length;

        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        if (sum % 2 != 0) {
            return false;
        }

        // dp[i][s] is whether I can pick some numbers from the first (i)th from
        // nums (can pick [0, i]) and their sum is s.
        boolean[][] dp = new boolean[n + 1][sum / 2 + 1];

        for (int i = 0; i <= n; i++) {
            dp[i][0] = true;
        }

        for (int s = 1; s <= sum / 2; s++) {
            for (int i = 1; i <= n; i++) {
                dp[i][s] = dp[i - 1][s];
                if (s >= nums[i - 1]) {
                    dp[i][s] = dp[i][s] || dp[i - 1][s - nums[i - 1]];
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            if (dp[i][sum / 2]) {
                return true;
            }
        }

        return false;
    }
}
