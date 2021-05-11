package twoPointers;

/*
https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/
 */
public class RemoveDuplicatesFromSortedArrayII {
    public int removeDuplicates(int[] nums) {
        if (nums == null) {
            return 0;
        }

        if (nums.length <= 2) {
            return nums.length;
        }

        int length = 2;
        for (int i = 2; i < nums.length; i++) {
            if (nums[i] != nums[length - 2]) {
                nums[length++] = nums[i];
            }
        }

        return length;
    }
}
