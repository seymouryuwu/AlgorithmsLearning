package breadthFirstSearch;

/*
https://leetcode.com/problems/number-of-islands/
 */
import java.util.LinkedList;
import java.util.Queue;

public class NumberOfIslands {
    private class Pair {
        public int row;
        public int column;
        public Pair(int row, int column) {
            this.row = row;
            this.column = column;
        }
    }

    public int numIslands(char[][] grid) {
        int number = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    fillWater(grid, i, j);
                    number++;
                }
            }
        }

        return number;
    }

    private void fillWater(char[][] grid, int i, int j) {
        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(i, j));

        while (!queue.isEmpty()) {
            Pair coordinate = queue.poll();
            int row = coordinate.row;
            int column = coordinate.column;
            if (grid[row][column] == '0') {
                continue;
            }
            grid[row][column] = '0';

            if (row != 0 && grid[row - 1][column] == '1') {
                queue.offer(new Pair(row - 1, column));
            }

            if (row != grid.length - 1 && grid[row + 1][column] == '1') {
                queue.offer(new Pair(row + 1, column));
            }

            if (column != 0 && grid[row][column - 1] == '1') {
                queue.offer(new Pair(row, column - 1));
            }

            if (column != grid[0].length - 1 && grid[row][column + 1] == '1') {
                queue.offer(new Pair(row, column + 1));
            }
        }
    }
}
