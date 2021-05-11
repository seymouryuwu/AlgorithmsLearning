package twoPointers;

/*
https://leetcode.com/problems/valid-triangle-number/
 */
import java.util.Arrays;

public class ValidTriangleNumber {
    public int triangleNumber(int[] nums) {
        if (nums == null || nums.length < 3) {
            return 0;
        }

        Arrays.sort(nums);
        int count = 0;

        for (int i = nums.length - 1; i >=2; i--) {
            int longest = nums[i];
            int middle = i - 1;
            int shortest = 0;

            while (shortest < middle) {
                if (nums[shortest] + nums[middle] > longest) {
                    count += (middle - shortest);
                    middle--;
                } else {
                    shortest++;
                }
            }
        }

        return count;
    }
}
