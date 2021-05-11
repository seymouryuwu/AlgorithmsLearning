package Trie;

/*
https://leetcode.com/problems/implement-trie-prefix-tree/
 */
public class ImplementTrie {

}

class Trie {
    Trie[] successors;
    boolean isWord;
    String word;

    /** Initialize your data structure here. */
    public Trie() {
        successors = new Trie[26];
        isWord = false;
        word = null;
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        if (word == null || word.length() == 0) {
            return;
        }

        char[] wordArray = word.toCharArray();
        Trie curr = this;
        for (char c : wordArray) {
            if (curr.successors[c - 'a'] == null) {
                curr.successors[c - 'a'] = new Trie();
            }

            curr = curr.successors[c - 'a'];
        }

        curr.isWord = true;
        curr.word = word;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        if (word == null || word.length() == 0) {
            return false;
        }

        char[] wordArray = word.toCharArray();
        Trie curr = this;
        for (int i = 0; i < wordArray.length; i++) {
            char c = wordArray[i];
            if (curr.successors[c - 'a'] == null) {
                return false;
            }

            curr = curr.successors[c - 'a'];
        }

        return curr.isWord;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        if (prefix == null) {
            return false;
        }

        char[] prefixArray = prefix.toCharArray();
        Trie curr = this;
        for (int i = 0; i < prefixArray.length; i++) {
            char c = prefixArray[i];
            if (curr.successors[c - 'a'] == null) {
                return false;
            }

            curr = curr.successors[c - 'a'];
        }

        return true;
    }
}

/*
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */