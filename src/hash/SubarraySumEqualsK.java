package hash;

/*
https://leetcode.com/problems/subarray-sum-equals-k/
 */
import java.util.HashMap;
import java.util.Map;

public class SubarraySumEqualsK {
    public int subarraySum(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int count = 0;
        int prefixSum = 0;
        // Key is the prefixSum
        // Value is the number of this prefixSum
        Map<Integer, Integer> prefixSumHash = new HashMap<>();
        prefixSumHash.put(0, 1);

        for (int num : nums) {
            prefixSum += num;
            if (prefixSumHash.containsKey(prefixSum - k)) {
                count += prefixSumHash.get(prefixSum - k);
            }

            if (prefixSumHash.containsKey(prefixSum)) {
                prefixSumHash.put(prefixSum, prefixSumHash.get(prefixSum) + 1);
            } else {
                prefixSumHash.put(prefixSum, 1);
            }
        }

        return count;
    }
}
