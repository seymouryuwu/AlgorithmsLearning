package binarySearch;

/*
https://leetcode.com/problems/peak-index-in-a-mountain-array/
 */
public class PeakIndexInAMountainArray {
    public int peakIndexInMountainArray(int[] arr) {
        if (arr == null || arr.length < 3) {
            return -1;
        }

        int start = 1;
        int end = arr.length - 2;

        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (arr[mid] > arr[mid - 1] && arr[mid] > arr[mid + 1]) {
                return mid;
            } else if (arr[mid] > arr[mid - 1]) {
                start = mid;
            } else {
                end = mid;
            }
        }

        if (arr[start] > arr[end]) {
            return start;
        } else {
            return end;
        }

        // return Math.max(arr[start], arr[end]);
    }
}
