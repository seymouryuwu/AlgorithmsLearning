package trie;

/*
https://leetcode.com/problems/design-add-and-search-words-data-structure/
 */
public class DesignAddAndSearchWordsDataStructure {

}

class WordDictionary {
    private static class Trie {
        private Trie[] successors;
        private boolean isWord;
        private String word;

        public Trie() {
            successors = new Trie[26];
            for (int i = 0; i < 26; i++) {
                successors[i] = null;
            }

            isWord = false;
            word = null;
        }

        public void addWord(String word) {
            char[] wordArray = word.toCharArray();
            Trie curr = this;
            for (int i = 0; i < wordArray.length; i++) {
                char c = wordArray[i];
                if (curr.successors[c - 'a'] == null) {
                    curr.successors[c - 'a'] = new Trie();
                }

                curr = curr.successors[c - 'a'];
            }

            curr.isWord = true;
            curr.word = word;
        }

        public boolean search(String word) {
            char[] wordArray = word.toCharArray();
            Trie curr = this;
            for (int i = 0; i < wordArray.length; i++) {
                char c = wordArray[i];

                if (c != '.') {
                    if (curr.successors[c - 'a'] == null) {
                        return false;
                    }

                    curr = curr.successors[c - 'a'];
                } else {
                    for (Trie successor : curr.successors) {
                        if (successor != null &&
                                successor.search(word.substring(i + 1))) {
                            return true;
                        }
                    }

                    return false;
                }
            }

            return curr.isWord;
        }

        // A better implement of DFS for search method
        public boolean search2(String word) {
            return find(word, 0, this);
        }

        private boolean find(String word, int index, Trie curr) {
            if (index == word.length()) {
                return curr.isWord;
            }

            char c = word.charAt(index);

            if (c == '.') {
                for (int i = 0; i < 26; i++) {
                    if (curr.successors[i] != null &&
                            find(word, index + 1, curr.successors[i])) {
                        return true;
                    }
                }

                return false;

            } else {
                if (curr.successors[c - 'a'] == null) {
                    return false;
                } else {
                    return find(word, index + 1, curr.successors[c - 'a']);
                }
            }
        }
    }

    private Trie trie;

    /** Initialize your data structure here. */
    public WordDictionary() {
        trie = new Trie();
    }

    public void addWord(String word) {
        if (word == null) {
            return;
        }

        trie.addWord(word);
    }

    public boolean search(String word) {
        if (word == null) {
            return false;
        }

        return trie.search(word);
    }
}

/*
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */

