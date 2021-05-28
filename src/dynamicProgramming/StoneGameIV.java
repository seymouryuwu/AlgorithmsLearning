package dynamicProgramming;
/*
https://leetcode.com/problems/stone-game-iv/
 */

import java.util.ArrayList;
import java.util.List;

public class StoneGameIV {
    public boolean winnerSquareGame(int n) {
        if (n == 0) {
            return false;
        }

        List<Integer> square = new ArrayList<>();
        for (long i = 1; i * i <= n; i++) {
            square.add((int)(i * i));
        }

        boolean[] dp = new boolean[n + 1];
        dp[1] = true;
        for (int i = 2; i <= n; i++) {
            dp[i] = false;
            for (int j = 0; j < square.size() && square.get(j) <= i; j++) {
                if (!dp[i - square.get(j)]) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[n];
    }
}
