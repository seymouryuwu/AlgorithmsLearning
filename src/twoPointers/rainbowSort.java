package twoPointers;

/*
https://www.lintcode.com/problem/143/
This question is on Lintcode, not Leetcode
 */
public class rainbowSort {
    public void sortColors2(int[] colors, int k) {
        sort(colors, 0, colors.length - 1, 1, k);
    }

    private void sort(int[] colors, int start, int end, int colorFrom, int colorTo) {
        if (colorFrom == colorTo) {
            return;
        }

        int pivot = (colorFrom + colorTo) / 2;
        int i = start;
        for (int j = start; j <= end; j++) {
            if (colors[j] <= pivot) {
                int temp = colors[j];
                colors[j] = colors[i];
                colors[i] = temp;

                i++;
            }
        }

        sort(colors, start, i - 1, colorFrom, pivot);
        sort(colors, i, end, pivot + 1, colorTo);
    }
}
