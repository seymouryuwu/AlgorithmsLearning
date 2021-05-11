package linkedList;

/*
https://leetcode.com/problems/sort-list/
 */
import dataStructure.ListNode;

public class SortList {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        int length = 0;
        ListNode curt = head;
        while (curt != null) {
            curt = curt.next;
            length++;
        }

        curt = head;
        for (int i = 1; i < length / 2; i++) {
            curt = curt.next;
        }

        ListNode secondHead = curt.next;
        curt.next = null;
        ListNode firstSorted = sortList(head);
        ListNode secondSorted = sortList(secondHead);

        return mergeLists(firstSorted, secondSorted);
    }

    private ListNode mergeLists(ListNode firstSorted, ListNode secondSorted) {
        ListNode dummy = new ListNode(0);
        ListNode prev = dummy;
        ListNode curt;

        while (firstSorted != null && secondSorted != null) {
            if (firstSorted.val <= secondSorted.val) {
                curt = firstSorted;
                firstSorted = firstSorted.next;
            } else {
                curt = secondSorted;
                secondSorted = secondSorted.next;
            }

            prev.next = curt;
            prev = curt;
        }

        if (firstSorted == null) {
            prev.next = secondSorted;
        }

        if (secondSorted == null) {
            prev.next = firstSorted;
        }

        return dummy.next;
    }
}
