package heap;

/*
https://leetcode.com/problems/find-k-pairs-with-smallest-sums/
 */
import dataStructure.MatrixNode;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class FindKPairsWithSmallestSums {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> result = new ArrayList<>();

        int m = nums1.length;
        int n = nums2.length;

        Queue<MatrixNode> heap = new PriorityQueue<>((MatrixNode a, MatrixNode b) -> a.val - b.val);
        boolean[][] isVisited = new boolean[m][n];

        heap.offer(new MatrixNode(nums1[0] + nums2[0], 0, 0));
        isVisited[0][0] = true;

        int[] dx = new int[]{0, 1};
        int[] dy = new int[]{1, 0};

        for (int i = 0; i < k && i < m * n; i++) {
            MatrixNode smallest = heap.poll();
            List<Integer> pair = new ArrayList<>();
            pair.add(nums1[smallest.x]);
            pair.add(nums2[smallest.y]);
            result.add(pair);

            for (int j = 0; j < 2; j++) {
                int nextX = smallest.x + dx[j];
                int nextY = smallest.y + dy[j];

                if (nextX < m && nextY < n && !isVisited[nextX][nextY]) {
                    heap.offer(new MatrixNode(nums1[nextX] + nums2[nextY], nextX, nextY));
                    isVisited[nextX][nextY] = true;
                }
            }
        }

        return result;
    }
}
