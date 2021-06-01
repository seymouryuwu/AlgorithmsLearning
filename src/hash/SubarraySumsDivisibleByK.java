package hash;
/*
https://leetcode.com/problems/subarray-sums-divisible-by-k/
 */

import java.util.HashMap;
import java.util.Map;

public class SubarraySumsDivisibleByK {
    public int subarraysDivByK(int[] nums, int k) {
        if (nums == null) {
            return 0;
        }

        // (prefixSum + m * k) % k = (prefixSum % k + (m * k) % k) % k = prefixSum % k
        // so if there are two (prefixSums % k) is same, between them there must be m * k.
        // To record every subarray with (prefixSum % k), we should store the count.

        // Key is (prefixSum % k)
        // Value is the number of subarray with (prefixSum % k)
        Map<Integer, Integer> hash = new HashMap<>();
        hash.put(0, 1);

        int count = 0;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (k != 0) {
                sum %= k;
            }

            // make sure sum is in range from [0, k)
            if (sum < 0) {
                sum += k;
            }

            if (hash.containsKey(sum)) {
                count += hash.get(sum);
                hash.put(sum, hash.get(sum) + 1);
            } else {
                hash.put(sum, 1);
            }
        }

        return count;
    }
}
