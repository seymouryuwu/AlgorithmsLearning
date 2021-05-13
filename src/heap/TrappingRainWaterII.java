package heap;

/*
https://leetcode.com/problems/trapping-rain-water-ii/
 */
import java.util.PriorityQueue;
import java.util.Queue;

public class TrappingRainWaterII {
    public int trapRainWater(int[][] heightMap) {
        if (heightMap == null || heightMap.length == 0 || heightMap[0].length == 0) {
            return 0;
        }

        int m = heightMap.length;
        int n = heightMap[0].length;

        boolean[][] isVisited = new boolean[m][n];
        Queue<Cell> boundary = new PriorityQueue<>((a, b) -> Integer.compare(a.height, b.height));

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
                    Cell cell = new Cell(i, j, heightMap[i][j]);
                    boundary.offer(cell);
                    isVisited[i][j] = true;
                }
            }
        }

        int capacity = 0;


        while (!boundary.isEmpty()) {
            Cell lowest = boundary.poll();
            int lowestHeight = lowest.height;
            int x = lowest.x;
            int y = lowest.y;
            // BFS until all adjacent cells' heights are higher than the lowest

            // if the adjacent cell of lowest is not visited,
            //    if the height of the adjacent cell is higher than lowest's
            //       put this cell into queue, and make it visited.
            //    else
            //       capacity += lowest - its height,
            //       make it visited, and do the same thing to its adjacent cell.
            capacity += findCapacity(x, y, lowestHeight, heightMap, boundary, isVisited);
        }

        return capacity;
    }

    private int findCapacity(int x,
                             int y,
                             int lowestHeight,
                             int[][] heightMap,
                             Queue<Cell> boundary,
                             boolean[][] isVisited) {
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        int capacity = 0;

        for (int i = 0; i < 4; i++) {
            int xAdj = x + dx[i];
            int yAdj = y + dy[i];
            if (xAdj >= 0 &&
                    xAdj < heightMap.length &&
                    yAdj >=0 &&
                    yAdj < heightMap[0].length &&
                    !isVisited[xAdj][yAdj]) {
                int heightAdj = heightMap[xAdj][yAdj];

                if (heightAdj >= lowestHeight) {
                    boundary.offer(new Cell(xAdj, yAdj, heightAdj));
                    isVisited[xAdj][yAdj] = true;

                } else {
                    capacity += lowestHeight - heightAdj;
                    isVisited[xAdj][yAdj] = true;
                    capacity += findCapacity(xAdj, yAdj, lowestHeight, heightMap, boundary, isVisited);
                }
            }
        }

        return capacity;
    }
}

class Cell {
    int x;
    int y;
    int height;

    public Cell(int x, int y, int height) {
        this.x = x;
        this.y = y;
        this.height = height;
    }
}
