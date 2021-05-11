package breadthFirstSearch;

/*
https://leetcode.com/problems/minimum-knight-moves/
 */
import java.util.LinkedList;
import java.util.Queue;

public class MinimumKnightMoves {
    // Approach 1: This one will cause timeout on Leetcode/
    private class Pair {
        public int row;
        public int column;
        public Pair(int row, int column) {
            this.row = row;
            this.column = column;
        }
    }

    public int minKnightMoves(int x, int y) {
        int steps = 0;
        if (x == 0 && y == 0) {
            return steps;
        }

        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(0, 0));

        int[] deltaX = {-1, -2, -2, -1, 1, 2, 2, 1};
        int[] deltaY = {2, 1, -1, -2, -2, -1, 1, 2};

        boolean isReached = false;
        while (!isReached) {
            steps++;
            int size = queue.size();
            for (int i = 0; i < size && !isReached; i++) {
                Pair coor = queue.poll();
                for (int j = 0; j < 8; j++) {
                    int nextRow = coor.row + deltaX[j];
                    int nextColumn = coor.column + deltaY[j];
                    if (nextRow == x && nextColumn == y) {
                        isReached = true;
                        break;
                    } else {
                        queue.offer(new Pair(nextRow, nextColumn));
                    }
                }
            }
        }

        return steps;
    }

    // Approach 2: Using symmetry and recording visited points,
    // it speeds up to be accepted by Leetcode.
    public int minKnightMoves2(int x, int y) {
        x = Math.abs(x);
        y = Math.abs(y);

        int steps = 0;
        if (x == 0 && y == 0) {
            return steps;
        }

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0,0});

        boolean[][] visited = new boolean[x + 4][y + 4];
        visited[1][1] = true;

        int[] deltaX = {-1, -2, -2, -1, 1, 2, 2, 1};
        int[] deltaY = {2, 1, -1, -2, -2, -1, 1, 2};


        while (!queue.isEmpty()) {
            steps++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] coor = queue.poll();
                for (int j = 0; j < 8; j++) {
                    int nextX = coor[0] + deltaX[j];
                    int nextY = coor[1] + deltaY[j];
                    if (nextX < -1 || nextY < -1 || nextX > x + 2 || nextY > y + 2) {
                        continue;
                    }

                    if (nextX == x && nextY == y) {
                        return steps;
                    } else if (!visited[nextX + 1][nextY + 1]) {
                        queue.offer(new int[]{nextX, nextY});
                        visited[nextX + 1][nextY + 1] = true;
                    }
                }
            }
        }

        return -1;
    }
}
