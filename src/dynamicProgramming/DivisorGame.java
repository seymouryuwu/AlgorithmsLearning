package dynamicProgramming;
/*
https://leetcode.com/problems/divisor-game/
 */

public class DivisorGame {
    public boolean divisorGame(int n) {
        return (n % 2 == 0);
    }

    // Because 0 < x < n, when n == 1, there is no choice, so Alice will lose.

    // For every even number n from 2, if when n == n - 1 (which must be odd) Alice would lose,
    // Alice can always choose 1 to let Bob face that situation.
    // So, when n is even, Alice could always win by choosing 1 if she would lose when n == n - 1.

    // Odd number can only have odd factors, so if an odd number is reduced by one of its factors,
    // the difference must be even.
    // For every odd number n from 3, no matter what factor(s) of n Alice chooses, the next turn
    // number must be even, which will let Bob face the above situation. That means, Bob would win,
    // and Alice would lose.

    // The above reasoning for even and odd numbers is true because it is already true when n == 2
    // and n == 3, and the following numbers can also be reasoned in the same way. (Mathematical
    // induction)
}
