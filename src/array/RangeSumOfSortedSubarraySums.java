package array;
/*
https://leetcode.com/problems/range-sum-of-sorted-subarray-sums/
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RangeSumOfSortedSubarraySums {
    public int rangeSum(int[] nums, int n, int left, int right) {
        int[] prefixSum = new int[n];
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            prefixSum[i] = sum;
        }

        List<Integer> sumArray = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int subarraySum = prefixSum[j] - ((i == 0) ? 0 : prefixSum[i - 1]);
                sumArray.add(subarraySum);
            }
        }

        Collections.sort(sumArray);

        long result = 0;
        for (int i = left - 1; i <= right - 1; i++) {
            result += sumArray.get(i);
        }

        return (int)(result % 1000000007);
    }

    // there is another way using binary search, but I don't understand
}
