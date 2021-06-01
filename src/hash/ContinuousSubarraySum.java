package hash;

/*
https://leetcode.com/problems/continuous-subarray-sum/
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContinuousSubarraySum {
    // time complexity is O(n^2)
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

    // time complexity O(n). using HashMap
    public boolean checkSubarraySum2(int[] nums, int k) {
        if (nums == null) {
            return false;
        }

        // (prefixSum + m * k) % k = (prefixSum % k + (m * k) % k) % k = prefixSum % k
        // so if there are two (prefixSums % k) is same, between them there must be m * k.
        // To make sure the size is at least two, we should check the index of the two prefixSum.

        // Key is (prefixSum % k)
        // Value is the first index of prefixSum for (prefixSum % k)
        Map<Integer, Integer> hash = new HashMap<>();
        hash.put(0, -1);

        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (k != 0) {
                sum %= k;
            }

            if (hash.containsKey(sum)) {
                if (i - hash.get(sum) > 1) {
                    return true;
                }
            } else {
                hash.put(sum, i);
            }
        }

        return false;
    }
}
