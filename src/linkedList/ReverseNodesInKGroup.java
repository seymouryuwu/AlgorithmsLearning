package linkedList;

import dataStructure.ListNode;

public class ReverseNodesInKGroup {
    // Approach 1
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode newHead = findNodeK(head, k);
        if (newHead == null || newHead.equals(head)) return head;
        ListNode restReverse = reverseKGroup(newHead.next, k);

        ListNode currentReverse = newHead;
        for (int i = k; i > 1 ; i--) {
            currentReverse.next = findNodeK(head, i-1);
            currentReverse = currentReverse.next;
        }
        head.next = restReverse;
        return newHead;
    }

    private ListNode findNodeK(ListNode head, int k) {
        if (k == 1) return head;
        ListNode currentNode = head;
        for (int i = 1; i <= k; i++) {
            if (currentNode == null) {
                return head;
            }
            if (i == k)  break;
            currentNode = currentNode.next;
        }
        return currentNode;
    }

    // Approach 2
    public ListNode reverseKGroup2(ListNode head, int k) {
        if (head == null) {
            return null;
        }

        ListNode curt = head;
        for (int i = 0; i < k; i++) {
            if (curt == null) {
                return head;
            }

            curt = curt.next;
        }

        ListNode prev = reverseKGroup2(curt, k);
        curt = head;

        for (int i = 0; i < k; i++) {
            ListNode temp = curt.next;
            curt.next = prev;
            prev = curt;
            curt = temp;
        }

        return prev;
    }

    // Approach 3: iteration.
    // to do later
}
