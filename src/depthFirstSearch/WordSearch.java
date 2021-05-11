package depthFirstSearch;

/*
https://leetcode.com/problems/word-search/
 */
public class WordSearch {
    private int[] dx = {1, -1, 0, 0};
    private int[] dy = {0, 0, 1, -1};

    public boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (dfs(board, i, j, 0, word)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean dfs(char[][] board, int row, int col, int charAt, String word) {
        if (!isValid(row, col, board)) {
            return false;
        }

        char c = word.charAt(charAt);
        if (board[row][col] != c) {
            return false;
        }

        board[row][col] = 0;

        if (charAt == word.length() - 1) {
            return true;
        }

        for (int i = 0; i < 4; i++) {
            if (dfs(board, row + dx[i], col + dy[i], charAt + 1, word)) {
                return true;
            }
        }

        board[row][col] = c;
        return false;
    }

    private boolean isValid(int row, int col, char[][] board) {
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length) {
            return false;
        }

        return board[row][col] != 0;
    }
}
