package binarySearch;

public class SearchInsertPosition {
    public int searchInsert(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int start = 0;
        int end = nums.length - 1;

        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }

        if (nums[start] >= target) {
            return start;
        }

        if (nums[end] >= target) {
            return end;
        }

        return end + 1;
    }

    // Brute force
    public int searchInsert2(int[] nums, int target) {
        int i = 0;
        while (i < nums.length) {
            if (target <= nums[i]) return i;
            i++;
        }

        return i;
    }
}
