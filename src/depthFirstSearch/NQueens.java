package depthFirstSearch;

/*
https://leetcode.com/problems/n-queens/
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NQueens {
    private static int QUEEN = 1;
    private static int EMPTY = 0;

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();

        int[][] chessboard = new int[n][n];
        for (int[] row : chessboard) {
            Arrays.fill(row, EMPTY);
        }

        helper(result, chessboard, 0);

        return result;
    }

    private void helper(List<List<String>> result,
                        int[][] chessboard,
                        int row) {
        int n = chessboard.length;
        for (int i = 0; i < n; i++) {
            if (isValid(chessboard, row, i)) {
                chessboard[row][i] = QUEEN;
                if (row == n - 1) {
                    result.add(serializeChessboard(chessboard));
                } else {
                    helper(result, chessboard, row + 1);
                }

                chessboard[row][i] = EMPTY;
            }
        }
    }

    private boolean isValid(int[][] chessboard, int row, int column) {
        int n = chessboard.length;

        for (int i = 0; i < row; i++) {
            if (chessboard[i][column] == QUEEN) {
                return false;
            }
        }

        for (int i = 1; i < n; i++) {
            if (row - i >= 0 && column - i >= 0) {
                if (chessboard[row - i][column - i] == QUEEN) {
                    return false;
                }
            }

            if (row - i >= 0 && column + i < n) {
                if (chessboard[row - i][column + i] == QUEEN) {
                    return false;
                }
            }
        }

        return true;
    }

    private List<String> serializeChessboard(int[][] chessboard) {
        List<String> serializedChessboard = new ArrayList<>();
        for (int[] row : chessboard) {
            StringBuilder sb = new StringBuilder();
            for (int point : row) {
                if (point == QUEEN) {
                    sb.append("Q");
                } else {
                    sb.append(".");
                }
            }

            serializedChessboard.add(sb.toString());
        }

        return serializedChessboard;
    }
}
