package binarySearch;

/*
https://leetcode.com/problems/search-in-a-sorted-array-of-unknown-size/
 */

/*
This is ArrayReader's API interface.
You should not implement it, or speculate about its implementation
    interface ArrayReader {
       public int get(int index) {}
    }
 */

public class SearchInASortedArrayOfUnknownSize {
    public int search(ArrayReader reader, int target) {
        int end = 1;
        int start = 0;

        while (reader.get(end) < target) {
            start = end;
            end = end * 2;
        }

        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (reader.get(mid) == target) {
                return mid;
            } else if (reader.get(mid) < target) {
                start = mid;
            } else {
                end = mid;
            }
        }

        if (reader.get(start) == target) {
            return start;
        }

        if (reader.get(end) == target) {
            return end;
        }

        return -1;
    }

    // This class is just for no error when compiling
    private static class ArrayReader {
        public int get(int index) {
            return 0;
        }
    }
}
