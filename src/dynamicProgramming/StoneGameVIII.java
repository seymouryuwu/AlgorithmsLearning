package dynamicProgramming;
/*
https://leetcode.com/problems/stone-game-viii/
 */

public class StoneGameVIII {
    public int stoneGameVIII(int[] stones) {
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

        // dp[i] is the score difference when the first stone is the sum of stone from index 0 to i
        int[] dp = new int[n];

        // when i == n - 1, the last stone, game over, player can get nothing

        int maxScore = Integer.MIN_VALUE;
        for (int i = n - 2; i >= 0; i--) {
            // player can take 2 stones, or ..., or (n - i) stones

            // take 2
            int score = prefixSum[i + 1] - dp[i + 1];

            // compared to take 3 ... (n - i) stones, which already calculated
            maxScore = Math.max(maxScore, score);

            dp[i] = maxScore;
        }

        return dp[0];
    }
}
