package linkedList;

/*
https://leetcode.com/problems/merge-two-sorted-lists/
 */
import dataStructure.ListNode;

public class MergeTwoSortedLists {
    // Approach 1: just try
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        ListNode lastNode = new ListNode();
        ListNode mergedList = lastNode;

        while(l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                ListNode newListNode = new ListNode(l1.val);
                lastNode.next = newListNode;
                lastNode = newListNode;
                l1 = l1.next;
            } else {
                ListNode newListNode = new ListNode(l2.val);
                lastNode.next = newListNode;
                lastNode = newListNode;
                l2 = l2.next;
            }
        }
        if (l1 == null) lastNode.next = l2;
        if (l2 == null) lastNode.next = l1;

        return mergedList.next;
    }

    // Approach 2: recursion
    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        else if (l2 == null) {
            return l1;
        }
        else if (l1.val < l2.val) {
            l1.next = mergeTwoLists2(l1.next, l2);
            return l1;
        }
        else {
            l2.next = mergeTwoLists2(l1, l2.next);
            return l2;
        }
    }

    // Approach 3: best way
    public ListNode mergeTwoLists3(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode prev = dummy;
        ListNode curt;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                curt = l1;
                l1 = l1.next;
            } else {
                curt = l2;
                l2 = l2.next;
            }

            prev.next = curt;
            prev = curt;
        }

        if (l1 == null) {
            prev.next = l2;
        }

        if (l2 ==  null) {
            prev.next = l1;
        }

        return dummy.next;
    }
}
