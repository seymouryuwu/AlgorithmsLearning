package dynamicProgramming;
/*
https://leetcode.com/problems/stone-game-vii/
 */

public class StoneGameVII {
    public int stoneGameVII(int[] stones) {
        if (stones == null || stones.length == 0) {
            return 0;
        }

        int n = stones.length;

        int[] prefixSum = new int[n];
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += stones[i];
            prefixSum[i] = sum;
        }

        // dp[i][size] is the difference when player faces stones[i] ... stones[i + size - 1];
        int[][] dp = new int[n][n + 1];
        // dp[i][1] are all zero
        for (int size = 2; size <= n; size++) {
            for (int i = 0; i + size - 1 < n; i++) {
                // subarray from index i to i + size - 1
                int takeLeft = prefixSum[i + size - 1] - prefixSum[i] - dp[i + 1][size - 1];
                int takeRight = prefixSum[i + size - 2] - (i == 0 ? 0 : prefixSum[i - 1]) - dp[i][size - 1];
                dp[i][size] = Math.max(takeLeft, takeRight);
            }
        }

        return dp[0][n];
    }
}
