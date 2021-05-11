package breadthFirstSearch;

/*
https://leetcode.com/problems/clone-graph/
 */
import dataStructure.GraphNode;

import java.util.*;

public class CloneGraph {
    public GraphNode cloneGraph(GraphNode node) {
        if (node == null) {
            return null;
        }

        Set<GraphNode> nodes = findNodes(node);

        Map<GraphNode, GraphNode> map = new HashMap<>();
        for (GraphNode n : nodes) {
            map.put(n, new GraphNode(n.val));
        }

        for (GraphNode n : nodes) {
            GraphNode copied = map.get(n);
            List<GraphNode> copiedNeighbours = new ArrayList<>();
            for (GraphNode neighbour : n.neighbors) {
                copiedNeighbours.add(map.get(neighbour));
            }

            copied.neighbors = copiedNeighbours;
        }

        return map.get(node);
    }

    private Set<GraphNode> findNodes(GraphNode node) {
        Queue<GraphNode> queue = new LinkedList<>();
        Set<GraphNode> hash = new HashSet<>();

        queue.offer(node);
        hash.add(node);
        while (!queue.isEmpty()) {
            GraphNode n = queue.poll();
            for (GraphNode neighbour : n.neighbors) {
                if (!hash.contains(neighbour)) {
                    hash.add(neighbour);
                    queue.offer(neighbour);
                }
            }
        }

        return hash;
    }
}
