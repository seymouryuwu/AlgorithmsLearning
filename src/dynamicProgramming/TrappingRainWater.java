package dynamicProgramming;
/*
https://leetcode.com/problems/trapping-rain-water/
 */

public class TrappingRainWater {
    // Approach 1: heap
    // see heap.TrappingRainWater

    // Approach 2: dynamic programming
    public int trap(int[] height) {
        if (height == null || height.length < 3) {
            return 0;
        }

        int length = height.length;
        int[] maxFromLeft = new int[length];
        int[] maxFromRight = new int[length];

        int maxL = height[0];
        for (int i = 0; i < length; i++) {
            maxL = Math.max(maxL, height[i]);
            maxFromLeft[i] = maxL;
        }

        int maxR = height[length - 1];
        for (int i = length - 1; i >= 0; i--) {
            maxR = Math.max(maxR, height[i]);
            maxFromRight[i] = maxR;
        }

        int capacity = 0;
        for (int i = 1; i < length - 1; i++) {
            if (height[i] < maxFromLeft[i] && height[i] < maxFromRight[i]) {
                capacity += (Math.min(maxFromLeft[i], maxFromRight[i]) - height[i]);
            }
        }

        return capacity;
    }

    // Approach 3: two pointers
    // see twoPointers.TrappingRainWater
}
