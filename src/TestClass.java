import dynamicProgramming.LongestIncreasingPathInAMatrix;
import heap.TrappingRainWater;
import heap.TrappingRainWaterII;
import stack.LargestRectangleInHistogram;

import java.util.PriorityQueue;
import java.util.Queue;

public class TestClass {
    public static void main(String[] args) {
        LongestIncreasingPathInAMatrix l = new LongestIncreasingPathInAMatrix();
        int[][] matrix = {{9,9,4},{6,6,8},{2,1,1}};
        System.out.println(l.longestIncreasingPath(matrix));
    }
}
