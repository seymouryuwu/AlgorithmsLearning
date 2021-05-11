package dynamicProgramming;

/*
https://leetcode.com/problems/russian-doll-envelopes/
 */
public class RussianDollEnvelopes {
    public int maxEnvelopes(int[][] envelopes) {
        sortEnvelopes(envelopes);

        int max = 1;

        int[] countMax = new int[envelopes.length];
        countMax[0] = 1;
        for (int i = 1; i < countMax.length; i++) {
            int maxForThis = 1;
            for (int j = 0; j < i; j++) {
                if (envelopes[j][0] < envelopes[i][0] &&
                        envelopes[j][1] < envelopes[i][1]) {
                    maxForThis = Math.max(maxForThis, countMax[j] + 1);
                }
            }

            countMax[i] = maxForThis;
            max = Math.max(max, countMax[i]);
        }

        return max;
    }

    private void sortEnvelopes(int[][] envelopes) {
        int WIDTH = 0;
        int HEIGHT = 1;

        sortWidthOrHeight(envelopes, 0, envelopes.length - 1, WIDTH);

        int start = 0;
        for (int i = 0; i < envelopes.length; i++) {
            if (i == envelopes.length - 1 || envelopes[i + 1][0] != envelopes[start][0]) {
                if (i > start) {
                    sortWidthOrHeight(envelopes, start, i, HEIGHT);
                }

                start = i + 1;
            }
        }
    }

    private void sortWidthOrHeight(int[][] envelopes, int start, int end, int side) {
        if (start >= end) {
            return;
        }

        int pivot = envelopes[end][side];
        int i = start;
        for (int j = start; j < end; j++) {
            if (envelopes[j][side] < pivot) {
                int[] temp = envelopes[j];
                envelopes[j] = envelopes[i];
                envelopes[i] = temp;
                i++;
            }
        }

        int[] temp = envelopes[end];
        envelopes[end] = envelopes[i];
        envelopes[i] = temp;

        sortWidthOrHeight(envelopes, start, i - 1, side);
        sortWidthOrHeight(envelopes, i + 1, end, side);
    }
}
