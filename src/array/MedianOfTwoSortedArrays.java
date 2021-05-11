package array;

/*
https://leetcode.com/problems/median-of-two-sorted-arrays/
 */
public class MedianOfTwoSortedArrays {
    // Approach 1: time complexity O(m + n)
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int length = nums1.length + nums2.length;
        if (length == 0) {
            return Double.MIN_VALUE;
        }

        int i = 0;
        int j = 0;
        int former = 0;
        double median = 0;
        while (i + j <= length / 2) {
            former = (int) median;
            if (i < nums1.length && j < nums2.length) {
                if (nums1[i] <= nums2[j]) {
                    median = nums1[i];
                    i++;
                } else {
                    median = nums2[j];
                    j++;
                }
            } else if (i < nums1.length) {
                median = nums1[i];
                i++;
            } else if (j < nums2.length) {
                median = nums2[j];
                j++;
            }
        }

        return (length % 2 == 1) ? median : (median + former) / 2;
    }

    // Approach 2: time complexity O(log(m+n))
    // Using recursion it will be easier.
    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        if ((nums1 == null && nums2 == null) ||
                (nums1 == null && nums2.length == 0) ||
                (nums2 == null && nums1.length == 0) ||
                (nums1 != null && nums2 != null && nums1.length + nums2.length == 0)) {
            return Double.MIN_VALUE;
        }

        double median;

        if (nums1 == null || nums1.length == 0) {
            int k = (nums2.length % 2 == 0) ? nums2.length / 2 : nums2.length / 2 + 1;
            median = nums2[k - 1];
            return (nums2.length % 2 == 0) ? (median + nums2[k]) / 2 : median;
        }

        if (nums2 == null || nums2.length == 0) {
            int k = (nums1.length % 2 == 0) ? nums1.length / 2 : nums1.length / 2 + 1;
            median = nums1[k - 1];
            return (nums1.length % 2 == 0) ? (median + nums1[k]) / 2 : median;
        }

        int length = nums1.length + nums2.length;
        int k = (length % 2 == 0) ? length / 2 : length / 2 + 1;

        int start1 = 0;
        int start2 = 0;

        while (k > 1) {
            if (start1 + k / 2 >= nums1.length) {
                if (nums1[nums1.length - 1] <= nums2[start2 + k / 2 - 1]) {
                    median = nums2[start1 + start2 + k - nums1.length - 1];
                    if (length % 2 == 1) {
                        return median;
                    } else {
                        return (median + nums2[start1 + start2 + k - nums1.length]) / 2;
                    }

                } else {
                    start2 = start2 + k / 2;
                }

            } else if (start2 + k / 2 >= nums2.length) {
                if (nums2[nums2.length - 1] <= nums1[start1 + k / 2 - 1]) {
                    median = nums1[start1 + start2 + k - nums2.length - 1];
                    if (length % 2 == 1) {
                        return median;
                    } else {
                        return (median + nums1[start1 + start2 + k - nums2.length]) / 2;
                    }

                } else {
                    start1 = start1 + k / 2;
                }

            } else {
                if (nums1[start1 + k / 2 - 1] < nums2[start2 + k / 2 - 1]) {
                    start1 = start1 + k / 2;
                } else {
                    start2 = start2 + k / 2;
                }
            }

            k = k - k / 2;
        }

        if (nums1[start1] < nums2[start2]) {
            median = nums1[start1++];
        } else {
            median = nums2[start2++];
        }

        if (length % 2 == 1) {
            return median;
        } else {
            if (start1 >= nums1.length) {
                return (median + nums2[start2]) / 2;
            }

            if (start2 >= nums2.length) {
                return (median + nums1[start1]) / 2;
            }

            return (median + Math.min(nums1[start1], nums2[start2])) / 2;
        }
    }

    // Approach 3: time complexity O(log(m+n)), recursion
    public double findMedianSortedArrays3(int[] nums1, int[] nums2) {
        if ((nums1 == null && nums2 == null) ||
                (nums1 == null && nums2.length == 0) ||
                (nums2 == null && nums1.length == 0) ||
                (nums1 != null && nums2 != null && nums1.length + nums2.length == 0)) {
            return Double.MIN_VALUE;
        }

        double median;
        if (nums1 == null) {
            int k = (nums2.length % 2 == 0) ? nums2.length / 2 : nums2.length / 2 + 1;
            median = nums2[k - 1];
            return (nums2.length % 2 == 0) ? (median + nums2[k]) / 2 : median;
        }

        if (nums2 == null) {
            int k = (nums1.length % 2 == 0) ? nums1.length / 2 : nums1.length / 2 + 1;
            median = nums1[k - 1];
            return (nums1.length % 2 == 0) ? (median + nums1[k]) / 2 : median;
        }

        int length = nums1.length + nums2.length;
        if (length % 2 == 1) {
            median = findKth(nums1, 0, nums2, 0, length / 2 + 1);
        } else {
            median = findKth(nums1, 0, nums2, 0, length / 2);
            median = (median + findKth(nums1, 0, nums2, 0, length / 2 + 1)) / 2;
        }

        return median;
    }

    private int findKth(int[] nums1, int start1, int[] nums2, int start2, int k) {
        if (start1 >= nums1.length) {
            return nums2[k - 1];
        }

        if (start2 >= nums2.length) {
            return nums1[k - 1];
        }

        if (k == 1) {
            return Math.min(nums1[start1], nums2[start2]);
        }

        int value1 = (start1 + k / 2 > nums1.length) ? Integer.MAX_VALUE : nums1[start1 + k / 2 - 1];
        int value2 = (start2 + k / 2 > nums2.length) ? Integer.MAX_VALUE : nums2[start2 + k / 2 - 1];

        if (value1 < value2) {
            return findKth(nums1, start1 + k / 2, nums2, start2, k - k / 2);
        } else {
            return findKth(nums1, start1, nums2, start2 + k / 2, k - k / 2);
        }
    }
}
