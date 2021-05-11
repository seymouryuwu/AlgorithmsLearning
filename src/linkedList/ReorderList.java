package linkedList;

/*
https://leetcode.com/problems/reorder-list/
 */
import dataStructure.ListNode;

public class ReorderList {
    public void reorderList(ListNode head) {
        if (head == null) {
            return;
        }

        int length = findLength(head);

        ListNode secondHead = divideList(head, length);

        secondHead = reverseList(secondHead);

        mergeLists(head, secondHead);
    }

    private int findLength(ListNode head) {
        int length = 0;
        while (head != null) {
            head = head.next;
            length++;
        }

        return length;
    }

    private ListNode divideList(ListNode head, int length) {
        int firstLength;
        if (length % 2 == 0) {
            firstLength = length / 2;
        } else {
            firstLength = length / 2 + 1;
        }

        for (int i = 1; i < firstLength; i++) {
            head = head.next;
        }

        ListNode secondHead = head.next;
        head.next = null;

        return secondHead;
    }

    private ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curt = head;
        while (curt != null) {
            ListNode temp = curt.next;
            curt.next = prev;
            prev = curt;
            curt = temp;
        }

        return prev;
    }

    private void mergeLists(ListNode first, ListNode second) {
        while(second != null) {
            ListNode firstTemp = first.next;
            first.next = second;
            ListNode secondTemp = second.next;
            second.next = firstTemp;

            first = firstTemp;
            second = secondTemp;
        }
    }
}
