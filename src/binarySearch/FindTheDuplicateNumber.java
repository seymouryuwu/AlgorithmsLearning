package binarySearch;
/*
https://leetcode.com/problems/find-the-duplicate-number/
 */

public class FindTheDuplicateNumber {
    // Approach 1: binary search
    // time complexity O(nlog(n))
    public int findDuplicate(int[] nums) {
        int small = 1;
        int large = nums.length - 1;
        while (small + 1 < large) {
            int mid = small + (large - small) / 2;
            int count = count(nums, mid);

            // if count > mid, the duplicate number must be in the range of [1, mid].
            if (count > mid) {
                large = mid;
            } else {
                small = mid;
            }
        }

        int count = 0;
        for (int num : nums) {
            if (num == small) {
                count++;
            }

            if (count > 1) {
                return small;
            }
        }

        return large;
    }

    // count the number of the numbers in nums that are less than or equal to num
    private int count(int[] nums, int num) {
        int count = 0;
        for (int n : nums) {
            if (n <= num) {
                count++;
            }
        }

        return count;
    }

    // Approach 2: two pointers
    // see twoPointers.FindTheDuplicateNumber
}
