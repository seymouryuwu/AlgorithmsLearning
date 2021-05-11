package Trie;

/*
https://leetcode.com/problems/implement-trie-ii-prefix-tree/
 */
public class ImplementTrieII {

}

class TrieII {
    TrieII[] successors;
    int countWord;
    int countPrefix;
    String word;

    public TrieII() {
        successors = new TrieII[26];
        countWord = 0;
        countPrefix = 0;
        word = null;
    }

    public void insert(String word) {
        if (word == null) {
            return;
        }

        char[] wordArray = word.toCharArray();
        TrieII curr = this;
        for (int i = 0; i < wordArray.length; i++) {
            char c = wordArray[i];
            if (curr.successors[c - 'a'] == null) {
                curr.successors[c - 'a'] = new TrieII();
            }

            curr.countPrefix++;
            curr = curr.successors[c - 'a'];
        }

        curr.countPrefix++;
        curr.countWord++;
        curr.word = word;
    }

    public int countWordsEqualTo(String word) {
        if (word == null) {
            return 0;
        }

        char[] wordArray = word.toCharArray();
        TrieII curr = this;
        for (int i = 0; i < wordArray.length; i++) {
            char c = wordArray[i];
            if (curr.successors[c - 'a'] == null) {
                return 0;
            }

            curr = curr.successors[c - 'a'];
        }

        return curr.countWord;
    }

    public int countWordsStartingWith(String prefix) {
        if (prefix == null) {
            return 0;
        }

        char[] prefixArray = prefix.toCharArray();
        TrieII curr = this;
        for (int i = 0; i < prefixArray.length; i++) {
            char c = prefixArray[i];
            if (curr.successors[c - 'a'] == null) {
                return 0;
            }

            curr = curr.successors[c - 'a'];
        }

        return curr.countPrefix;
    }

    public void erase(String word) {
        if (word == null) {
            return;
        }

        char[] wordArray = word.toCharArray();
        TrieII curr = this;
        for (int i = 0; i < wordArray.length; i++) {
            char c = wordArray[i];
            if (curr.successors[c - 'a'] == null) {
                return;
            }

            curr.countPrefix--;
            curr = curr.successors[c - 'a'];
        }

        curr.countPrefix--;
        curr.countWord--;
    }
}

/*
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * int param_2 = obj.countWordsEqualTo(word);
 * int param_3 = obj.countWordsStartingWith(prefix);
 * obj.erase(word);
 */
