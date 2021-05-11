package binarySearch;

/*
https://leetcode.com/problems/search-in-rotated-sorted-array/
 */
public class SearchInRotatedSortedArray {
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int start = 0;
        int end = nums.length - 1;

        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                return mid;
            }

            // Without checking whether they are equal,
            // nums = {5, 6, 7, 1, 2, 5, 5, 5, 5, 5, 5, 5} or
            // nums = {5, 5, 5, 5, 5, 5, 5, 6, 7, 1, 2, 5}
            // will give wrong answer.
            if (nums[mid] == nums[start]) {
                start++;
            } else if (nums[mid] > nums[start]) {
                if (nums[mid] > target && target >= nums[start]) {
                    end = mid;
                } else {
                    start = mid;
                }
            } else {
                if (nums[mid] < target && target <= nums[end]) {
                    start = mid;
                } else {
                    end = mid;
                }
            }
        }

        if (nums[start] == target) {
            return start;
        }

        if (nums[end] == target) {
            return end;
        }

        return -1;
    }
}
