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

    // using scrolling array to optimise space complexity
    public int uniquePaths2(int m, int n) {
        if (m == 0 || n == 0) {
            return 0;
        }

        if (m == 1 || n == 1) {
            return 1;
        }

        int[][] count = new int[2][n];

        for (int i = 0; i < n; i++) {
            count[0][i] = 1;
        }

        int old = 0;
        int now = 1;
        for (int i = 1; i < m; i++) {
            count[now][0] = 1;
            for (int j = 1; j < n; j++) {
                count[now][j] = count[now][j - 1] + count[old][j];
            }

            int temp = old;
            old = now;
            now = temp;
        }

        return count[old][n - 1];
    }
}
