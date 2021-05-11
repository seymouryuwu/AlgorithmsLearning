package array;

/*
https://leetcode.com/problems/continuous-subarray-sum/
 */
import java.util.ArrayList;
import java.util.List;

public class ContinuousSubarraySum {
    public boolean checkSubarraySum(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return false;
        }

        List<Integer> prefixSum = new ArrayList<>();
        prefixSum.add(0);

        int sum = nums[0];
        prefixSum.add(sum);


        for (int i = 1; i < nums.length; i++) {
            sum += nums[i];
            for (int j = 0; j < prefixSum.size() - 1; j++) {
                if (k == 0) {
                    if (sum - prefixSum.get(j) == 0) {
                        return true;
                    }
                } else if ((sum - prefixSum.get(j)) % k == 0) {
                    return true;
                }
            }

            prefixSum.add(sum);
        }

        return false;
    }

    // An approach with time complexity O(n) is at Leetcode solution.
}
