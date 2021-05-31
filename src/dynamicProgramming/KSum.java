package dynamicProgramming;
/*
https://www.lintcode.com/problem/89/
 */

public class KSum {
    public int kSum(int[] A, int k, int target) {
        if (A == null) {
            return 0;
        }

        int n = A.length;

        // dp[i][j][t] is the number of solutions that I pick j numbers from
        // the first (i)th of A, and the sum of them is t.
        int[][][] dp = new int[n + 1][k + 1][target + 1];

        for (int i = 0; i <= n; i++) {
            dp[i][0][0] = 1;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                for (int t = 1; t <= target; t++) {
                    dp[i][j][t] = dp[i - 1][j][t];
                    if (t - A[i - 1] >= 0) {
                        dp[i][j][t] += dp[i - 1][j - 1][t - A[i - 1]];
                    }
                }
            }
        }

        return dp[n][k][target];
    }
}
