package twoPointers;

/*
https://leetcode.com/problems/rotate-string/
 */
public class RotateString {
    // This question can be solved by two pointers, but I don't know how to do it.
    public boolean rotateString(String A, String B) {
        if (A == null || B == null) {
            return false;
        }

        if (A.equals(B)) {
            return true;
        }

        for (int i = 0; i < A.length() - 1; i++) {
            A = A.substring(1, A.length()) + A.charAt(0);
            if (A.equals(B)) {
                return true;
            }
        }

        return false;
    }
}
