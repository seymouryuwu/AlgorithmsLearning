package linkedList;

/*
https://leetcode.com/problems/copy-list-with-random-pointer/
 */
import dataStructure.ListNodeWithPointer;

import java.util.HashMap;
import java.util.Map;

public class CopyListWithRandomPointer {
    public ListNodeWithPointer copyRandomList(ListNodeWithPointer head) {
        if (head == null) {
            return head;
        }

        Map<ListNodeWithPointer, ListNodeWithPointer> copyMap = new HashMap<>();

        ListNodeWithPointer curt = head;
        while (curt != null) {
            ListNodeWithPointer copyCurt = new ListNodeWithPointer(curt.val);
            copyMap.put(curt, copyCurt);

            curt = curt.next;
        }

        curt = head;
        while (curt != null) {
            ListNodeWithPointer copyCurt = copyMap.get(curt);
            if (curt.next != null) {
                copyCurt.next = copyMap.get(curt.next);
            }

            copyCurt.random = copyMap.get(curt.random);

            curt = curt.next;
        }

        return copyMap.get(head);
    }

    // Approach 2: no extra space
    public ListNodeWithPointer copyRandomList2(ListNodeWithPointer head) {
        if (head ==  null) {
            return head;
        }

        ListNodeWithPointer curt = head;
        while (curt != null) {
            ListNodeWithPointer copyNode = new ListNodeWithPointer(curt.val);
            ListNodeWithPointer temp = curt.next;
            curt.next = copyNode;
            copyNode.next = temp;

            curt = temp;
        }

        curt = head;
        while (curt != null) {
            ListNodeWithPointer copyNode = curt.next;
            if (curt.random != null) {
                copyNode.random = curt.random.next;
            }

            curt = copyNode.next;
        }

        ListNodeWithPointer copyHead = head.next;

        curt = head;
        while (curt != null) {
            ListNodeWithPointer copyCurt = curt.next;
            ListNodeWithPointer nextCurt = copyCurt.next;

            curt.next = nextCurt;
            if (nextCurt != null) {
                copyCurt.next = nextCurt.next;
            }

            curt = nextCurt;
        }

        return copyHead;
    }
}
