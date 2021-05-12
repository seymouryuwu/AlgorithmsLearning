package heap;
/*
https://leetcode.com/problems/trapping-rain-water/
 */

import java.util.PriorityQueue;
import java.util.Queue;

public class TrappingRainWater {
    // Approach 1: heap
    public int trap(int[] height) {
        if (height == null || height.length < 3) {
            return 0;
        }

        Queue<Integer> heightFromLeft = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
        Queue<Integer> heightFromRight = new PriorityQueue<>((a, b) -> Integer.compare(b, a));

        heightFromLeft.offer(height[0]);

        for (int i = 2; i < height.length; i++) {
            heightFromRight.offer(height[i]);
        }

        int capacity = 0;
        for (int i = 1; i < height.length - 1; i++) {
            int maxHeightL = heightFromLeft.peek();
            int maxHeightR = heightFromRight.peek();

            if (maxHeightL > height[i] && maxHeightR > height[i]) {
                capacity += (Math.min(maxHeightL, maxHeightR) - height[i]);
            }

            heightFromLeft.offer(height[i]);
            heightFromRight.remove(height[i + 1]);
        }

        return capacity;
    }

    // Approach 2: dynamic programming
    // see dynamicProgramming.TrappingRainWater

    // Approach 3: two pointers
    // see twoPointers.TrappingRainWater
}
