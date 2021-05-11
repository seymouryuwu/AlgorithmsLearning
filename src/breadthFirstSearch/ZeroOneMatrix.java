package breadthFirstSearch;

/*
https://leetcode.com/problems/01-matrix/
 */
import java.util.LinkedList;
import java.util.Queue;

public class ZeroOneMatrix {
    private class Pair {
        public int row;
        public int column;
        public Pair(int r, int c) {
            row = r;
            column = c;
        }
    }

    public int[][] updateMatrix(int[][] matrix) {
        if (matrix ==  null) {
            return null;
        }

        int[][] result = new int[matrix.length][matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    result[i][j] = 0;
                } else {
                    result[i][j] = findNearest(matrix, i, j);
                }
            }
        }

        return result;
    }

    private int findNearest(int[][] matrix, int row, int column) {
        int distance = 0;
        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(row, column));

        boolean isFound = false;
        while (!queue.isEmpty() && !isFound) {
            distance++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Pair coor = queue.poll();
                if (coor.row != 0) {
                    if (matrix[coor.row - 1][coor.column] == 0) {
                        isFound = true;
                        break;
                    } else {
                        queue.offer(new Pair(coor.row - 1, coor.column));
                    }
                }

                if (coor.row != matrix.length - 1) {
                    if (matrix[coor.row + 1][coor.column] == 0) {
                        isFound = true;
                        break;
                    } else {
                        queue.offer(new Pair(coor.row + 1, coor.column));
                    }
                }

                if (coor.column != 0) {
                    if (matrix[coor.row][coor.column - 1] == 0) {
                        isFound = true;
                        break;
                    } else {
                        queue.offer(new Pair(coor.row, coor.column - 1));
                    }
                }

                if (coor.column != matrix[0].length - 1) {
                    if (matrix[coor.row][coor.column + 1] == 0) {
                        isFound = true;
                        break;
                    } else {
                        queue.offer(new Pair(coor.row, coor.column + 1));
                    }
                }
            }
        }

        return distance;
    }
}
