package heap;
/*
https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/
 */
import dataStructure.MatrixNode;

import java.util.PriorityQueue;
import java.util.Queue;

public class KthSmallestElementInASortedMatrix {
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        Queue<MatrixNode> heap = new PriorityQueue<>((MatrixNode a, MatrixNode b) -> a.val - b.val);
        boolean[][] isVisited = new boolean[n][n];

        heap.offer(new MatrixNode(matrix[0][0], 0, 0));
        isVisited[0][0] = true;

        int[] dx = new int[]{0, 1};
        int[] dy = new int[]{1, 0};

        for (int i = 0; i < k - 1; i++) {
            MatrixNode smallest = heap.poll();
            for (int j = 0; j < 2; j++) {
                int nextX = smallest.x + dx[j];
                int nextY = smallest.y + dy[j];
                if (nextX < n && nextY < n && !isVisited[nextX][nextY]) {
                    heap.offer(new MatrixNode(matrix[nextX][nextY], nextX, nextY));
                    isVisited[nextX][nextY] = true;
                }
            }
        }

        return heap.peek().val;
    }
}
