package dynamicProgramming;
/*
https://leetcode.com/problems/stone-game-v/
 */

public class StoneGameV {
    public int stoneGameV(int[] stoneValue) {
        if (stoneValue == null || stoneValue.length == 0) {
            return 0;
        }

        int n = stoneValue.length;

        int[] prefixSum = new int[n];
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += stoneValue[i];
            prefixSum[i] = sum;
        }

        // dp[i][size] stores when stoneValue[i] .. stoneValue[i + size - 1] remained,
        // the max score Alice can get.
        // size is from 1 to n.
        int[][] dp = new int[n][n + 1];

        // for each size of the subarray
        for (int size = 2; size <= n; size++) {
            // for each beginning index i
            for (int i = 0; i + size - 1 < n; i++) {
                // in this subarray, divide it with (size - 1) ways
                int maxFinalScore = Integer.MIN_VALUE;
                for (int leftSize = 1; leftSize < size; leftSize++) {
                    // left will be index from i to i + leftSize - 1
                    int leftSum = prefixSum[i + leftSize - 1] - ((i == 0) ? 0 : prefixSum[i - 1]);

                    // right will be index from i + leftSize to i + size - 1
                    int rightSum = prefixSum[i + size - 1] - prefixSum[i + leftSize - 1];

                    // the score of left subarray
                    int leftScore = dp[i][leftSize];

                    // the score of right subarray
                    int rightScore = dp[i + leftSize][size - leftSize];

                    int finalScore;
                    if (leftSum > rightSum) {
                        finalScore = rightSum + rightScore;
                    } else if(leftSum < rightSum) {
                        finalScore = leftSum + leftScore;
                    } else { // left and right are equal
                        finalScore = leftSum + Math.max(leftScore, rightScore);
                    }

                    maxFinalScore = Math.max(maxFinalScore, finalScore);
                }

                dp[i][size] = maxFinalScore;
            }
        }

        return dp[0][n];
    }
}
