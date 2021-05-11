package twoPointers;

/*
https://leetcode.com/problems/sort-colors/
 */
public class SortColors {
    // Approach 1. Approach 2 is better.
    public void sortColors(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }

        int left = 0;
        int right = nums.length - 1;

        for (int i = 0; i < nums.length; i++) {
            while (i < right && nums[i] == 2) {
                int temp = nums[right];
                nums[right] = nums[i];
                nums[i] = temp;

                right--;
            }

            if (nums[i] == 0) {
                int temp = nums[left];
                nums[left] = nums[i];
                nums[i] = temp;

                left++;
            }
        }
    }

    public void sortColors2(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }

        int left = 0;
        int right = nums.length - 1;

        int i = 0;
        while (i <= right) {
            if (nums[i] == 0) {
                int temp = nums[left];
                nums[left] = nums[i];
                nums[i] = temp;

                left++;
                i++;
            } else if (nums[i] == 1) {
                i++;
            } else if (nums[i] == 2) {
                int temp = nums[right];
                nums[right] = nums[i];
                nums[i] = temp;

                right--;
            }
        }
    }
}
