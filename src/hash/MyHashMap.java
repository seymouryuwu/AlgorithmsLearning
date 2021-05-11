package hash;

/*
https://leetcode.com/problems/design-hashmap/
 */
import java.util.ArrayList;
import java.util.List;

public class MyHashMap {
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private static final int DEFAULT_CAPACITY = 16;

    private float loadFactor;
    private int capacity;
    private int size;

    private List<MapNode<Integer, Integer>> buckets;

    private class MapNode<K, V> {
        final K key;
        V value;
        MapNode<K, V> next;

        public MapNode(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }

        public MapNode(K key, V value, MapNode<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    /** Initialize your data structure here. */
    public MyHashMap() {
        loadFactor = DEFAULT_LOAD_FACTOR;
        capacity = DEFAULT_CAPACITY;
        size = 0;
        buckets = new ArrayList<>(capacity);
        for (int i = 0; i < capacity; i++) {
            buckets.add(null);
        }
    }

    /** value will always be non-negative. */
    public void put(int key, int value) {
        int hash = hash(key);
        MapNode<Integer, Integer> head = buckets.get(hash);
        MapNode<Integer, Integer> curt = head;
        while (curt != null) {
            if (curt.key == key) {
                curt.value = value;

                return;
            } else {
                curt = curt.next;
            }
        }

        MapNode<Integer, Integer> newNode = new MapNode<>(key, value);
        newNode.next = head;
        buckets.set(hash, newNode);
        size++;

        if (size > loadFactor * capacity) {
            rehash();
        }
    }

    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        int hash = hash(key);
        MapNode<Integer, Integer> curt = buckets.get(hash);
        while (curt != null) {
            if (curt.key == key) {
                return curt.value;
            } else {
                curt = curt.next;
            }
        }

        return -1;
    }

    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        int hash = hash(key);
        MapNode<Integer, Integer> curt = buckets.get(hash);
        MapNode<Integer, Integer> dummy = new MapNode<>(-1, -1, curt);
        MapNode<Integer, Integer> prev = dummy;
        while (curt != null) {
            if (curt.key == key) {
                prev.next = curt.next;
                size--;
                break;
            } else {
                prev = curt;
                curt = curt.next;
            }
        }

        buckets.set(hash, dummy.next);
    }

    private int hash(int key) {
        return (key * 2069) % capacity;
    }

    private void rehash() {
        capacity = capacity * 2;
        List<MapNode<Integer, Integer>> newBuckets = new ArrayList<>(capacity);
        for (int i = 0; i < capacity; i++) {
            newBuckets.add(null);
        }

        for (MapNode<Integer, Integer> node : buckets) {
            if (node != null) {
                MapNode<Integer, Integer> curt = node;
                while (curt != null) {
                    MapNode<Integer, Integer> nextNode = curt.next;

                    int hash = hash(curt.key);
                    curt.next = newBuckets.get(hash);
                    newBuckets.set(hash, curt);

                    curt = nextNode;
                }
            }
        }

        buckets = newBuckets;
    }
}
