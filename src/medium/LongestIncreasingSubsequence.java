package medium;

public class LongestIncreasingSubsequence {
    public int longestIncreasingSubsequence(int[] nums) {
        int[] minLast = new int[nums.length + 1];
        minLast[0] = Integer.MIN_VALUE;
        for (int i = 1; i <= nums.length; i++) {
            minLast[i] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < nums.length; i++) {
            int index = binarySearch(minLast, nums[i]);
            minLast[index] = nums[i];
        }

        for (int i = nums.length; i >= 1; i--) {
            if (minLast[i] != Integer.MAX_VALUE) {
                return i;
            }
        }

        return 0;
    }

    private int binarySearch(int[] minLast, int num) {
        int start = 0;
        int end = minLast.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (minLast[mid] < num) {
                start = mid;
            } else {
                end = mid;
            }
        }
        return end;
    }
}
