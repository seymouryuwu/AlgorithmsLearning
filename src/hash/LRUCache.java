package hash;

/*
https://leetcode.com/problems/lru-cache/
 */
import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    // Approach 1: singly linked list and hashmap
    private class Node {
        public int key;
        public int value;
        public Node next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
            next = null;
        }

        public Node(int key, int value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    int capacity;
    int size;
    Map<Integer, Node> previous;
    Node dummy;
    Node last;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        size = 0;
        previous = new HashMap<>();
        last = null;
        dummy = new Node(0, 0, last);
    }

    public int get(int key) {
        if (!previous.containsKey(key)) {
            return -1;
        } else {
            Node prev = previous.get(key);
            Node curt = prev.next;
            if (curt != last) {
                prev.next = curt.next;
                previous.put(curt.next.key, prev);

                last.next = curt;
                curt.next = null;
                previous.put(key, last);
                last = curt;
            }

            return curt.value;
        }
    }

    public void put(int key, int value) {
        if (size == 0) {
            Node node = new Node(key, value);
            dummy.next = node;
            previous.put(key, dummy);
            last = node;
            size++;
        } else {
            if (!previous.containsKey(key)) {
                Node node = new Node(key, value);
                last.next = node;
                previous.put(key, last);
                last = node;
                size++;

                if (size > capacity) {
                    Node head = dummy.next;
                    dummy.next = head.next;
                    previous.remove(head.key);
                    previous.put(head.next.key, dummy);
                    size--;
                }
            } else {
                Node prev = previous.get(key);
                Node curt = prev.next;
                if (curt != last) {
                    prev.next = curt.next;
                    previous.put(curt.next.key, prev);

                    last.next = curt;
                    curt.next = null;
                    previous.put(key, last);
                    last = curt;
                }

                curt.value = value;
            }
        }
    }

    // Approach 2: doubly linked list and hashmap
    // see another class -- LRUCache_Approach2
}
