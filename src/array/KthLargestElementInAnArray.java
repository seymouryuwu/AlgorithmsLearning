package array;

/*
https://leetcode.com/problems/kth-largest-element-in-an-array/
 */
import java.util.Arrays;

public class KthLargestElementInAnArray {
    // Approach 1: quick select
    public int findKthLargest(int[] nums, int k) {
        int pivotIndex = quickSelect(nums, k, 0, nums.length - 1);

        return nums[pivotIndex];
    }

    private int quickSelect(int[] nums, int k, int start, int end) {

        int pivotIndex = quickPartition(nums, start, end);
        int largestFound = end + 1 - pivotIndex;
        if (largestFound == k) {
            return pivotIndex;
        }

        if (largestFound < k) {
            pivotIndex = quickSelect(nums, k - largestFound, start, pivotIndex - 1);
        } else {
            pivotIndex = quickSelect(nums, k, pivotIndex + 1, end);
        }

        return pivotIndex;
    }

    private int quickPartition(int[] nums, int start, int end) {
        int pivot = nums[end];
        int i = start;

        for (int j = start; j < end; j++) {
            if (nums[j] < pivot) {
                int temp = nums[j];
                nums[j] = nums[i];
                nums[i] = temp;

                i++;
            }
        }

        nums[end] = nums[i];
        nums[i] = pivot;

        return i;
    }

    // Approach 2: sort
    public int findKthLargest2(int[] nums, int k) {
        Arrays.sort(nums);

        return nums[nums.length - k];
    }
}
