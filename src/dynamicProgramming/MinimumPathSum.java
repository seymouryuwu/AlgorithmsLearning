package dynamicProgramming;

/*
https://leetcode.com/problems/minimum-path-sum/
 */
public class MinimumPathSum {
    public int minPathSum(int[][] grid) {
        int[][] minSum = new int[grid.length][grid[0].length];
        minSum[0][0] = grid[0][0];

        for (int i = 1; i < minSum[0].length; i++) {
            minSum[0][i] = grid[0][i] + minSum[0][i - 1];
        }

        for (int i = 1; i < minSum.length; i++) {
            minSum[i][0] = grid[i][0] + minSum[i - 1][0];
        }

        for (int i = 1; i < minSum.length; i++) {
            for (int j = 1; j < minSum[0].length; j++) {
                minSum[i][j] = grid[i][j] + Math.min(minSum[i][j - 1], minSum[i - 1][j]);
            }
        }

        return minSum[minSum.length - 1][minSum[0].length - 1];
    }

    // Optimize space complexity with scrolling array
    public int minPathSum2(int[][] grid) {
        int[][] minSum = new int[2][grid[0].length];

        minSum[0][0] = grid[0][0];
        for (int i = 1; i < grid[0].length; i++) {
            minSum[0][i] = minSum[0][i - 1] + grid[0][i];
        }

        int old = 0;
        int now = 1;
        for (int i = 1; i < grid.length; i++) {
            minSum[now][0] = minSum[old][0] + grid[i][0];
            for (int j = 1; j < grid[0].length; j++) {
                minSum[now][j] = Math.min(minSum[old][j], minSum[now][j - 1]) + grid[i][j];
            }

            int temp = old;
            old = now;
            now = temp;
        }

        return minSum[old][grid[0].length - 1];
    }
}
