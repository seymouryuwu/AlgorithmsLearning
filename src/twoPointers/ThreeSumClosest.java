package twoPointers;

/*
https://leetcode.com/problems/3sum-closest/
 */
import java.util.Arrays;

public class ThreeSumClosest {
    public int threeSumClosest(int[] nums, int target) {
        if (nums == null || nums.length < 3) {
            return 0;
        }

        Arrays.sort(nums);

        int closestSum = nums[0] + nums[1] + nums[2];

        for (int smallest = 0; smallest <= nums.length - 3; smallest++) {
            int middle = smallest + 1;
            int largest = nums.length - 1;

            while (middle < largest) {
                int sum = nums[smallest] + nums[middle] + nums[largest];

                if (sum == target) {
                    return target;
                }

                if (sum < target) {
                    middle++;
                } else {
                    largest--;
                }

                if (Math.abs(target - closestSum) > Math.abs(target - sum)) {
                    closestSum = sum;
                }
            }
        }

        return closestSum;
    }
}
