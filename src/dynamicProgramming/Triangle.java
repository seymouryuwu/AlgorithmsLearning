package dynamicProgramming;

/*
https://leetcode.com/problems/triangle/
 */
import java.util.Arrays;
import java.util.List;

public class Triangle {
    // Approach 1
    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0) {
            return 0;
        }

        int[][] hash = new int[triangle.size()][triangle.size()];
        for (int[] h : hash) {
            Arrays.fill(h, Integer.MAX_VALUE);
        }

        return minSumToBottom(triangle, hash, 0, 0);
    }

    private int minSumToBottom(List<List<Integer>> triangle, int[][] hash, int x, int y) {
        int nodeValue = triangle.get(x).get(y);
        if (x == triangle.size() - 1) {
            hash[x][y] = nodeValue;
            return nodeValue;
        }

        if (hash[x + 1][y] == Integer.MAX_VALUE) {
            hash[x + 1][y] = minSumToBottom(triangle, hash, x + 1, y);
        }

        if (hash[x + 1][y + 1] == Integer.MAX_VALUE) {
            hash[x + 1][y + 1] = minSumToBottom(triangle, hash, x + 1, y + 1);
        }

        int minSum = nodeValue + Math.min(hash[x + 1][y], hash[x + 1][y + 1]);
        hash[x][y] = minSum;

        return minSum;
    }

    // Approach 2: without recursion
    public int minimumTotal2(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0) {
            return 0;
        }

        int size = triangle.size();
        int[][] minSum = new int[size][size];

        for (int i = 0; i < size; i++) {
            minSum[size - 1][i] = triangle.get(size - 1).get(i);
        }

        for (int i = size - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                minSum[i][j] = triangle.get(i).get(j) + Math.min(minSum[i + 1][j], minSum[i + 1][j + 1]);
            }
        }

        return minSum[0][0];
    }
}
