package dynamicProgramming;
/*
https://www.lintcode.com/problem/440/
 */

public class BackpackIII {
    public int backPackIII(int[] A, int[] V, int m) {
        if (A == null || V == null ) {
            return 0;
        }

        int n = A.length;

        // dp[i][w] is the max total values of the items that I picked from the
        // first (i)th kinds of items and the sum of their weights is exactly w.
        int[][] dp = new int[n + 1][m + 1];

        for (int w = 1; w <= m; w++) {
            dp[0][w] = -1;
        }

        for (int i = 1; i <= n; i++) {
            for (int w = 1; w <= m; w++) {
                // I don't pick any (i)th items
                dp[i][w] = dp[i - 1][w];
                if (w >= A[i - 1] && dp[i][w - A[i - 1]] != -1) {
                    // I pick one or more (i)th items
                    dp[i][w] = Math.max(dp[i][w], dp[i][w - A[i - 1]] + V[i - 1]);
                }
            }
        }

        int max = -1;
        for (int w = 1; w <= m; w++) {
            max = Math.max(max, dp[n][w]);
        }

        return (max == -1) ? 0 : max;
    }
}
