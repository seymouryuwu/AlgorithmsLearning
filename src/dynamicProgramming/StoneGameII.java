package dynamicProgramming;
/*
https://leetcode.com/problems/stone-game-ii/
 */

public class StoneGameII {
    public int stoneGameII(int[] piles) {
        if (piles == null || piles.length == 0) {
            return 0;
        }

        int n = piles.length;

        // maxM[i] is when the player faces piles[i] ... piles[n - 1], the max M this position can have.
        // so it can be any number from 1 to maxM[i]. And it is just M, not X.
        int[] maxM = findMaxM(n);


        // dp[i][m] stores at the situation when piles[i] ... piles[n - 1] remained,
        // and the M == m, (the max score that the player who starts first right now
        // could get in the end) - (the other player's score).
        int[][] dp = new int[n][maxM[n - 1] + 1];
        for (int i = n - 1; i >= 0; i--) {
            for (int m = 1; m <= maxM[i]; m++) {
                // For every state dp[i][m], player can choose number of piles from 1 to 2 * m,
                // we need to find every step he chooses that can get max difference.
                int maxDiff = Integer.MIN_VALUE;
                int sum = 0;
                // x is the number of piles player chooses.

                for (int x = 1; i + x - 1 < n && x <= 2 * m; x++) {
                    sum += piles[i + x - 1];
                    // when the remaining piles are less than or equal to 2 * m, we don't need to check
                    // the next turn's state, because next turn is nothing, this player just pick all the rest.
                    if (i + 2 * m < n) {
                        // if x is greater than m, the next step state will not be m. it will be x.
                        maxDiff = Math.max(maxDiff, sum - dp[i + x][Math.max(m, x)]);
                    }
                }

                if (i + 2 * m >= n) {
                    maxDiff = sum;
                }

                dp[i][m] = maxDiff;
            }
        }

        int sum = 0;
        for (int p : piles) {
            sum += p;
        }

        return (sum + dp[0][1]) / 2;
    }

    // at each situation i, the max M it might have
    private int[] findMaxM(int n) {
        int[] maxM = new int[n];
        maxM[0] = 1;
        for (int i = 1; i < n; i++) {
            int max = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (i - j <= maxM[j] * 2) {
                    max = Math.max(max, i - j);
                } else {
                    break;
                }
            }

            maxM[i] = max;
        }

        return maxM;
    }

    // use suffixSum to make it neat
    public int stoneGameII2(int[] piles) {
        if (piles == null || piles.length == 0) {
            return 0;
        }

        int n = piles.length;

        // suffix sum
        // suffixSum[i] is the sum from piles[i] (included) to the end;
        int[] suffixSum = new int[n];
        int sum = 0;
        for (int i = n - 1; i >= 0; i--) {
            sum += piles[i];
            suffixSum[i] = sum;
        }

        // maxM[i] is when the player faces piles[i] ... piles[n - 1], the max M this state can have.
        // so it can be any number from 1 to maxM[i]. Note it is just M, not X.
        int[] maxM = findMaxM(n);

        // dp[i][m] stores at the situation when piles[i] ... piles[n - 1] remained,
        // and the M == m, (the max score that the player who starts first right now
        // could get in the end) - (the other player's score).
        int[][] dp = new int[n][maxM[n - 1] + 1];
        for (int i = n - 1; i >= 0; i--) {
            for (int m = 1; m <= maxM[i]; m++) {
                // when the remaining piles are less than or equal to 2 * m, we don't need to check
                // the next turn's state, because next turn there is nothing, this player just pick all the rest.
                if (n - i <= 2 * m) {
                    dp[i][m] = suffixSum[i];
                    continue;
                }

                // For every state dp[i][m], player can choose number of piles from 1 to 2 * m,
                // we need to find every step he chooses that can get max difference.
                int maxDiff = Integer.MIN_VALUE;
                // x is the number of piles player chooses.
                for (int x = 1; x <= 2 * m; x++) {
                    sum = suffixSum[i] - suffixSum[i + x];
                    // if x is greater than m, the next step state will not be m. it will be x.
                    maxDiff = Math.max(maxDiff, sum - dp[i + x][Math.max(m, x)]);
                }

                dp[i][m] = maxDiff;
            }
        }

        return (suffixSum[0] + dp[0][1]) / 2;
    }
}
