package easy;

import java.util.ArrayList;
import java.util.Arrays;

public class MaximumSubarray {
    // Approach 1: stupid one
    public int maxSubArray(int[] nums) {
        if (nums.length == 1) return nums[0];

        ArrayList<Integer> combinedArray = new ArrayList<>();
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i == nums.length-1 || Integer.signum(nums[i]) != Integer.signum(nums[i+1])) {
                combinedArray.add(sum + nums[i]);
                sum = 0;
            } else {
                sum += nums[i];
            }
        }

        int maxSum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > maxSum) maxSum = nums[i];
        }

        for (int i = 0; i < combinedArray.size(); i++) {
            if (combinedArray.get(i) <= 0) continue;
            sum = combinedArray.get(i);
            maxSum = Math.max(maxSum, sum);
            for (int j = i+1; j < combinedArray.size(); j++) {
                sum += combinedArray.get(j);
                if (sum < 0) break;
                maxSum = Math.max(maxSum, sum);
            }
        }

        return maxSum;
    }

    // Approach 2: Greedy
    public int maxSubArray2(int[] nums) {
        int curtSum = nums[0];
        int maxSum = nums[0];

        for(int i = 1; i < nums.length; ++i) {
            curtSum = Math.max(nums[i], curtSum + nums[i]);
            maxSum = Math.max(maxSum, curtSum);
        }

        return maxSum;
    }

    // Approach 3: prefix sum
    public int maxSubArray3(int[] nums) {
        int max = Integer.MIN_VALUE;
        int curtSum = 0;
        int minSum = 0;

        for (int num : nums) {
            curtSum += num;
            max = Math.max(max, curtSum - minSum);
            minSum = Math.min(minSum, curtSum);
        }

        return max;
    }
}
