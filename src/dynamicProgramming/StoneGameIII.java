package dynamicProgramming;
/*
https://leetcode.com/problems/stone-game-iii/
 */

public class StoneGameIII {
    public String stoneGameIII(int[] stoneValue) {
        if (stoneValue == null || stoneValue.length == 0) {
            return "Tie";
        }

        int n = stoneValue.length;

        // dp[i] stores at situation when stoneValue[i] ... stoneValue[n - 1] remained,
        // (the final score of current first player) - (the second player's score)
        int[] dp = new int[n];
        //dp[n - 1] = stoneValue[n - 1];

        for (int i = n - 1; i >= 0; i--) {
            int max = Integer.MIN_VALUE;
            int sum = 0;
            // j is the number of stone token - 1
            for (int j = 0; j < 3 && i + j < n; j++) {
                sum += stoneValue[i + j];
                max = Math.max(max, sum - (i + j + 1 < n ? dp[i + j + 1] : 0));
            }

            dp[i] = max;
        }

        if (dp[0] == 0) {
            return "Tie";
        }

        if (dp[0] > 0) {
            return "Alice";
        } else {
            return "Bob";
        }
    }
}
