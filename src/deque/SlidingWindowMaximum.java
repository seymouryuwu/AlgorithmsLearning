package deque;
/*
https://leetcode.com/problems/sliding-window-maximum/
 */

import java.util.ArrayDeque;
import java.util.Deque;

public class SlidingWindowMaximum {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] result = new int[nums.length - k + 1];
        // A deque that stores the index of the numbers in the window.
        // When a number comes in, in the deque only the numbers which are greater than
        // the new number are possible to be the max in the current window, so we poll
        // out all the smaller number when a new number comes in.

        // deque stores index!
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < k; i++) {
            offerDeque(deque, nums, i);
        }

        result[0] = nums[deque.peekFirst()];

        for (int i = k; i < nums.length; i++) {
            offerDeque(deque, nums, i);

            if (deque.peekFirst() == i - k) {
                deque.pollFirst();
            }

            result[i - k + 1] = nums[deque.peekFirst()];
        }

        return result;
    }

    private void offerDeque(Deque<Integer> deque, int[] nums, int index) {
        while(!deque.isEmpty() && nums[deque.peekLast()] <= nums[index]) {
            deque.pollLast();
        }

        deque.offerLast(index);
    }
}
