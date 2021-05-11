package unionFind;

/*
https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/
 */
import java.util.Arrays;

public class NumberOfConnectedComponentsInAnUndirectedGraph {
    // Approach 1: similar to union find, but doesn't store component size
    public int countComponents(int n, int[][] edges) {
        if (edges == null) {
            return 0;
        }

        int[] fathers = new int[n];
        Arrays.fill(fathers, -1);

        for (int[] e : edges) {
            if (fathers[e[0]] == -1 && fathers[e[1]] == -1) {
                fathers[e[0]] = e[0];
                fathers[e[1]] = e[0];
            } else if (fathers[e[0]] == -1) {
                fathers[e[0]] = fathers[e[1]];
            } else if (fathers[e[1]] == -1) {
                fathers[e[1]] = fathers[e[0]];
            } else if (fathers[e[0]] != fathers[e[1]]) {
                int notFather = fathers[e[0]];
                for (int i = 0; i < n; i++) {
                    if (fathers[i] == notFather) {
                        fathers[i] = fathers[e[1]];
                    }
                }
            }
        }

        int components = 0;
        for (int i = 0; i < n; i++) {
            if (fathers[i] == -1) {
                fathers[i] = i;
            }

            if (fathers[i] == i) {
                components++;
            }
        }

        return components;
    }


    // Approach 2: union find
    private int find(int[] representative, int vertex) {
        if (vertex == representative[vertex]) {
            return vertex;
        }

        representative[vertex] = find(representative, representative[vertex]);
        return representative[vertex];
    }

    private int combine(int[] representative, int[] size, int vertex1, int vertex2) {
        vertex1 = find(representative, vertex1);
        vertex2 = find(representative, vertex2);

        if (vertex1 == vertex2) {
            return 0;
        } else {
            if (size[vertex1] > size[vertex2]) {
                size[vertex1] += size[vertex2];
                representative[vertex2] = vertex1;
            } else {
                size[vertex2] += size[vertex1];
                representative[vertex1] = vertex2;
            }
            return 1;
        }
    }

    public int countComponents2(int n, int[][] edges) {
        int[] representative = new int[n];
        int[] size = new int[n];

        for (int i = 0; i < n; i++) {
            representative[i] = i;
            size[i] = 1;
        }

        int components = n;
        for (int i = 0; i < edges.length; i++) {
            components -= combine(representative, size, edges[i][0], edges[i][1]);
        }

        return components;
    }
}
