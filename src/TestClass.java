import dynamicProgramming.*;
import heap.TrappingRainWater;
import heap.TrappingRainWaterII;
import stack.LargestRectangleInHistogram;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class TestClass {
    public static void main(String[] args) {
        BackpackII backpackII = new BackpackII();
        int m = 15;
        int[] A = {2, 3, 5, 7, 4, 5, 2, 4, 8};
        int[] V = {1, 5, 2, 4, 3, 9, 1, 3, 1};
        System.out.println(backpackII.backPackII(m, A, V));

    }
}
