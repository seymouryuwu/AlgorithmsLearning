package heap;

/*
https://leetcode.com/problems/find-median-from-data-stream/
 */
import java.util.PriorityQueue;
import java.util.Queue;

public class FindMedianFromDataStream {

}

class MedianFinder {
    Queue<Integer> smaller;
    Queue<Integer> larger;

    /** initialize your data structure here. */
    public MedianFinder() {
        // smaller is a max heap
        smaller = new PriorityQueue<>((a, b) -> b - a);
        // larger is a min heap
        larger = new PriorityQueue<>((a, b) -> a - b);
    }

    public void addNum(int num) {
        // make sure the size of smaller is always bigger than or equals to larger
        // so if the list is even, the median will be smaller.peek();
        int s = (!smaller.isEmpty()) ? smaller.peek() : Integer.MIN_VALUE;
        int l = (!larger.isEmpty()) ? larger.peek() : Integer.MAX_VALUE;

        if (num < s) {
            if (smaller.size() != larger.size()) {
                larger.offer(smaller.poll());
            }
            smaller.offer(num);
        } else if (num <= l) {
            if (smaller.size() == larger.size()) {
                smaller.offer(num);
            } else {
                larger.offer(num);
            }
        } else {
            if (smaller.size() == larger.size()) {
                smaller.offer(larger.poll());
            }
            larger.offer(num);
        }
    }

    public double findMedian() {
        if (smaller.size() > larger.size()) {
            return (double)smaller.peek();
        } else {
            double a = (double)smaller.peek();
            double b = (double)larger.peek();
            return (a + b) / 2;
        }
    }
}

// A neat way
class MedianFinder2 {
    Queue<Integer> smaller;
    Queue<Integer> larger;

    /** initialize your data structure here. */
    public MedianFinder2() {
        // smaller is a max heap
        smaller = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
        // larger is a min heap
        larger = new PriorityQueue<>((a, b) -> Integer.compare(a, b));

        smaller.offer(Integer.MIN_VALUE);
        larger.offer(Integer.MAX_VALUE);
    }

    public void addNum(int num) {
        // make sure the size of smaller is always bigger than or equals to larger's
        // so if the list is even, the median will be smaller.peek();

        if (smaller.size() == larger.size()) {
            smaller.offer(num);
        } else {
            larger.offer(num);
        }

        if (smaller.peek() > larger.peek()) {
            int temp = larger.poll();
            larger.offer(smaller.poll());
            smaller.offer(temp);
        }
    }

    public double findMedian() {
        if (smaller.size() > larger.size()) {
            return (double)smaller.peek();
        } else {
            double a = (double)smaller.peek();
            double b = (double)larger.peek();
            return (a + b) / 2;
        }
    }
}

/*
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
