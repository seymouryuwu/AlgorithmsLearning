package medium;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.List;
import java.util.Objects;

/**
 * Definition for singly-linked list.
 */

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

public class AddTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode sumNode = new ListNode((l1.val + l2.val)%10);
        boolean hasCarry = l1.val + l2.val > 9;
        boolean isAddFinished = false;
        if (l1.next == null) {
            if (l2.next == null) {
                isAddFinished = true;
                if (hasCarry) sumNode.next = new ListNode(1);
            } else {
                l1 = new ListNode(0);
                l2 = l2.next;
                if (hasCarry) l1.val += 1;
            }
        } else {
            if (l2.next == null) {
                l1 = l1.next;
                l2 = new ListNode(0);
                if (hasCarry) l1.val += 1;
            } else {
                l1 = l1.next;
                l2 = l2.next;
                if (hasCarry) l1.val += 1;
            }
        }

        if (!isAddFinished) {
            sumNode.next = addTwoNumbers(l1, l2);
        }

        return sumNode;
    }

    int carry;
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        int sum = l1.val + l2.val + carry;
        carry = sum/10;
        ListNode sumNode = new ListNode(sum%10);
        if (l1.next == null && l2.next == null) {
            if (carry == 1) sumNode.next = new ListNode(1);
        } else if (l1.next == null) {
            sumNode.next = addTwoNumbers2(new ListNode(0), l2.next);
        } else sumNode.next = addTwoNumbers2(l1.next, Objects.requireNonNullElseGet(l2.next, () -> new ListNode(0)));

        return sumNode;
    }

    public ListNode addTwoNumbers3(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode p = l1, q = l2, curr = dummyHead;
        int carry = 0;
        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }
        return dummyHead.next;
    }
}
