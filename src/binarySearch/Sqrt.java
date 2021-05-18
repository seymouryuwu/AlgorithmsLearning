package binarySearch;

public class Sqrt {
    // Approach 1: brute force
    public int mySqrt(int x) {
        int root = 0;
        while (root * root >= 0 && root * root <= x) {
            root++;
        }
        return root-1;
    }

    // Approach 2: binary search
    public int mySqrt2(int x) {
        int start = 0;
        int end = x;

        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            long square = (long)mid * (long)mid;
            if (square == x) {
                return mid;
            }

            if (square > x) {
                end = mid;
            } else {
                start = mid;
            }
        }

        long squareOfEnd = (long)end * (long)end;
        if (squareOfEnd > x) {
            return start;
        } else {
            return end;
        }
    }
}
