package hash;

/*
https://leetcode.com/problems/design-hashset/
 */
import java.util.ArrayList;
import java.util.List;

public class MyHashSet {
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private static final int DEFAULT_CAPACITY = 16;

    private float loadFactor;
    private int capacity;
    private int size;

    private List<HashNode<Integer>> buckets;

    private class HashNode<E> {
        public E element;
        public HashNode<E> next;

        public HashNode(E e) {
            element = e;
            next = null;
        }

        public HashNode(E e, HashNode<E> n) {
            element = e;
            next = n;
        }
    }

    /** Initialize your data structure here. */
    public MyHashSet() {
        loadFactor = DEFAULT_LOAD_FACTOR;
        capacity = DEFAULT_CAPACITY;
        size = 0;

        buckets = new ArrayList<>(capacity);
        for (int i = 0; i < capacity; i++) {
            buckets.add(null);
        }
    }

    public void add(int key) {
        int hash = hash(key);
        HashNode<Integer> head = buckets.get(hash);
        HashNode<Integer> curt = head;

        while (curt != null) {
            if (curt.element == key) {
                return;
            }

            curt = curt.next;
        }

        buckets.set(hash, new HashNode<>(key, head));
        size++;

        if (size > loadFactor * capacity) {
            rehash();
        }
    }

    public void remove(int key) {
        int hash = hash(key);
        HashNode<Integer> head = buckets.get(hash);
        HashNode<Integer> dummy = new HashNode<>(-1, head);

        HashNode<Integer> curt = head;
        HashNode<Integer> prev = dummy;

        while (curt != null) {
            if (curt.element == key) {
                prev.next = curt.next;
                size--;
                buckets.set(hash, dummy.next);
                return;
            }

            prev = curt;
            curt = curt.next;
        }
    }

    /** Returns true if this set contains the specified element */
    public boolean contains(int key) {
        int hash = hash(key);
        HashNode<Integer> curt = buckets.get(hash);
        while (curt != null) {
            if (curt.element == key) {
                return true;
            }

            curt = curt.next;
        }

        return false;
    }

    private int hash(int key) {
        return (key * 2069) % capacity;
    }

    private void rehash() {
        capacity = capacity * 2;
        List<HashNode<Integer>> newBuckets = new ArrayList<>(capacity);
        for (int i = 0; i < capacity; i++) {
            newBuckets.add(null);
        }

        for (HashNode<Integer> node : buckets) {
            HashNode<Integer> curt = node;
            while (curt != null) {
                HashNode<Integer> nextNode = curt.next;

                int hash = hash(curt.element);
                curt.next = newBuckets.get(hash);
                newBuckets.set(hash, curt);

                curt = nextNode;
            }
        }

        buckets = newBuckets;
    }
}
