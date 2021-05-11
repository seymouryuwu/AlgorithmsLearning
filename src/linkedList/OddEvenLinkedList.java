package linkedList;

/*
https://leetcode.com/problems/odd-even-linked-list/
 */
import dataStructure.ListNode;

public class OddEvenLinkedList {
    public ListNode oddEvenList(ListNode head) {

        ListNode oddDummy = new ListNode();
        ListNode oddPrev = oddDummy;

        ListNode evenDummy = new ListNode();
        ListNode evenPrev = evenDummy;

        ListNode curt = head;
        while (curt != null) {
            oddPrev.next = curt;
            evenPrev.next = curt.next;

            oddPrev = curt;
            evenPrev = curt.next; // maybe null

            if (evenPrev != null) {
                curt = evenPrev.next;
            } else {
                curt = null;
            }
        }

        oddPrev.next = evenDummy.next;

        return oddDummy.next;
    }
}
