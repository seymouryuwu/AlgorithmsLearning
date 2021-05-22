package binarySearch;
/*
https://www.lintcode.com/problem/437/
 */

public class CopyBooks {
    public int copyBooks(int[] pages, int k) {
        if (pages == null || pages.length == 0) {
            return 0;
        }

        int shortest = pages[0];
        int longest = 0;

        for (int page : pages) {
            shortest = Math.max(shortest, page);
            longest += page;
        }

        while (shortest + 1 < longest) {
            int mid = shortest + (longest - shortest) / 2;
            int copiersNeeded = countCopiers(pages, mid);
            if (copiersNeeded > k) {
                shortest = mid;
            } else {
                longest = mid;
            }
        }

        if (countCopiers(pages, shortest) <= k) {
            return shortest;
        } else {
            return longest;
        }
    }

    // check within time t, how many copiers are needed
    // the shorter the time given, the more copiers needed
    private int countCopiers(int[] pages, int t) {
        int count = 0;
        int index = 0;
        while (index < pages.length) {
            count++;
            int pageSum = 0;
            while (index < pages.length && pageSum + pages[index] <= t) {
                pageSum += pages[index];
                index++;
            }
        }

        return count;
    }
}
