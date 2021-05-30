package dynamicProgramming;
/*
https://leetcode.com/problems/interleaving-string/
 */

public class InterleavingString {
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1 == null || s2 == null || s3 == null) {
            return false;
        }

        int m = s1.length();
        int n = s2.length();
        if (m + n != s3.length()) {
            return false;
        }

        char[] chars1 = s1.toCharArray();
        char[] chars2 = s2.toCharArray();
        char[] chars3 = s3.toCharArray();

        // dp[i][j] is whether the first (i + j)th chars in s3 are interleaving of the
        // first (i)th chars in s1 and the first (j)th chars in s2.
        boolean[][] dp = new boolean[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = true;
                } else if (i == 0) {
                    dp[i][j] = (chars2[j - 1] == chars3[i + j - 1]) && dp[i][j - 1];
                } else if (j == 0) {
                    dp[i][j] = (chars1[i - 1] == chars3[i + j - 1]) && dp[i - 1][j];
                } else {
                    dp[i][j] = (chars1[i - 1] == chars3[i + j - 1]) && dp[i - 1][j] ||
                               (chars2[j - 1] == chars3[i + j - 1]) && dp[i][j - 1];
                }
            }
        }

        return dp[m][n];
    }
}
