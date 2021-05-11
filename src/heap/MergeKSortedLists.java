package heap;


import dataStructure.ListNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

public class MergeKSortedLists {
    // Approach 1: heap
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        Queue<ListNode> heap = new PriorityQueue<>((ListNode l1, ListNode l2) -> l1.val - l2.val);
        for (ListNode list : lists) {
            if (list != null) {
                heap.offer(list);
            }
        }

        ListNode dummy = new ListNode(0);
        ListNode prev = dummy;

        while (!heap.isEmpty()) {
            ListNode curt = heap.poll();
            if (curt.next != null) {
                heap.offer(curt.next);
            }

            prev.next = curt;
            prev = curt;
        }

        return dummy.next;
    }

    // Approach 2: without heap
    public ListNode mergeKLists2(ListNode[] lists) {
        int minIndex = 0;

        ListNode dummyNode = new ListNode();
        ListNode lastNode = dummyNode;

        boolean isAllNull = false;
        while (!isAllNull) {
            isAllNull = true;
            ListNode minNode = new ListNode(Integer.MAX_VALUE);
            for (int i = 0; i < lists.length; i++) {
                if (lists[i] != null && lists[i].val <= minNode.val) {
                    minIndex = i;
                    minNode = lists[i];
                    isAllNull = false;
                }
            }
            if (!isAllNull) {
                lastNode.next = minNode;
                lastNode = lastNode.next;
                lists[minIndex] = lists[minIndex].next;
            }
        }
        return dummyNode.next;
    }

    // Approach 3: worst space complexity
    public ListNode mergeKLists3(ListNode[] lists) {
        ArrayList<Integer> l = new ArrayList<>();

        for (ListNode ln : lists) {
            while (ln != null) {
                l.add(ln.val);
                ln = ln.next;
            }
        }

        Collections.sort(l);

        ListNode head = new ListNode(0);
        ListNode h = head;
        for (int i : l) {
            h.next = new ListNode(i);
            h = h.next;
        }
        h.next = null;
        return head.next;
    }

    // Approach 4: merge sort
    public ListNode mergeKLists4(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        if (lists.length == 1) {
            return lists[0];
        }

        if (lists.length == 2) {
            return mergeTwoLists(lists[0], lists[1]);
        }

        int mid = lists.length / 2;
        ListNode[] first = new ListNode[mid];
        ListNode[] second = new ListNode[lists.length - mid];
        for (int i = 0; i < mid; i++) {
            first[i] = lists[i];
        }

        for (int i = mid; i < lists.length; i++) {
            second[i - mid] = lists[i];
        }

        ListNode firstMerged = mergeKLists4(first);
        ListNode secondMerged = mergeKLists4(second);
        return mergeTwoLists(firstMerged, secondMerged);
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
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
