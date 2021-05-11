package dataStructure;

/*
https://leetcode.com/problems/word-search-ii/
 */

import java.util.HashMap;
import java.util.Map;

class Trie {
    Map<Character, Trie> successors;
    boolean isWord;
    String word;

    public Trie() {
        successors = new HashMap<>();
        isWord = false;
        word = null;
    }

    public void insert(String word) {
        char[] wordArray = word.toCharArray();

        Trie curr = this;

        for (char c : wordArray) {
            if (!curr.successors.containsKey(c)) {
                curr.successors.put(c, new Trie());
            }

            curr = curr.successors.get(c);
        }

        curr.isWord = true;
        curr.word = word;
    }

    public boolean find(String word) {
        char[] wordArray = word.toCharArray();

        Trie curr = this;

        for (char c : wordArray) {
            if (!curr.successors.containsKey(c)) {
                return false;
            }

            curr = curr.successors.get(c);
        }

        return curr.isWord;
    }
}
