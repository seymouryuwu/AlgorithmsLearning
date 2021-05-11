package array;

public class SortAnArray {
    // Approach 1: quick sort
    public int[] sortArray(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return nums;
        }

        quickSort(nums, 0, nums.length - 1);
        // int[] sorted = mergeSort(nums);

        return nums;
    }

    // Approach 2: merge sort
    public int[] sortArray2(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return nums;
        }

        // quickSort(nums, 0, nums.length - 1);

        return mergeSort(nums);
    }

    private void quickSort(int[] nums, int begin, int end) {
        if (begin < end) {
            int pivot = quickPartition(nums, begin, end);

            quickSort(nums, begin, pivot - 1);
            quickSort(nums, pivot + 1, end);
        }
    }

    private int quickPartition(int[] nums, int begin, int end) {
        int pivot = nums[end];
        int i = begin;

        for (int j = begin; j < end; j++) {
            if (nums[j] < pivot) {
                int temp = nums[j];
                nums[j] = nums[i];
                nums[i] = temp;

                i++;
            }
        }

        nums[end] = nums[i];
        nums[i] = pivot;

        return i;
    }

    private int[] mergeSort(int[] nums) {
        if (nums.length == 1) {
            return nums;
        }

        int mid = nums.length / 2;
        int[] first = new int[mid];
        int[] second = new int[nums.length - mid];

        for (int i = 0; i < mid; i++) {
            first[i] = nums[i];
        }

        for (int i = mid; i < nums.length; i++) {
            second[i - mid] = nums[i];
        }

        first = mergeSort(first);
        second = mergeSort(second);

        return merge(first, second);
    }

    private int[] merge(int[] first, int[] second) {
        int[] merged = new int[first.length + second.length];
        int i = 0;
        int j = 0;
        while (i < first.length && j < second.length) {
            if (first[i] <= second[j]) {
                merged[i + j] = first[i];
                i++;
            } else {
                merged[i + j] = second[j];
                j++;
            }
        }

        while (i < first.length) {
            merged[i + j] = first[i];
            i++;
        }

        while (j < second.length) {
            merged[i + j] = second[j];
            j++;
        }

        return merged;
    }
}
