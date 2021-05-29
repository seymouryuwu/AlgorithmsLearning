import dynamicProgramming.LongestIncreasingPathInAMatrix;
import dynamicProgramming.StoneGameII;
import dynamicProgramming.StoneGameVIII;
import heap.TrappingRainWater;
import heap.TrappingRainWaterII;
import stack.LargestRectangleInHistogram;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class TestClass {
    public static void main(String[] args) {
        StoneGameVIII sg = new StoneGameVIII();
        int[] stones = {7,-6,5,10,5,-2,-6};
        System.out.println(sg.stoneGameVIII(stones));
        //System.out.println(Arrays.toString(stoneGameII.findMaxM(50)));
    }
}
