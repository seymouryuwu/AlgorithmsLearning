package dynamicProgramming;

/*
https://leetcode.com/problems/unique-paths-ii/
 */
public class UniquePathsII {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        int[][] countPaths = new int[m][n];

        for (int i = 0; i < m; i++) {
            if (obstacleGrid[i][0] == 0) {
                countPaths[i][0] = 1;
            } else {
                for (int j = i; j < m; j++) {
                    countPaths[j][0] = 0;
                }

                break;
            }
        }

        for (int i = 0; i < n; i++) {
            if (obstacleGrid[0][i] == 0) {
                countPaths[0][i] = 1;
            } else {
                for (int j = i; j < n; j++) {
                    countPaths[0][j] = 0;
                }

                break;
            }
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    countPaths[i][j] = 0;
                } else {
                    countPaths[i][j] = countPaths[i][j - 1] + countPaths[i - 1][j];
                }
            }
        }

        return countPaths[m - 1][n - 1];
    }
}
