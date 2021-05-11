package easy;

public class Sqrt {
    public int mySqrt(int x) {
        int root = 0;
        while (root * root >= 0 && root * root <= x) {
            root++;
        }
        return root-1;
    }

    // is this method better than the first one?
    public int mySqrt2(int x) {
        if (x < 2) return x;

        long num;
        int pivot, left = 2, right = x / 2;
        while (left <= right) {
            pivot = left + (right - left) / 2;
            num = (long)pivot * pivot;
            if (num > x) right = pivot - 1;
            else if (num < x) left = pivot + 1;
            else return pivot;
        }

        return right;
    }
}
