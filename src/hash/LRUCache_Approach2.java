package hash;

import java.util.HashMap;
import java.util.Map;

public class LRUCache_Approach2 {
    private class Node {
        public int key;
        public int value;
        public Node prev;
        public Node next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
            prev = null;
            next = null;
        }

        public Node(int key, int value, Node prev, Node next) {
            this.key = key;
            this.value = value;
            this.prev = prev;
            this.next = next;
        }
    }

    int capacity;
    int size;
    Map<Integer, Node> hash;
    Node head;
    Node tail;

    public LRUCache_Approach2(int capacity) {
        this.capacity = capacity;
        size = 0;
        hash = new HashMap<>();
        head = null;
        tail = null;
    }

    public int get(int key) {
        if (!hash.containsKey(key)) {
            return -1;
        } else {
            Node curt = hash.get(key);
            Node prev = curt.prev;
            Node next = curt.next;

            if (next != null) {
                tail.next = curt;
                curt.prev = tail;
                curt.next = null;
                tail = curt;

                if (prev == null) {
                    head = next;
                    next.prev = null;
                } else {
                    prev.next = next;
                    next.prev = prev;
                }
            }

            return curt.value;
        }
    }

    public void put(int key, int value) {
        if (size == 0) {
            Node node = new Node(key, value);
            hash.put(key, node);
            head = node;
            tail = node;
            size++;
        } else {
            if (!hash.containsKey(key)) {
                Node node = new Node(key, value);
                tail.next = node;
                node.prev = tail;
                hash.put(key, node);
                tail = node;
                size++;

                if (size > capacity) {
                    hash.remove(head.key);
                    head.next.prev = null;
                    head = head.next;
                    size--;
                }
            } else {
                Node curt = hash.get(key);
                Node prev = curt.prev;
                Node next = curt.next;
                if (next != null) {
                    tail.next = curt;
                    curt.prev = tail;
                    curt.next = null;
                    tail = curt;

                    if (prev == null) {
                        head = next;
                        next.prev = null;
                    } else {
                        prev.next = next;
                        next.prev = prev;
                    }
                }

                curt.value = value;
            }
        }
    }
}
