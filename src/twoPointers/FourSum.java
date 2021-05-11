package twoPointers;

/*
https://leetcode.com/problems/4sum/
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FourSum {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> results = new ArrayList<>();
        if (nums == null || nums.length < 4) {
            return results;
        }

        Arrays.sort(nums);

        for (int first = 0; first <= nums.length - 4; first++) {
            while (first <= nums.length - 4 &&
                    first != 0 &&
                    nums[first] == nums[first - 1]) {
                first++;
            }

            for (int second = first + 1; second <= nums.length - 3; second++) {
                while (second <= nums.length - 3 &&
                        second != first + 1 &&
                        nums[second] == nums[second - 1]) {
                    second++;
                }

                int third = second + 1;
                int fourth = nums.length - 1;

                while (third < fourth) {
                    int sum = nums[first] + nums[second] + nums[third] + nums[fourth];

                    if (sum == target) {
                        List<Integer> result = new ArrayList<>();
                        result.add(nums[first]);
                        result.add(nums[second]);
                        result.add(nums[third]);
                        result.add(nums[fourth]);
                        results.add(result);

                        third++;
                        fourth--;
                    } else if (sum < target) {
                        third++;
                    } else {
                        fourth--;
                    }

                    while (third < fourth &&
                            third != second + 1 &&
                            nums[third] == nums[third - 1]) {
                        third++;
                    }

                    while (third < fourth &&
                            fourth != nums.length - 1 &&
                            nums[fourth] == nums[fourth + 1]) {
                        fourth--;
                    }
                }
            }
        }

        return results;
    }
}
