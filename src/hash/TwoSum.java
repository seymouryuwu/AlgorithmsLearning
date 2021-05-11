package hash;

/*
https://leetcode.com/problems/two-sum/
 */

import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    // Approach 1: brute force
    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length-1; i++) {
            for (int j = i+1; j < nums.length; j++) {
                if (nums[i]+nums[j] == target) return new int[]{i, j};
            }
        }
        return null;
    }

    // Approach 2: hash
    public int[] twoSum2(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            return null;
        }

        Map<Integer, Integer> hash = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (hash.containsKey(target - nums[i])) {
                return new int[]{hash.get(target - nums[i]), i};
            } else {
                hash.put(nums[i], i);
            }
        }

        return null;
    }
}
