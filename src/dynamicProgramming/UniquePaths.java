package dynamicProgramming;

/*
https://leetcode.com/problems/unique-paths/
 */
public class UniquePaths {
    public int uniquePaths(int m, int n) {
        int[][] countPaths = new int[m][n];
        countPaths[0][0] = 1;

        for (int i = 1; i < m; i++) {
            countPaths[i][0] = 1;
        }

        for (int j = 1; j < n; j++) {
            countPaths[0][j] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                countPaths[i][j] = countPaths[i][j - 1] + countPaths[i - 1][j];
            }
        }

        return countPaths[m - 1][n - 1];
    }
}
