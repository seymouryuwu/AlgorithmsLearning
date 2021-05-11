package unionFind;

/*
https://leetcode.com/problems/number-of-islands-ii/
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NumberOfIslandsII {
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> numIslands = new ArrayList<>();

        if (positions == null || positions.length == 0) {
            numIslands.add(0);
            return numIslands;
        }

        int[][] grid = new int[m][n];
        int islandsNum = 0;

        Map<Integer, Integer> landRepresentatives = new HashMap<>();
        Map<Integer, Integer> islandSize = new HashMap<>();

        for (int i = 0; i < positions.length; i++) {
            int coordinate = positions[i][0] * n + positions[i][1];
            landRepresentatives.put(coordinate, coordinate);
            islandSize.put(coordinate, 1);
        }

        for (int i = 0; i < positions.length; i++) {
            int coordinate = positions[i][0] * n + positions[i][1];

            islandsNum = addLand(coordinate,
                    n,
                    grid,
                    islandsNum,
                    landRepresentatives,
                    islandSize);
            numIslands.add(islandsNum);
        }

        return numIslands;
    }

    private int addLand(int coordinate,
                        int columnsNum,
                        int[][] grid,
                        int islandsNum,
                        Map<Integer, Integer> landRepresentatives,
                        Map<Integer, Integer> islandSize) {

        int row = coordinate / columnsNum;
        int column = coordinate % columnsNum;
        if (grid[row][column] == 1) {
            return islandsNum;
        }

        islandsNum++;
        grid[row][column] = 1;

        int[] dR = new int[]{1, -1, 0, 0};
        int[] dC = new int[]{0, 0, 1, -1};

        for (int i = 0; i < 4; i++) {
            int adjacentRow = row + dR[i];
            int adjacentColumn = column + dC[i];
            if (adjacentRow >= 0 &&
                    adjacentRow < grid.length &&
                    adjacentColumn >= 0 &&
                    adjacentColumn < grid[0].length &&
                    grid[adjacentRow][adjacentColumn] == 1) {

                int adjCoordinate = adjacentRow * columnsNum + adjacentColumn;
                islandsNum -= combine(coordinate,
                        adjCoordinate,
                        landRepresentatives,
                        islandSize);
            }
        }

        return islandsNum;
    }

    private int combine(int coor1,
                        int coor2,
                        Map<Integer, Integer> landRepresentatives,
                        Map<Integer, Integer> islandSize) {
        int representative1 = find(coor1, landRepresentatives);
        int representative2 = find(coor2, landRepresentatives);

        if (representative1 == representative2) {
            return 0;
        }

        int size1 = islandSize.get(representative1);
        int size2 = islandSize.get(representative2);
        if (size1 < size2) {
            landRepresentatives.put(representative1, representative2);
            islandSize.put(representative2, size1 + size2);
        } else {
            landRepresentatives.put(representative2, representative1);
            islandSize.put(representative1, size1 + size2);
        }

        return 1;
    }

    private int find(int coor, Map<Integer, Integer> landRepresentatives) {
        int repre = coor;
        while (repre != landRepresentatives.get(repre)) {
            repre = landRepresentatives.get(repre);
        }

        int curr = coor;
        while (curr != repre) {
            int temp = landRepresentatives.get(curr);
            landRepresentatives.put(curr, repre);
            curr = temp;
        }

        return repre;
    }
}
