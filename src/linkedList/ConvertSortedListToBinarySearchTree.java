package linkedList;

/*
https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/
 */
import dataStructure.ListNode;
import dataStructure.TreeNode;

public class ConvertSortedListToBinarySearchTree {
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }

        TreeNode result = new TreeNode();
        ListNode mid = findMid(head);
        result.val = mid.val;

        if (mid == head) {
            result.left = null;
        } else {
            result.left = sortedListToBST(head);
        }

        result.right = sortedListToBST(mid.next);

        return result;
    }

    private ListNode findMid(ListNode head) {
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

        ListNode mid = curt.next;
        curt.next = null;

        return mid;
    }
}
