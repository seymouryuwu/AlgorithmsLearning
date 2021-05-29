package greedy;
/*
https://leetcode.com/problems/stone-game-vi/
 */

import java.util.PriorityQueue;
import java.util.Queue;

public class StoneGameVI {
    public int stoneGameVI(int[] aliceValues, int[] bobValues) {
        if (aliceValues == null || bobValues == null ||
                aliceValues.length == 0 || bobValues.length == 0) {
            return 0;
        }

        int n = aliceValues.length;
        // to win the game, every player wants to pick the index i with
        // maximum of (aliceValues[i] + bobValues);

        boolean[] isTakenByAlice = new boolean[n];
        Queue<SumIndex> heap = new PriorityQueue<>((a, b) -> Integer.compare(b.sum, a.sum));

        for (int i = 0; i < n; i++) {
            heap.offer(new SumIndex(aliceValues[i] + bobValues[i], i));
        }

        while (!heap.isEmpty()) {
            SumIndex aliceTake = heap.poll();
            isTakenByAlice[aliceTake.index] = true;

            // Bob takes.
            if (!heap.isEmpty()) {
                heap.poll();
            }
        }

        int aliceScore = 0;
        int bobScore = 0;
        for (int i = 0; i < n; i++) {
            if (isTakenByAlice[i]) {
                aliceScore += aliceValues[i];
            } else {
                bobScore += bobValues[i];
            }
        }

        if (aliceScore > bobScore) {
            return 1;
        }

        if (aliceScore < bobScore) {
            return -1;
        }

        return 0;
    }

    private class SumIndex {
        public int sum;
        public int index;

        public SumIndex(int sum, int index) {
            this.sum = sum;
            this.index = index;
        }
    }
}
