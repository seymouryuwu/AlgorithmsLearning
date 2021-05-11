package twoPointers;

/*
https://leetcode.com/problems/3sum-smaller/
 */
import java.util.Arrays;

public class ThreeSumSmaller {
    public int threeSumSmaller(int[] nums, int target) {
        if (nums == null || nums.length < 3) {
            return 0;
        }

        Arrays.sort(nums);
        int count = 0;

        for (int smallest = 0; smallest <= nums.length - 3; smallest++) {
            int middle = smallest + 1;
            int largest = nums.length - 1;

            while (middle < largest) {
                if (nums[smallest] + nums[middle] + nums[largest] < target) {
                    count += (largest - middle);
                    middle++;
                } else {
                    largest--;
                }
            }
        }

        return count;
    }
}
