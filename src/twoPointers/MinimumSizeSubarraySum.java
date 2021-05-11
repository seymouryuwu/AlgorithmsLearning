package twoPointers;

/*
https://leetcode.com/problems/minimum-size-subarray-sum/
 */
public class MinimumSizeSubarraySum {
    public int minSubArrayLen(int target, int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int start = 0;
        int end = 0;
        int sum = nums[0];
        int min = Integer.MAX_VALUE;

        while (start < nums.length) {
            if (sum < target) {
                end++;
                if (end < nums.length) {
                    sum += nums[end];
                } else {
                    break;
                }
            } else {
                if (start == end) {
                    return 1;
                }

                min = Math.min(end - start + 1, min);
                sum -= nums[start];
                start++;
            }
        }

        return (min == Integer.MAX_VALUE) ? 0 : min;
    }

    public int minSubArrayLen2(int target, int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int start = 0;
        int sum = 0;
        int min = Integer.MAX_VALUE;

        for (int end = 0; end < nums.length; end++) {
            sum += nums[end];
            while (sum >= target) {
                min = Math.min(min, end + 1 - start);
                sum -= nums[start++];
            }
        }

        return (min == Integer.MAX_VALUE) ? 0 : min;
    }
}
