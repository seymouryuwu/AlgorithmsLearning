package linkedList;

/*
https://leetcode.com/problems/intersection-of-two-linked-lists/
 */
import dataStructure.ListNode;

import java.util.HashSet;
import java.util.Set;

public class IntersectionOfTwoLinkedLists {
    // Approach 1: hash table
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        Set<ListNode> nodesInA = new HashSet<>();
        ListNode curt = headA;
        while (curt != null) {
            nodesInA.add(curt);
            curt = curt.next;
        }

        curt = headB;
        while (curt != null) {
            if (nodesInA.contains(curt)) {
                return curt;
            }

            curt = curt.next;
        }

        return null;
    }

    // Approach 2: two pointers
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        ListNode pointerA = headA;
        ListNode pointerB = headB;

        while (pointerA != pointerB) {
            if (pointerA.next == null && pointerB.next == null) {
                return null;
            }

            pointerA = (pointerA.next == null) ? headB : pointerA.next;
            pointerB = (pointerB.next == null) ? headA : pointerB.next;
        }

        return pointerA;
    }
}
