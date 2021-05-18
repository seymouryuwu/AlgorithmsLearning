package binarySearch;

import java.util.ArrayList;
import java.util.List;

public class CountOfSmallerNumbersAfterSelf {
    // Approach 1: brute force, time limit exceeded
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> result = new ArrayList<>();
        if (nums.length == 0) {
            return result;
        }

        for (int i = nums.length - 1; i >= 0; i--) {
            int num = nums[i];
            int count = 0;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < num) {
                    count++;
                }
            }

            result.add(0, count);
        }

        return result;
    }
}
