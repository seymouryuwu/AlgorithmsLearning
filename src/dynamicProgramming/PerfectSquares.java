package dynamicProgramming;

import java.util.ArrayList;
import java.util.List;

/*
https://leetcode.com/problems/perfect-squares/
 */
public class PerfectSquares {
    public int numSquares(int n) {
        List<Integer> perfect = new ArrayList<>();
        for (int i = 1; i * i <= n; i++) {
            perfect.add(i * i);
        }

        int[] numSquares = new int[n + 1];
        numSquares[0] = 0;
        for (int i = 1; i <= n; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < perfect.size() && perfect.get(j) <= i; j++) {
                min = Math.min(min, numSquares[i - perfect.get(j)] + 1);
            }

            numSquares[i] = min;
        }

        return numSquares[n];
    }
}
