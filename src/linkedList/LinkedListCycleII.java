package linkedList;

/*
https://leetcode.com/problems/linked-list-cycle-ii/
 */
import dataStructure.ListNode;

public class LinkedListCycleII {
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }

        ListNode slow = head;
        ListNode fast = head.next;

        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return null;
            }

            slow = slow.next;
            fast= fast.next.next;
        }

        ListNode pointer1 = slow;
        ListNode pointer2 = new ListNode(0);
        pointer2.next = head;

        while (pointer1 != pointer2) {
            pointer1 = pointer1.next;
            pointer2 = pointer2.next;
        }

        return pointer1;
    }
}
