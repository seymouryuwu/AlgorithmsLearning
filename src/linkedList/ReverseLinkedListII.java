package linkedList;

/*
https://leetcode.com/problems/reverse-linked-list-ii/
 */
import dataStructure.ListNode;

public class ReverseLinkedListII {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode prev = dummy;
        ListNode curt = head;
        ListNode temp;

        ListNode beforeLast = dummy;;
        ListNode reverseFirst = head;

        int i = 1;
        while (curt != null) {
            temp = curt.next;

            if (i == m) {
                beforeLast = prev;
                reverseFirst = curt;
            }

            if (i > m && i <= n) {
                curt.next = prev;
            }

            if (i == n) {
                beforeLast.next = curt;
                reverseFirst.next = temp;
            }

            prev = curt;
            curt = temp;
            i++;
        }

        return dummy.next;
    }
}
