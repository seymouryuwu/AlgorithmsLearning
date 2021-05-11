package linkedList;

import dataStructure.ListNode;

public class SwapNodesInPairs {
    // Approach 1: recursion
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode newHead = head.next;

        head.next = swapPairs(newHead.next);
        newHead.next = head;

        return newHead;
    }

    // Approach 2: iteration
    public ListNode swapPairs2(ListNode head) {
        ListNode dummy = new ListNode(0, head);
        ListNode prev = dummy;

        while (head != null && head.next != null) {
            ListNode first = head;
            ListNode second = head.next;
            ListNode temp = second.next;

            second.next = first;
            first.next = temp;
            prev.next = second;

            prev = first;
            head = temp;
        }

        return dummy.next;
    }
}
