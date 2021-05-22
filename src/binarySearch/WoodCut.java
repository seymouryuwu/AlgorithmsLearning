package binarySearch;
/*
https://www.lintcode.com/problem/183
 */

public class WoodCut {
    public int woodCut(int[] L, int k) {
        if (L == null || L.length == 0) {
            return 0;
        }

        if (getPieces(L, 1) < k) {
            return 0;
        }

        int small = 1;
        int large = L[0];
        for (int l : L) {
            large = Math.max(large, l);
        }

        while (small + 1 < large) {
            int mid = small + (large - small) / 2;
            long pieces = getPieces(L, mid);
            if (pieces < k) {
                large = mid;
            } else {
                small = mid;
            }
        }

        if (getPieces(L, large) >= k) {
            return large;
        } else {
            return small;
        }
    }

    private long getPieces(int[] L, int length) {
        long sum = 0;
        for (int l : L) {
            sum += l / length;
        }

        return sum;
    }
}
