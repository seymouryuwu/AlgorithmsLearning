package linkedList;

/*
https://leetcode.com/problems/reverse-linked-list/
 */
import dataStructure.ListNode;

public class ReverseLinkedList {
    public ListNode reverseList(ListNode head) {
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
}
