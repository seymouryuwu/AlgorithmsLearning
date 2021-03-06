package dynamicProgramming;
/*
https://leetcode.com/problems/edit-distance/
 */

public class EditDistance {
    public int minDistance(String word1, String word2) {
        if (word1 == null || word2 == null) {
            return 0;
        }

        int m = word1.length();
        int n = word2.length();

        // dp[i][j] is the minimum number of operations required to convert the first ith chars
        // in word1 to first jth chars in word2
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }

        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                int min = Math.min(dp[i][j - 1] + 1, dp[i - 1][j] + 1);
                min = Math.min(min, dp[i - 1][j - 1] + 1);
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    min = Math.min(min, dp[i - 1][j - 1]);
                }

                dp[i][j] = min;
            }
        }

        return dp[m][n];
    }
}
