package dynamicProgramming;
/*
https://www.lintcode.com/problem/394/
 */

public class CoinsInALine {
    public boolean firstWillWin(int n) {
        if (n < 1) {
            return false;
        }

        if (n < 3) {
            return true;
        }

        boolean[] firstWin = new boolean[n + 1];
        firstWin[1] = true;
        firstWin[2] = true;

        for (int i = 3; i <= n; i++) {
            if (firstWin[i - 2] && firstWin[i - 1]) {
                firstWin[i] = false;
            } else {
                firstWin[i] = true;
            }
        }

        return firstWin[n];
    }
}
