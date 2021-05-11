package Trie;

/*
https://leetcode.com/problems/word-squares/
 */

import java.util.ArrayList;
import java.util.List;

public class WordSquares {
    public List<List<String>> wordSquares(String[] words) {
        List<List<String>> wordSquares = new ArrayList<>();
        if (words == null || words.length == 0) {
            return wordSquares;
        }

        TrieWS trie = new TrieWS();
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
                     TrieWS trie) {
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

class TrieWS {
    TrieWS[] successors;
    boolean isWord;
    String word;

    public TrieWS() {
        successors = new TrieWS[26];
        isWord = false;
        word = null;
    }

    public void insert(String word) {
        if (word == null || word.length() == 0) {
            return;
        }

        word = word.toLowerCase();

        char[] wordArray = word.toCharArray();

        TrieWS curr = this;
        for (int i = 0; i < wordArray.length; i++) {
            char c = wordArray[i];
            if (curr.successors[c - 'a'] == null) {
                curr.successors[c - 'a'] = new TrieWS();
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

        TrieWS curr = this;
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

    private void findWordsUnderTrieNode(TrieWS node, List<String> words) {
        if (node == null) {
            return;
        }

        if (node.isWord) {
            words.add(node.word);
        }

        for (TrieWS successor : node.successors) {
            findWordsUnderTrieNode(successor, words);
        }
    }
}
