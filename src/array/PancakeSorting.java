package array;

/*
https://leetcode.com/problems/pancake-sorting/

The solution on the Leetcode assume the arr is ranged from 1 to the length and
there is and only is one of each number ranged from 1 to length.
However, this assumption is not mentioned in the description.
This Approach doesn't concern that assumption.
 */
import java.util.ArrayList;
import java.util.List;

public class PancakeSorting {
    public List<Integer> pancakeSort(int[] arr) {
        List<Integer> result = new ArrayList<>();
        if (arr == null || arr.length == 0) {
            return result;
        }

        int end = arr.length - 1;
        while (end >= 0) {
            int maxIndex = findMax(arr, end);
            if (maxIndex == end) {
                end--;
            } else if (maxIndex == 0) {
                result.add(end + 1);
                flip(arr, end);
                end--;
            } else {
                result.add(maxIndex + 1);
                flip(arr, maxIndex);

                result.add(end + 1);
                flip(arr, end);
                end--;
            }
        }

        return result;
    }

    private int findMax(int[] arr, int end) {
        int index = 0;
        int max = arr[0];
        for (int i = 0; i <= end; i++) {
            if (arr[i] >= max) {
                max = arr[i];
                index = i;
            }
        }

        return index;
    }

    private void flip(int[] arr, int end) {
        int left = 0;
        int right = end;

        while (left < right) {
            int temp = arr[right];
            arr[right] = arr[left];
            arr[left] = temp;

            left++;
            right--;
        }
    }
}
