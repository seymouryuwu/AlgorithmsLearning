package dynamicProgramming;
/*
https://www.lintcode.com/problem/395/
 */

// We have to assume the initialed score for each player is 0, in case when there
// are only one or two numbers, the second player might have no chance to pick number.
// it's not specified in the description.
public class CoinsInALineII {
    public boolean firstWillWin(int[] values) {
        if (values == null || values.length == 0) {
            return false;
        }

        int n = values.length;
        // dp states store ((sum of first player) - (sum of second player)) at the situation
        // when there are (n - i) coins remained.
        int[] states = new int[n];

        states[n - 1] = values[n - 1];
        if (n == 1) {
            return (states[0] >= 0);
        }

        states[n - 2] = Math.max((values[n - 1] + values[n - 2]), (values[n - 2] - values[n - 1]));
        if (n == 2) {
            return (states[0] >= 0);
        }

        for (int i = n - 3; i >= 0; i--) {
            int pickTwo = values[i] + values[i + 1] - states[i + 2];
            int pickOne = values[i] - states[i + 1];
            states[i] = Math.max(pickTwo, pickOne);
        }

        return (states[0] >= 0);
    }
}
