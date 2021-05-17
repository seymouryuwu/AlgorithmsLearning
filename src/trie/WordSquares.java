package trie;

/*
https://leetcode.com/problems/word-squares/
 */

import java.util.ArrayList;
import java.util.List;

public class WordSquares {
    private static class Trie {
        Trie[] successors;
        boolean isWord;
        String word;

        public Trie() {
            successors = new Trie[26];
            isWord = false;
            word = null;
        }

        public void insert(String word) {
            if (word == null || word.length() == 0) {
                return;
            }

            word = word.toLowerCase();

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

        public List<String> findWordsWithPrefix(String prefix) {
            List<String> wordsWithPrefix = new ArrayList<>();
            if (prefix == null || prefix.length() == 0) {
                return wordsWithPrefix;
            }

            prefix = prefix.toLowerCase();

            char[] prefixArray = prefix.toCharArray();

            Trie curr = this;
            for (int i = 0; i < prefixArray.length; i++) {
                char c = prefixArray[i];
                if (curr.successors[c - 'a'] == null) {
                    return wordsWithPrefix;
                }

                curr = curr.successors[c - 'a'];
            }

            findWordsUnderTrieNode(curr, wordsWithPrefix);

            return wordsWithPrefix;
        }

        private void findWordsUnderTrieNode(Trie node, List<String> words) {
            if (node == null) {
                return;
            }

            if (node.isWord) {
                words.add(node.word);
            }

            for (Trie successor : node.successors) {
                findWordsUnderTrieNode(successor, words);
            }
        }
    }

    public List<List<String>> wordSquares(String[] words) {
        List<List<String>> wordSquares = new ArrayList<>();
        if (words == null || words.length == 0) {
            return wordSquares;
        }

        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }

        List<String> wordSquare = new ArrayList<>();
        for (String word : words) {
            wordSquare.add(word);
            dfs(wordSquares, wordSquare, trie);
            wordSquare.clear();
        }

        return wordSquares;
    }

    private void dfs(List<List<String>> wordSquares,
                     List<String> wordSquare,
                     Trie trie) {
        int length = wordSquare.get(0).length();
        int size = wordSquare.size();

        if (length == size) {
            wordSquares.add(new ArrayList<>(wordSquare));
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (String word : wordSquare) {
            sb.append(word.charAt(size));
        }

        String prefix = sb.toString();

        List<String> candidates = trie.findWordsWithPrefix(prefix);
        for (String candidate : candidates) {
            if (candidate.length() == length) {
                wordSquare.add(candidate);
                dfs(wordSquares, wordSquare, trie);
                wordSquare.remove(wordSquare.size() - 1);
            }
        }
    }
}


