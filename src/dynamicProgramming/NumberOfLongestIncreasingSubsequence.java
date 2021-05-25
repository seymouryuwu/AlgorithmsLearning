package dynamicProgramming;
/*
https://leetcode.com/problems/number-of-longest-increasing-subsequence/
 */

public class NumberOfLongestIncreasingSubsequence {
    public int findNumberOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        Ending[] endings = new Ending[nums.length];

        for (int i = 0; i < nums.length; i++) {
            Ending ending = new Ending();
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    if (endings[j].length + 1 > ending.length) {
                        ending.length = endings[j].length + 1;
                        ending.count = endings[j].count;
                    } else if (endings[j].length + 1 == ending.length) {
                        ending.count += endings[j].count;
                    }
                }
            }

            endings[i] = ending;
        }

        int longest = 0;
        for (Ending e : endings) {
            longest = Math.max(longest, e.length);
        }

        int count = 0;
        for (Ending e : endings) {
            if (e.length == longest) {
                count += e.count;
            }
        }

        return count;
    }

    private class Ending {
        int length = 1;
        int count = 1;
    }
}
