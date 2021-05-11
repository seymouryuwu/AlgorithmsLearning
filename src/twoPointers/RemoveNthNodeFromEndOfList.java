package twoPointers;

/*
https://leetcode.com/problems/remove-nth-node-from-end-of-list/
 */
import dataStructure.ListNode;

public class RemoveNthNodeFromEndOfList {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return null;
        }

        ListNode dummy = new ListNode(0, head);

        ListNode left = dummy;
        for (int i = 0; i < n; i++) {
            left = left.next;
        }

        ListNode right = head;
        ListNode prev = dummy;
        while (left.next != null) {
            left = left.next;
            right = right.next;
            prev = prev.next;
        }

        prev.next = right.next;

        return dummy.next;
    }
}
