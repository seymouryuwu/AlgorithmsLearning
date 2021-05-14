package stack;
/*
https://leetcode.com/problems/maximal-rectangle/
 */

import java.util.Stack;

public class MaximalRectangle {
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        // this array stores the count of '1' in one edge whose rightmost is at the position(x, y)
        int[][] count = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            int count1 = 0;
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == '0') {
                    count1 = 0;
                } else {
                    count1++;
                    count[i][j] = count1;
                }
            }
        }

        // now it's similar with LargestRectangleHistogram.
        int maxArea = 0;
        for (int j = 0; j < count[0].length; j++) {
            // monotonic stack which stores the index of the count
            // pop out the top when top's width is greater than or equals to my width
            Stack<Integer> stack = new Stack<>();
            for (int i = 0; i <= count.length; i++) {
                int width = (i == count.length) ? 0 : count[i][j];

                while (!stack.isEmpty() && count[stack.peek()][j] >= width) {
                    int w = count[stack.pop()][j];
                    int h = (stack.isEmpty()) ? i : (i - stack.peek() - 1);
                    maxArea = Math.max(maxArea, w * h);
                }

                stack.push(i);
            }
        }

        return maxArea;
    }
}
