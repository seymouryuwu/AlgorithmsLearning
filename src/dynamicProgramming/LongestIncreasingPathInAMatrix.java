package dynamicProgramming;
/*
https://leetcode.com/problems/longest-increasing-path-in-a-matrix/
 */

public class LongestIncreasingPathInAMatrix {
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int m = matrix.length;
        int n = matrix[0].length;

        // stores the longest increasing path from this point
        int[][] longestFrom = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++){
                longestFrom[i][j] = 0;
            }
        }

        int longest = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++){
                search(i, j, matrix, longestFrom);
                longest = Math.max(longest, longestFrom[i][j]);
            }
        }

        return longest;
    }

    private void search(int x, int y, int[][] matrix, int[][] longestFrom) {
        if (longestFrom[x][y] != 0) {
            return;
        }

        int m = matrix.length;
        int n = matrix[0].length;

        longestFrom[x][y] = 1;

        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        for (int i = 0; i < 4; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];

            if (nextX >= 0 && nextX < m && nextY >= 0 && nextY < n) {
                if (matrix[nextX][nextY] > matrix[x][y]) {
                    search(nextX, nextY, matrix, longestFrom);
                    longestFrom[x][y] = Math.max(longestFrom[x][y], 1 + longestFrom[nextX][nextY]);
                }
            }
        }
    }
}
