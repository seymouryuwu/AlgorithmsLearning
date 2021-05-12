import heap.TrappingRainWater;

import java.util.PriorityQueue;
import java.util.Queue;

public class TestClass {
    public static void main(String[] args) {
        TrappingRainWater t = new TrappingRainWater();
        int[] height = {9,6,8,8,5,6,3};
        System.out.println(t.trap(height));

    }
}
