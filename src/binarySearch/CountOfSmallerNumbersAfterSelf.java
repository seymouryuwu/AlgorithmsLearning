package binarySearch;

import java.util.ArrayList;
import java.util.List;

public class CountOfSmallerNumbersAfterSelf {
    // Approach 1: brute force, time limit exceeded
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> result = new ArrayList<>();
        if (nums.length == 0) {
            return result;
        }

        for (int i = nums.length - 1; i >= 0; i--) {
            int num = nums[i];
            int count = 0;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < num) {
                    count++;
                }
            }

            result.add(0, count);
        }

        return result;
    }

    public List<Integer> countSmaller2(int[] nums) {
        List<Integer> result = new ArrayList<>();

        List<Integer> sorted = new ArrayList<>();

        for (int i = nums.length - 1; i >= 0; i--) {
            int num = nums[i];

            result.add(0, insertNum(sorted, num));
        }

        return result;
    }

    // return the number of the smaller number
    private int insertNum(List<Integer> sorted, int num) {
        int size = sorted.size();

        if (size == 0) {
            sorted.add(num);
            return 0;
        }

        if (size == 1) {
            if (sorted.get(0) >= num) {
                sorted.add(num);
                return 0;
            } else {
                sorted.add(0, num);
                return 1;
            }
        }

        if (size == 2) {
            if (sorted.get(0) < num) {
                sorted.add(0, num);
                return 2;
            }

            if (sorted.get(1) >= num) {
                sorted.add(num);
                return 0;
            }

            sorted.add(1, num);
            return 1;
        }


        // binary search find the first smaller number
        int start = 0;
        int end = size - 1;

        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (sorted.get(mid) >= num) {
                start = mid;
            } else {
                end = mid;
            }
        }

        int count;
        if (sorted.get(start) < num) {
            // it's start
            count = size - start;
            sorted.add(start, num);
        } else {
            count = size - end;
            sorted.add(end, num);
        }

        return count;
    }
}
