package array;

/*
https://leetcode.com/problems/merge-sorted-array/
 */
import java.util.Arrays;

public class MergeSortedArray {
    // Approach 1
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        for (int i = 0; i < n; i++) {
            nums1[i + m] = nums2[i];
        }

        Arrays.sort(nums1);
    }

    // Approach 2
    public void merge2(int[] nums1, int m, int[] nums2, int n) {
        int[] merged = new int[m + n];
        int index1 = 0;
        int index2 = 0;
        for (int i = 0; i < m + n; i++) {
            if (index1 >= m) {
                merged[i] = nums2[index2++];
            } else if (index2 >= n) {
                merged[i] = nums1[index1++];
            } else {
                if (nums1[index1] <= nums2[index2]) {
                    merged[i] = nums1[index1++];
                } else {
                    merged[i] = nums2[index2++];
                }
            }
        }

        for (int i = 0; i < m + n; i++) {
            nums1[i] = merged[i];
        }
    }

    // Approach 3: from end to start. Space O(1).
    public void merge3(int[] nums1, int m, int[] nums2, int n) {
        int index = m + n - 1;
        int index1 = m - 1;
        int index2 = n - 1;

        while (index1 >= 0 && index2 >= 0) {
            if (nums1[index1] > nums2[index2]) {
                nums1[index--] = nums1[index1--];
            } else {
                nums1[index--] = nums2[index2--];
            }
        }

        while (index1 >= 0) {
            nums1[index--] = nums1[index1--];
        }

        while (index2 >= 0) {
            nums1[index--] = nums2[index2--];
        }
    }
}
