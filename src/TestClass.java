import dynamicProgramming.LongestIncreasingPathInAMatrix;
import dynamicProgramming.StoneGameII;
import heap.TrappingRainWater;
import heap.TrappingRainWaterII;
import stack.LargestRectangleInHistogram;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class TestClass {
    public static void main(String[] args) {
        StoneGameII stoneGameII = new StoneGameII();
        int[] piles = {86,11,7,6,46,37,72,67,33,25,54,45};
        System.out.println(stoneGameII.stoneGameII2(piles));
        //System.out.println(Arrays.toString(stoneGameII.findMaxM(50)));
    }
}
