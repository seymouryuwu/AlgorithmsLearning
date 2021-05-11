package linkedList;

import dataStructure.ListNode;

public class SwappingNodesInALinkedList {
    public ListNode swapNodes(ListNode head, int k) {
        ListNode dummy = new ListNode(0, head);
        ListNode curt = head;
        ListNode prev = dummy;
        ListNode temp;

        ListNode first = head;
        ListNode beforeFirst = dummy;
        ListNode afterFirst = head.next;

        int i = 1;
        while (curt != null) {
            temp = curt.next;
            if (i == k) {
                first = curt;
                beforeFirst = prev;
                afterFirst = temp;
            }

            prev = curt;
            curt = curt.next;
            i++;
        }

        int length = i - 1;

        curt = head;
        prev = dummy;

        ListNode second = head;
        ListNode beforeSecond = dummy;
        ListNode afterSecond = head.next;

        i = 1;
        while (curt != null) {
            temp = curt.next;
            if (i == length - k + 1) {
                second = curt;
                beforeSecond = prev;
                afterSecond = temp;

                break;
            }

            prev = curt;
            curt = curt.next;
            i++;
        }

        if (beforeFirst != second) {
            beforeFirst.next = second;
            first.next = afterSecond;
        } else {
            first.next = second;
        }

        if (afterFirst != second) {
            second.next = afterFirst;
            beforeSecond.next = first;
        } else {
            second.next = first;
        }

        return dummy.next;
    }

    // Approach 2: swap value
    // see Leetcode solutions.
}
