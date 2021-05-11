package twoPointers;

/*
https://leetcode.com/problems/two-sum-less-than-k/
 */
import java.util.Arrays;

public class TwoSumLessThanK {
    // Approach 1: brute force
    public int twoSumLessThanK(int[] nums, int k) {
        if (nums == null || nums.length < 2) {
            return -1;
        }

        int max = Integer.MIN_VALUE;

        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int sum = nums[i] + nums[j];
                if (sum > max && sum < k) {
                    max = sum;
                }
            }
        }

        if (max == Integer.MIN_VALUE) {
            return -1;
        }

        return max;
    }

    // Approach 2: two pointers
    public int twoSumLessThanK2(int[] nums, int k) {
        if (nums == null || nums.length < 2) {
            return -1;
        }

        Arrays.sort(nums);

        int max = Integer.MIN_VALUE;
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum >= k) {
                right--;
            } else {
                max = Math.max(max, sum);
                left++;
            }
        }

        if (max == Integer.MIN_VALUE) {
            return -1;
        }

        return max;
    }
}
