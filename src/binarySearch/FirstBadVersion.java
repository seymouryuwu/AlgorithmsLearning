package binarySearch;

/*
https://leetcode.com/problems/first-bad-version/
 */

/*
The isBadVersion API is defined in the parent class VersionControl.
    boolean isBadVersion(int version);
*/
// public class FirstBadVersion extends VersionControl {
public class FirstBadVersion {
    public int firstBadVersion(int n) {
        int start = 0;
        int end = n;

        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (isBadVersion(mid)) {
                end = mid;
            } else {
                start = mid;
            }
        }

        if (isBadVersion(start)) {
            return start;
        }

        if (isBadVersion(end)) {
            return end;
        }

        return -1;
    }

    // This class is just for no error when compiling
    private boolean isBadVersion(int n) {
        return false;
    }
}
