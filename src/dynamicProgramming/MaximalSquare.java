package dynamicProgramming;
/*
https://leetcode.com/problems/maximal-square/
 */

public class MaximalSquare {
    public int maximalSquare(char[][] matrix) {
        int[][] maxSquare = new int[2][matrix[0].length];
        // max is the max of edge length
        int max = 0;

        for (int i = 0; i < matrix[0].length; i++) {
            if (matrix[0][i] == '0') {
                maxSquare[0][i] = 0;
            } else {
                maxSquare[0][i] = 1;
                max = 1;
            }
        }


        int old = 0;
        int now = 1;
        for (int i = 1; i < matrix.length; i++) {
            if (matrix[i][0] == '0') {
                maxSquare[now][0] = 0;
            } else {
                maxSquare[now][0] = 1;
                max = Math.max(max, 1);
            }

            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][j] == '0') {
                    maxSquare[now][j] = 0;
                } else {
                    maxSquare[now][j] = 1 + Math.min(maxSquare[now][j - 1],
                            Math.min(maxSquare[old][j - 1], maxSquare[old][j]));
                }

                max = Math.max(max, maxSquare[now][j]);
            }

            int temp = old;
            old = now;
            now = temp;
        }

        return max * max;
    }
}
