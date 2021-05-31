package dynamicProgramming;
/*
https://www.lintcode.com/problem/125/
 */

public class BackpackII {
    public int backPackII(int m, int[] A, int[] V) {
        if (A == null || V == null || A.length == 0 || V.length == 0) {
            return 0;
        }

        int n = A.length;

        // dp[i][w] is the max value of items that I picked from first (i)th items
        // (I can pick 0 to i items) and the sum of their weights is exactly w.
        // If I cannot find the items whose sum of weights is exactly w, store -1.
        int[][] dp = new int[n + 1][m + 1];

        int[][] pi = new int[n + 1][m + 1];

        for (int w = 1; w <= m; w++) {
            dp[0][w] = -1;
        }

        for (int w = 1; w <= m; w++) {
            for (int i = 1; i <= n; i++) {
                dp[i][w] = dp[i - 1][w];
                pi[i][w] = 1;
                if (w >= A[i - 1] && dp[i - 1][w - A[i - 1]] != -1) {
                    if (dp[i - 1][w - A[i - 1]] + V[i - 1] > dp[i][w]) {
                        dp[i][w] = dp[i - 1][w - A[i - 1]] + V[i - 1];
                        pi[i][w] = 2;
                    }
                }
            }
        }

        int maxV = -1;
        int weight = 0;
        for (int w = 1; w <= m; w++) {
            maxV = Math.max(maxV, dp[n][w]);
            if (maxV == dp[n][w]) {
                weight = w;
            }
        }

        for (int i = n; i >= 1; i--) {
            if (pi[i][weight] == 2) {
                System.out.println("Item: " + (i - 1) + "; Weight: " + A[i - 1] + "; Value: " + V[i - 1]);
                weight -= A[i - 1];
            }
        }

        return (maxV != -1) ? maxV : 0;
    }
}
