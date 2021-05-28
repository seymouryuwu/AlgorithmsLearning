package dynamicProgramming;
/*
https://leetcode.com/problems/stone-game/
 */

public class StoneGame {
    public boolean stoneGame(int[] piles) {
        if (piles == null || piles.length == 0) {
            return false;
        }

        int n = piles.length;

        // dp[i][j] stores at the situation when the remaining piles are from piles[i]
        // to piles[j], the first player's score - the second player's score in the end.
        int[][] dp = new int[n][n];

        if (n == 1) {
            return true;
        }

        for (int i = 0; i < n; i++) {
            dp[i][i] = piles[i];
        }

        for (int size = 2; size <= n; size++) {
            for (int i = 0; i + size <= n; i++) {
                int j = i + size - 1;

                int pickLeft = piles[i] - dp[i + 1][j];
                int pickRight = piles[j] - dp[i][j - 1];
                dp[i][j] = Math.max(pickLeft, pickRight);
            }
        }

        return (dp[0][n - 1] > 0);
    }
}
