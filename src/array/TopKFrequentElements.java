package array;

/*
https://leetcode.com/problems/top-k-frequent-elements/
 */
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class TopKFrequentElements {
    // Approach 1: quick sort
    public int[] topKFrequent(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }

        Map<Integer, Integer> count = new HashMap<>();

        for (int num : nums) {
            if (count.containsKey(num)) {
                count.put(num, count.get(num) + 1);
            } else {
                count.put(num, 1);
            }
        }

        int[] elements = new int[count.size()];
        int i = 0;
        for (Map.Entry<Integer, Integer> e : count.entrySet()) {
            elements[i] = e.getKey();
            i++;
        }

        quickSort(elements, count, 0, elements.length - 1);

        int[] results = new int[k];
        for (int j = 0; j < k; j++) {
            results[j] = elements[j];
        }
        return results;
    }

    private void quickSort(int[] elements, Map<Integer, Integer> count, int start, int end) {
        if (start >= end) {
            return;
        }

        int pivot = elements[end];
        int pivotFreq = count.get(pivot);

        int i = start;
        for (int j = start; j < end; j++) {
            if (count.get(elements[j]) > pivotFreq) {
                int temp = elements[j];
                elements[j] = elements[i];
                elements[i] = temp;

                i++;
            }
        }

        elements[end] = elements[i];
        elements[i] = pivot;

        quickSort(elements, count, start, i - 1);
        quickSort(elements, count, i + 1, end);
    }

    // Approach 2: heap
    public int[] topKFrequent2(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }

        Map<Integer, Integer> count = new HashMap<>();

        for (int num : nums) {
            if (count.containsKey(num)) {
                count.put(num, count.get(num) + 1);
            } else {
                count.put(num, 1);
            }
        }

        Queue<Integer> queue = new PriorityQueue<>((n1, n2) -> count.get(n1) - count.get(n2));

        for (int element : count.keySet()) {
            queue.offer(element);
            if (queue.size() > k) {
                queue.poll();
            }
        }

        int[] results = new int[k];
        for (int i = k - 1; i >= 0; i--) {
            results[i] = queue.poll();
        }

        return  results;
    }
}
