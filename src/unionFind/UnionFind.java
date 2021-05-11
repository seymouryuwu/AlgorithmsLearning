package unionFind;

public class UnionFind {
    int[] parents;
    int[] size;

    public UnionFind(int n) {
        parents = new int[n];
        size = new int[n];

        for (int i = 0; i < n; i++) {
            parents[i] = i;
            size[i] = 1;
        }
    }

    public int find(int child) {
        int curr = child;
        while (parents[curr] != curr) {
            curr = parents[curr];
        }

        int parent = curr;

        curr = child;
        while (curr != parent) {
            int temp = parents[curr];
            parents[curr] = parent;
            curr = temp;
        }

        return parent;
    }

    public boolean union(int a, int b) {
        int parentA = find(a);
        int parentB = find(b);

        if (parentA == parentB) {
            return false;
        }

        if (size[parentA] > size[parentB]) {
            parents[parentB] = parentA;
            size[parentA] += size[parentB];
        } else {
            parents[parentA] = parentB;
            size[parentB] += size[parentA];
        }

        return true;
    }
}
