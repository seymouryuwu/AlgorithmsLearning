package dynamicProgramming;

/*
https://leetcode.com/problems/longest-well-performing-interval/
 */
import java.util.HashMap;

public class LongestWellPerformingInterval {
    // Approach 1: time complexity O(n^2)
    public int longestWPI(int[] hours) {
        if (hours == null || hours.length == 0) {
            return 0;
        }

        // difference[k] stores the difference of (tiring days - non-tiring days)
        // from day k to day i. (k <= i)
        // when k > i, it stores 0.
        int[] difference = new int[hours.length];
        int lgt = 0;

        for (int i = 0; i < hours.length; i++) {
            int change = (hours[i] > 8) ? 1 : -1;
            int longest = 0;
            for (int k = i; k >= 0; k--) {
                difference[k] += change;
                if (difference[k] > 0) {
                    longest = i - k + 1;
                }
            }

            lgt = Math.max(lgt, longest);
        }

        return lgt;
    }

    // Approach 2: time complexity is O(n)
    public int longestWPI2(int[] hours) {
        if (hours.length == 0) return 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        int max = 0;
        int sum = 0;
        for (int i = 0; i < hours.length; i++) {
            sum += hours[i] > 8 ? 1 : -1;
            if (!map.containsKey(sum)) map.put(sum, i);
            if (sum > 0) max = i+1;
            else if (map.containsKey(sum - 1)) {
                max = Math.max(max, i - map.get(sum-1));
            }
        }
        return max;
    }
}
