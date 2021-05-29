package dynamicProgramming;
/*
https://leetcode.com/problems/longest-common-subsequence/
 */

public class LongestCommonSubsequence {
    public int longestCommonSubsequence(String text1, String text2) {
        if (text1 == null || text2 == null || text1.length() == 0 || text2.length() == 0) {
            return 0;
        }

        int m = text1.length();
        int n = text2.length();


        // dp[i][j] is the longest common subsequence of first ith chars in text1 and first jth chars in text2
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                int longest = Math.max(dp[i][j - 1], dp[i - 1][j]);
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    longest = Math.max(longest, dp[i - 1][j - 1] + 1);
                }

                dp[i][j] = longest;
            }
        }

        return dp[m][n];
    }
}
