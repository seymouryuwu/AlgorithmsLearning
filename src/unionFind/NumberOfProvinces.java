package unionFind;
/*
https://leetcode.com/problems/number-of-provinces/
 */

public class NumberOfProvinces {
    // Approach 1: union find
    // it can also be solved by BFS and DFS
    public int findCircleNum(int[][] isConnected) {
        if (isConnected == null ||
                isConnected.length == 0 ||
                isConnected[0].length != isConnected.length) {
            return 0;
        }

        int n = isConnected.length;
        int numberOfProvinces = n;
        UnionFind uf = new UnionFind(n);

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isConnected[i][j] == 1 && uf.union(i, j)) {
                    numberOfProvinces--;
                }
            }
        }

        return numberOfProvinces;
    }
}
