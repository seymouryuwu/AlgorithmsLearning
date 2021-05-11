package array;

import java.util.ArrayList;
import java.util.List;

public class RotateArray {
    // Approach 1: brute force, space complexity O(1).
    public void rotate(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return;
        }

        k = k % nums.length;

        for (int i = 0; i < k; i++) {
            rotateOnce(nums);
        }
    }

    private void rotateOnce(int[] nums) {
        int value = nums[0];
        for (int i = 0; i < nums.length; i++) {
            if (i == nums.length - 1) {
                nums[0] = value;
            } else {
                int temp = nums[i + 1];
                nums[i + 1] = value;
                value = temp;
            }
        }
    }

    // Approach 2: using extra space
    public void rotate2(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return;
        }

        k = k % nums.length;

        if (k == 0) {
            return;
        }

        List<Integer> tail = new ArrayList<>();
        for (int i = nums.length - k; i < nums.length; i++) {
            tail.add(nums[i]);
        }

        for (int i = nums.length - k - 1; i >= 0; i--) {
            nums[i + k] = nums[i];
        }

        for (int i = 0; i < tail.size(); i++) {
            nums[i] = tail.get(i);
        }
    }

    // Approach 3: reverse
    // see Leetcode solution.
}
