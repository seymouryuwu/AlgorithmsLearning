package array;
/*
https://leetcode.com/problems/maximum-average-subarray-i/
 */

public class MaximumAverageSubarrayI {
    public double findMaxAverage(int[] nums, int k) {
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }

        int maxSum = sum;
        for (int i = k; i <nums.length; i++) {
            sum -= nums[i - k];
            sum += nums[i];
            maxSum = Math.max(maxSum, sum);
        }

        return (double)maxSum / k;
    }
}
