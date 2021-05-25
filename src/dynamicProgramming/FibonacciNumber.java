package dynamicProgramming;

/*
https://leetcode.com/problems/fibonacci-number/
 */
public class FibonacciNumber {
    // Approach 1: recursion
    public int fib(int n) {
        if (n <= 1) {
            return n;
        }

        return fib(n - 1) + fib(n - 2);
    }

    // Approach 2: dynamic programming
    public int fib2(int n) {
        if (n <= 1) {
            return n;
        }

        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }

    // using scrolling array (length is 2) to optimise space complexity
    public int fib3(int n) {
        if (n <= 1) {
            return n;
        }

        int old = 1;
        int now = 0;
        for (int i = 2; i <= n; i++) {
            now = now + old;
            int temp = old;
            old = now;
            now = temp;
        }

        return old;
    }
}
