package twoPointers;
/*
https://leetcode.com/problems/trapping-rain-water/
 */

public class TrappingRainWater {
    // Approach 1: heap
    // see heap.TrappingRainWater

    // Approach 2: dynamic programming
    // see dynamicProgramming.TrappingRainWater

    // Approach 3: two pointers
    public int trap(int[] height) {
        if (height == null || height.length < 3) {
            return 0;
        }

        int maxLeft = 0;
        int maxRight = 0;

        int pointerL = 0;
        int pointerR = height.length - 1;

        int capacity = 0;
        while (pointerL < pointerR) {
            if (height[pointerL] < height[pointerR]) {
                if (height[pointerL] >= maxLeft) {
                    maxLeft = height[pointerL];
                } else {
                    capacity += maxLeft - height[pointerL];
                }

                pointerL++;
            } else {
                if (height[pointerR] >= maxRight) {
                    maxRight = height[pointerR];
                } else {
                    capacity += maxRight - height[pointerR];
                }

                pointerR--;
            }
        }

        return capacity;
    }
}
