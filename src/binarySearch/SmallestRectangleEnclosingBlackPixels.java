package binarySearch;

/*
https://leetcode.com/problems/smallest-rectangle-enclosing-black-pixels/
 */
public class SmallestRectangleEnclosingBlackPixels {
    public int minArea(char[][] image, int x, int y) {
        if (image == null || image.length == 0 || image[0].length == 0) {
            return -1;
        }

        int top = findSmallestX(image, x);
        int bottom = findBiggestX(image, x);
        int left = findSmallestY(image, y);
        int right = findBiggestY(image, y);

        return (right - left + 1) * (bottom - top + 1);
    }

    private int findSmallestX(char[][] image, int x) {
        int start = 0;
        int end = x;

        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (isAllWhiteInRow(image, mid)) {
                start = mid;
            } else {
                end = mid;
            }
        }

        if (isAllWhiteInRow(image, start)) {
            return end;
        } else {
            return start;
        }
    }

    private int findBiggestX(char[][] image, int x) {
        int start = x;
        int end = image.length - 1;

        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (isAllWhiteInRow(image, mid)) {
                end = mid;
            } else {
                start = mid;
            }
        }

        if (isAllWhiteInRow(image, end)) {
            return start;
        } else {
            return end;
        }
    }

    private boolean isAllWhiteInRow(char[][] image, int x) {
        for (int i = 0; i < image[0].length; i++) {
            if (image[x][i] == '1') {
                return false;
            }
        }
        return true;
    }

    private int findSmallestY(char[][] image, int y) {
        int start = 0;
        int end = y;

        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (isAllWhiteInColumn(image, mid)) {
                start = mid;
            } else {
                end = mid;
            }
        }

        if (isAllWhiteInColumn(image, start)) {
            return end;
        } else {
            return start;
        }
    }

    private int findBiggestY(char[][] image, int y) {
        int start = y;
        int end = image[0].length - 1;

        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (isAllWhiteInColumn(image, mid)) {
                end = mid;
            } else {
                start = mid;
            }
        }

        if (isAllWhiteInColumn(image, end)) {
            return start;
        } else {
            return end;
        }
    }

    private boolean isAllWhiteInColumn(char[][] image, int y) {
        for (int i = 0; i < image.length; i++) {
            if (image[i][y] == '1') {
                return false;
            }
        }
        return true;
    }
}
