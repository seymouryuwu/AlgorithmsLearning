package linkedList;

/*
https://leetcode.com/problems/partition-list/
 */
import dataStructure.ListNode;

public class PartitionList {
    public ListNode partition(ListNode head, int x) {
        ListNode lessDummy = new ListNode(0);
        ListNode greaterDummy = new ListNode(0);
        ListNode lessTail = lessDummy;
        ListNode greaterTail = greaterDummy;

        ListNode curt = head;
        while (curt != null) {
            if (curt.val < x) {
                lessTail.next = curt;
                lessTail = curt;
            } else {
                greaterTail.next = curt;
                greaterTail = curt;
            }

            curt = curt.next;
        }

        lessTail.next = greaterDummy.next;
        greaterTail.next = null;

        return lessDummy.next;
    }
}
