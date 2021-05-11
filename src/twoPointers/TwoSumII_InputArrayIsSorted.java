package twoPointers;

/*
https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/
 */
public class TwoSumII_InputArrayIsSorted {
    public int[] twoSum(int[] numbers, int target) {
        if (numbers == null || numbers.length < 2) {
            return null;
        }

        int start = 0;
        int end = numbers.length - 1;

        while (start < end) {
            if (numbers[start] + numbers[end] == target) {
                return new int[]{start + 1, end + 1};
            }

            if (numbers[start] + numbers[end] < target) {
                start++;
            } else {
                end--;
            }
        }

        return null;
    }
}
