package binarySearch;

/*
https://leetcode.com/problems/single-element-in-a-sorted-array/
 */
public class SingleElementInASortedArray {
    public int singleNonDuplicate(int[] nums) {
        if (nums == null) {
            return -1;
        }

        int start = 0;
        int end = nums.length - 1;

        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (((end - start) / 2) % 2 == 0) {
                if (nums[mid] == nums[mid - 1]) {
                    end = mid;
                } else {
                    start = mid;
                }
            } else {
                if (nums[mid] == nums[mid - 1]) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
        }

        if (start != 0 && nums[start - 1] == nums[start]) {
            return nums[end];
        } else {
            return nums[start];
        }
    }
}
