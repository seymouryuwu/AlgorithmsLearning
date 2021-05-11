package breadthFirstSearch;

/*
https://leetcode.com/problems/graph-valid-tree/
 */
import unionFind.UnionFind;

import java.util.*;

public class GraphValidTree {
    // Approach 1: BFS
    // It can also be solved by DFS, just change Queue to Stack.
    public boolean validTree(int n, int[][] edges) {
        if (n == 0) {
            return false;
        }

        if (edges.length != n - 1) {
            return false;
        }

        Map<Integer, Set<Integer>> graph = initializeGraph(n, edges);

        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> hash = new HashSet<>();

        queue.offer(0);
        hash.add(0);

        while (!queue.isEmpty()) {
            Integer node = queue.poll();
            for (Integer neighbour : graph.get(node)) {
                if (!hash.contains(neighbour)) {
                    queue.offer(neighbour);
                    hash.add(neighbour);
                }
            }
        }

        return (hash.size() == n);
    }

    private Map<Integer, Set<Integer>> initializeGraph(int n, int[][] edges) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) {
            graph.put(i, new HashSet<Integer>());
        }

        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        return graph;
    }

    // Approach 2: Union Find
    public boolean validTree2(int n, int[][] edges) {
        if (n == 0) {
            return false;
        }

        if (edges.length != n - 1) {
            return false;
        }

        UnionFind unionFind = new UnionFind(n);

        for (int[] edge : edges) {
            if (!unionFind.union(edge[0], edge[1])) {
                return false;
            }
        }

        return true;
    }
}

