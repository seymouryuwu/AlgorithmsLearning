package heap;

/*
https://leetcode.com/problems/sliding-window-median/
 */
import java.util.PriorityQueue;
import java.util.Queue;

public class SlidingWindowMedian {
    public double[] medianSlidingWindow(int[] nums, int k) {
        double[] medians = new double[nums.length - k + 1];

        MedianFinderSW mf = new MedianFinderSW();

        for (int i = 0; i < k - 1; i++) {
            mf.insert(nums[i]);
        }

        for (int i = 0; i < nums.length - k + 1; i++) {
            mf.insert(nums[i + k - 1]);
            medians[i] = mf.findMedian();
            mf.remove(nums[i]);
        }

        return medians;
    }
}

class MedianFinderSW {
    Queue<Integer> smaller;
    Queue<Integer> larger;

    public MedianFinderSW() {
        smaller = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
        larger = new PriorityQueue<>((a, b) -> Integer.compare(a, b));
    }

    public void insert(int num) {
        if (smaller.size() == larger.size()) {
            smaller.offer(num);
        } else {
            larger.offer(num);
        }

        if (!larger.isEmpty() && smaller.peek() > larger.peek()) {
            int temp = larger.poll();
            larger.offer(smaller.poll());
            smaller.offer(temp);
        }
    }

    public void remove(int num) {
        if (!larger.isEmpty() && num >= larger.peek()) {
            larger.remove(num);
        } else {
            smaller.remove(num);
        }

        if (smaller.size() < larger.size()) {
            smaller.offer(larger.poll());
        }
    }

    public double findMedian() {
        if (smaller.size() != larger.size()) {
            return (double)smaller.peek();
        } else {
            double a = (double)smaller.peek();
            double b = (double)larger.peek();
            return (a + b) / 2;
        }
    }
}
