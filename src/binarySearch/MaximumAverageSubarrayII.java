package binarySearch;
/*
https://leetcode.com/problems/maximum-average-subarray-ii/
 */

public class MaximumAverageSubarrayII {
    public double findMaxAverage(int[] nums, int k) {
        // the idea is we try to find the max average using binary search in a certain range.
        // assume there is an average value av of a subarray whose length >= k,
        // we want to find if there is another average value which is greater than (or equal to) it.
        // so, (nums[first] + ... + nums[last]) / (last - first + 1) >= av,
        // if this inequality is true, that means there potentially (when equal) exists a greater average.
        // so we then pick a greater av to check again, until find the answer within error less than 0.00001.
        // the above inequality can be (nums[first] - av) + ... + (nums[last] - av) >= 0,
        // so using prefix sum it can be checked in O(n);

        double large = nums[0];
        double small = nums[0];
        for (int num: nums) {
            large = Math.max(large, num);
            small = Math.min(small, num);
        }

        while (small + 0.00001 < large) {
            double mid = small + (large - small) / 2;
            if (check(nums, k, mid)) {
                small = mid;
            } else {
                large = mid;
            }
        }

        return small;
    }

    // check if there is a subarray with required length whose average >= av
    // if there is, return true
    private boolean check(int[] nums, int k, double av) {
        double[] prefix = new double[nums.length];
        double sum = 0.0;
        for (int i = 0; i < nums.length; i++) {
            double increment = nums[i] - av;
            sum += increment;
            prefix[i] = sum;
        }

        double minPrefix = 0;
        for (int i = k - 1; i < prefix.length; i++) {
            if (prefix[i] - minPrefix >= 0) {
                return true;
            }

            minPrefix = Math.min(minPrefix, prefix[i - k + 1]);
        }

        return false;
    }
}
