package linkedList;

/*
https://leetcode.com/problems/rotate-list/
 */
import dataStructure.ListNode;

public class RotateList {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) {
            return head;
        }

        int length = 1;
        ListNode last = head;
        while (last.next != null) {
            last = last.next;
            length++;
        }

        k = k % length;
        last.next = head;

        ListNode curt = head;
        for (int i = 1; i < length - k; i++) {
            curt = curt.next;
        }

        ListNode newHead = curt.next;
        curt.next = null;

        return newHead;
    }
}
