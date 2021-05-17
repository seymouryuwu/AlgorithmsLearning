package trie;

/*
https://leetcode.com/problems/word-search-ii/
 */
import java.util.*;

public class WordSearchII {
    private static class Trie {
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

    public List<String> findWords(char[][] board, String[] words) {
        List<String> results = new ArrayList<>();
        if (board == null || words == null || words.length == 0) {
            return results;
        }

        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }

        Set<String> r = new HashSet<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(board, i, j, trie, r);
            }
        }

        results.addAll(r);

        return results;
    }

    // row and col is the cell that needs to be found.
    // trie is the node whose successors may contain that letter
    private void dfs(char[][] board, int row, int col, Trie trie, Set<String> results) {
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length) {
            return;
        }

        if (board[row][col] == 0) {
            return;
        }

        char c = board[row][col];
        if (!trie.successors.containsKey(c)) {
            return;
        }

        trie = trie.successors.get(c);
        board[row][col] = 0;

        if (trie.isWord) {
            results.add(trie.word);
        }

        int[] dRow = {1, -1, 0, 0};
        int[] dCol = {0, 0, 1, -1};
        for (int i = 0; i < 4; i++) {
            dfs(board, row + dRow[i], col + dCol[i], trie, results);
        }

        board[row][col] = c;
    }
}


